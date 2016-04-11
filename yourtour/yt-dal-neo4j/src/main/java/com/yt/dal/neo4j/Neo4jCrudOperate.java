package com.yt.dal.neo4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yt.dal.ICrudOperate;
import com.yt.dal.bean.AbstractBaseBean;

@Repository
public class Neo4jCrudOperate<T> implements ICrudOperate<T> {

	@Autowired
	private Neo4jSessionFactory factory;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public Neo4jCrudOperate() {
		super();
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		clazz = (Class<T>) params[0];
	}

	@Override
	public T getBean(Object id) throws Exception {
		Session session = factory.openSession();
		return session.load(clazz, (Long) id);
	}

	@Override
	public List<T> getBeans() throws Exception {
		Session session = factory.openSession();
		Collection<T> result = session.loadAll(clazz);
		List<T> list = new Vector<T>();
		if (!result.isEmpty()) {
			list.addAll(result);
		}
		return list;
	}

	@Override
	public T deleteBean(Object id, Object operatorId) throws Exception {
		T entity = this.getBean(id);
		if (entity != null) {
			return this.saveBean(entity, operatorId);
		}
		return entity;
	}

	@Override
	public T saveBean(T bean, Object operatorId) throws Exception {
		Session session = factory.openSession();
		if (bean instanceof AbstractBaseBean) {
			AbstractBaseBean entity = (AbstractBaseBean)bean;
			if (entity.getId() == null || ((long)entity.getId()) == -1l) {
				// new entity
				entity.setCreatedTime(new Date().getTime());
				entity.setCreatedUserId(operatorId);
			} else {
				// update
				entity.setUpdatedTime(new Date().getTime());
				entity.setUpdatedUserId(operatorId);
			}
		}
		session.save(bean);
		return bean;
	}

}
