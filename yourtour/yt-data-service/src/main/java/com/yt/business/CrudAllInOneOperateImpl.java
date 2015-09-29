package com.yt.business;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.hbase.BaseBean;
import com.yt.neo4j.bean.Neo4jBaseBean;
import com.yt.neo4j.repository.CrudGeneralOperate;

@Component
public class CrudAllInOneOperateImpl extends CrudGeneralOperate implements
		CrudAllInOneOperate {
	private static final Log LOG = LogFactory
			.getLog(CrudAllInOneOperateImpl.class);

	@Autowired
	private com.yt.hbase.CrudOperate hbaseCrud;

	private boolean save2Hbase = true; // 是否同时保存到Hbase中，默认为true。

	/**
	 * 默认的构造函数
	 */
	public CrudAllInOneOperateImpl() {
		super();
	}

	/**
	 * 默认的构造函数
	 * 
	 * @param save2Hbase
	 *            设置为true表示同时保存到Hbase中，否则仅仅保存在Neo4J中。
	 */
	public CrudAllInOneOperateImpl(boolean save2Hbase) {
		this();
		this.setSave2Hbase(save2Hbase);
	}

	/**
	 * 设置是否同时保存到Hbase中
	 * 
	 * @param save2Hbase
	 *            设置为true表示同时保存到Hbase中，否则仅仅保存在Neo4J中。
	 */
	public void setSave2Hbase(boolean save2Hbase) {
		this.save2Hbase = save2Hbase;
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("The flag is set, save2Hbase = %s",
					String.valueOf(this.save2Hbase)));
		}
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

		if (save2Hbase && bean instanceof BaseBean) {
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

		if (save2Hbase && clazz.isAssignableFrom(BaseBean.class)) {
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
		if (save2Hbase && neo4jBean instanceof BaseBean) {
			BaseBean hbaseBean = (BaseBean) neo4jBean;
			this.saveRow(hbaseBean);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#saveRow(com.yt.hbase.BaseBean)
	 */
	@Override
	public void saveRow(BaseBean bean) throws Exception {
		hbaseCrud.saveRow(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#saveRows(java.util.List)
	 */
	@Override
	public void saveRows(List<? extends BaseBean> beans) throws Exception {
		hbaseCrud.saveRows(beans);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#deleteRow(com.yt.hbase.BaseBean)
	 */
	@Override
	public void deleteRow(BaseBean bean) throws Exception {
		hbaseCrud.deleteRow(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#deleteRows(java.lang.Class)
	 */
	@Override
	public void deleteRows(Class<? extends BaseBean> clazz) throws Exception {
		hbaseCrud.deleteRows(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRow(java.lang.String, java.lang.String)
	 */
	@Override
	public BaseBean getRow(String className, String rowkey) throws Exception {
		return hbaseCrud.getRow(className, rowkey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRow(java.lang.Class, java.lang.String)
	 */
	@Override
	public BaseBean getRow(Class<? extends BaseBean> clazz, String rowKey)
			throws Exception {
		return hbaseCrud.getRow(clazz, rowKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRows(java.lang.Class)
	 */
	@Override
	public List<? extends BaseBean> getRows(Class<? extends BaseBean> clazz)
			throws Exception {
		return hbaseCrud.getRows(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.CrudOperate#getRows(java.lang.Class, long, long)
	 */
	@Override
	public List<? extends BaseBean> getRows(Class<? extends BaseBean> clazz,
			long ts1, long ts2) throws Exception {
		return hbaseCrud.getRows(clazz, ts1, ts2);
	}

}
