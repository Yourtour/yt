package com.yt.dal;

import java.util.List;

public interface ICrudOperate<T> {
	public T getBean(Object id) throws Exception;

	public List<T> getBeans() throws Exception;

	public T deleteBean(Object id, Object operatorId) throws Exception;

	public T saveBean(T bean, Object operatorId) throws Exception;
}
