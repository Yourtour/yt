package com.yt.dal.hbase.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseTable;

public class BeanDescriptorGeneralCacheImpl implements IBeanDescriptorCache,
		InitializingBean {
	private static final Log LOG = LogFactory
			.getLog(BeanDescriptorGeneralCacheImpl.class);
	private final TypeFilter[] ENTITY_TYPE_FILTERS = new TypeFilter[] {
			new AnnotationTypeFilter(HbaseTable.class, false),
			new AssignableTypeFilter(BaseBean.class) };
	private final String RESOURCE_PATTERN = "/**/*.class";

	private String[] beanRepositories;

	private Map<String, BeanDescriptor> cache;

	public BeanDescriptorGeneralCacheImpl() {
		super();
		this.cache = new HashMap<String, BeanDescriptor>();
	}

	public void setBeanRepositories(String[] beanRepositories) {
		this.beanRepositories = beanRepositories;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 启动的时候扫描指定包路径中的Bean，并进行缓存。
		ResourcePatternResolver resourcePatternResolver = ResourcePatternUtils
				.getResourcePatternResolver(new PathMatchingResourcePatternResolver());
		for (String path : beanRepositories) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Scan the BaseBean in the path: %s......", path));
			}
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(path)
					+ RESOURCE_PATTERN;
			Resource[] resources = resourcePatternResolver
					.getResources(pattern);
			MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
					resourcePatternResolver);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader reader = readerFactory
							.getMetadataReader(resource);
					String className = reader.getClassMetadata().getClassName();
					if (matchesEntityTypeFilter(reader, readerFactory)) {
						if (LOG.isDebugEnabled()) {
							LOG.debug(String.format(
									"Found a BaseBean[%s] in the path: %s.",
									className, path));
						}
						putCache(className);
					}
				}
			}
		}
	}

	private boolean matchesEntityTypeFilter(MetadataReader reader,
			MetadataReaderFactory readerFactory) throws IOException {
		for (TypeFilter filter : ENTITY_TYPE_FILTERS) {
			if (filter.match(reader, readerFactory)) {
				return true;
			}
		}
		return false;
	}

	private void putCache(String className) throws Exception {
		// 从指定的BaseBean类中的注解生成BeanDescriptor，并放入到缓存中。
		@SuppressWarnings("unchecked")
		Class<BaseBean> clazz = (Class<BaseBean>) Class.forName(className);
		BeanDescriptor bd = BeanDescriptor.valueOf(clazz);
		cache.put(className, bd);
	}

	@Override
	public BeanDescriptor put(Class<? extends BaseBean> clazz) {
		String beanClass = clazz.getName();
		if (!cache.containsKey(beanClass)) {
			BeanDescriptor bd = BeanDescriptor.valueOf(clazz);
			if (bd != null) {
				cache.put(beanClass, bd);
				if (LOG.isDebugEnabled()) {
					LOG.debug(String
							.format("Put the BeanDescriptor[%s] into cache.",
									beanClass));
				}
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"The BeanDescriptor from [%s] is null.", beanClass));
				}
			}
		}
		return get(beanClass);
	}

	@Override
	public BeanDescriptor get(Class<? extends BaseBean> clazz) {
		String beanClass = clazz.getName();
		BeanDescriptor bd = get(beanClass);
		if (bd == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String
						.format("The BeanDescriptor[%s] not exist, will be loaded automatically.",
								clazz.getName()));
			}
			bd = put(clazz);
		}
		return bd.clone();
	}

	@Override
	public BeanDescriptor get(String beanClass) {
		if (cache.containsKey(beanClass)) {
			BeanDescriptor bd = cache.get(beanClass);
			return bd.clone();
		} else {
			return null;
		}
	}

}
