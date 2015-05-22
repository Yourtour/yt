package com.yt.dal.hbase.cache;

import com.yt.dal.hbase.BaseBean;

public interface IBeanDescriptorCache {
	public BeanDescriptor put(Class<? extends BaseBean> clazz);
	
	public BeanDescriptor get(Class<? extends BaseBean> clazz);
	
	public BeanDescriptor get(String beanClass);
}
