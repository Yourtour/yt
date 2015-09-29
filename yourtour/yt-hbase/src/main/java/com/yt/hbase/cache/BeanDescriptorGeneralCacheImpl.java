package com.yt.hbase.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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

import com.yt.hbase.BaseBean;
import com.yt.hbase.annotation.HbaseTable;

/**
 * 一个本地的通用设置hbase表描述对象的缓存实现。
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年5月18日</td>
 * <td>john</td>
 * <td>Create</td>
 * </tr>
 * </table>
 * 
 * @author john
 * 
 * @version 1.0
 * @since 1.0
 */
public class BeanDescriptorGeneralCacheImpl implements BeanDescriptorCache,
		InitializingBean {
	private static final Log LOG = LogFactory
			.getLog(BeanDescriptorGeneralCacheImpl.class);
	private final TypeFilter[] ENTITY_TYPE_FILTERS = new TypeFilter[] {
			new AnnotationTypeFilter(HbaseTable.class, false),
			new AssignableTypeFilter(BaseBean.class) };
	private final String RESOURCE_PATTERN = "/**/*.class";

	private String[] beanRepositories;

	private Map<String, BeanDescriptor> cache;

	/**
	 * 默认构造方法
	 */
	public BeanDescriptorGeneralCacheImpl() {
		super();
		this.cache = new HashMap<String, BeanDescriptor>();
	}

	/**
	 * 设置需要扫描的hbase实体类的包列表
	 * 
	 * @param beanRepositories
	 *            包含hbase实体类的包列表
	 */
	public void setBeanRepositories(String[] beanRepositories) {
		this.beanRepositories = beanRepositories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
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

	// 判断是否在预定义的过滤条件中
	private boolean matchesEntityTypeFilter(MetadataReader reader,
			MetadataReaderFactory readerFactory) throws IOException {
		for (TypeFilter filter : ENTITY_TYPE_FILTERS) {
			if (filter.match(reader, readerFactory)) {
				return true;
			}
		}
		return false;
	}

	// 设置缓存
	private void putCache(String className) throws Exception {
		// 从指定的BaseBean类中的注解生成BeanDescriptor，并放入到缓存中。
		@SuppressWarnings("unchecked")
		Class<BaseBean> clazz = (Class<BaseBean>) Class.forName(className);
		BeanDescriptor bd = BeanDescriptor.valueOf(clazz);
		cache.put(className, bd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.cache.IBeanDescriptorCache#put(java.lang.Class)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.cache.IBeanDescriptorCache#get(java.lang.Class)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.cache.IBeanDescriptorCache#get(java.lang.String)
	 */
	@Override
	public BeanDescriptor get(String beanClassName) {
		if (cache.containsKey(beanClassName)) {
			BeanDescriptor bd = cache.get(beanClassName);
			return bd.clone();
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.cache.IBeanDescriptorCache#get()
	 */
	@Override
	public List<BeanDescriptor> get() {
		List<BeanDescriptor> list = new Vector<BeanDescriptor>(cache.size());
		list.addAll(cache.values());
		return list;
	}

}
