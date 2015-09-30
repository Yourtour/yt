package com.yt.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yt.hbase.BaseBean;
import com.yt.neo4j.bean.Neo4jBaseBean;
import com.yt.neo4j.repository.CrudGeneralOperate;

public class CrudAllInOneOperateImpl extends CrudGeneralOperate implements
		CrudAllInOneOperate {

	@Autowired
	private CrudAllInOneConfig config;

	/**
	 * 默认的构造函数
	 */
	public CrudAllInOneOperateImpl() {
		super();
	}

	private boolean save2Hbase() {
		if (config == null) {
			return false;
		}
		return config.isSave2Hbase();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudGeneralOperate#delete(com.yt.neo4j.bean.
	 * Neo4jBaseBean)
	 */
	@Override
	public void delete(Neo4jBaseBean bean) throws Exception {
		super.delete(bean);

		if (save2Hbase() && bean instanceof BaseBean) {
			BaseBean hbaseBean = (BaseBean) bean;
			this.deleteRow(hbaseBean);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudGeneralOperate#delete(java.lang.Class)
	 */
	@Override
	public void delete(Class<? extends Neo4jBaseBean> clazz) throws Exception {
		super.delete(clazz);

		if (save2Hbase() && clazz.isAssignableFrom(BaseBean.class)) {
			@SuppressWarnings("unchecked")
			Class<? extends BaseBean> c = (Class<? extends BaseBean>) clazz;
			this.deleteRows(c);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudGeneralOperate#save(com.yt.neo4j.bean.
	 * Neo4jBaseBean, java.lang.String)
	 */
	@Override
	public void save(Neo4jBaseBean neo4jBean, String operator) throws Exception {
		super.save(neo4jBean, operator);
		if (save2Hbase() && neo4jBean instanceof BaseBean) {
			BaseBean hbaseBean = (BaseBean) neo4jBean;
			this.saveRow(hbaseBean);
		}
	}

	private void checkHbaseCrud() throws Exception {
		if (config == null || config.getHbaseCrud() == null) {
			throw new Exception("The Hbase CRUD Operate is null!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#saveRow(com.yt.hbase.BaseBean)
	 */
	@Override
	public void saveRow(BaseBean bean) throws Exception {
		checkHbaseCrud();
		config.getHbaseCrud().saveRow(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#saveRows(java.util.List)
	 */
	@Override
	public void saveRows(List<? extends BaseBean> beans) throws Exception {
		checkHbaseCrud();
		config.getHbaseCrud().saveRows(beans);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#deleteRow(com.yt.hbase.BaseBean)
	 */
	@Override
	public void deleteRow(BaseBean bean) throws Exception {
		checkHbaseCrud();
		config.getHbaseCrud().deleteRow(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#deleteRows(java.lang.Class)
	 */
	@Override
	public void deleteRows(Class<? extends BaseBean> clazz) throws Exception {
		checkHbaseCrud();
		config.getHbaseCrud().deleteRows(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRow(java.lang.String, java.lang.String)
	 */
	@Override
	public BaseBean getRow(String className, String rowkey) throws Exception {
		checkHbaseCrud();
		return config.getHbaseCrud().getRow(className, rowkey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRow(java.lang.Class, java.lang.String)
	 */
	@Override
	public BaseBean getRow(Class<? extends BaseBean> clazz, String rowKey)
			throws Exception {
		checkHbaseCrud();
		return config.getHbaseCrud().getRow(clazz, rowKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRows(java.lang.Class)
	 */
	@Override
	public List<? extends BaseBean> getRows(Class<? extends BaseBean> clazz)
			throws Exception {
		checkHbaseCrud();
		return config.getHbaseCrud().getRows(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRows(java.lang.Class, long, long)
	 */
	@Override
	public List<? extends BaseBean> getRows(Class<? extends BaseBean> clazz,
			long ts1, long ts2) throws Exception {
		checkHbaseCrud();
		return config.getHbaseCrud().getRows(clazz, ts1, ts2);
	}

}
