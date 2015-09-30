package com.yt.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.hbase.CrudOperate;

public class CrudAllInOneConfig {
	private static final Log LOG = LogFactory.getLog(CrudAllInOneConfig.class);

	private CrudOperate hbaseCrud;

	private boolean save2Hbase = true; // 是否同时保存到Hbase中，默认为true。

	public CrudAllInOneConfig() {
		super();
	}

	/**
	 * 设置hbase操作类
	 * 
	 * @param hbaseCrud
	 *            hbase操作类
	 */
	public void setHbaseCrud(CrudOperate hbaseCrud) {
		this.hbaseCrud = hbaseCrud;
	}

	/**
	 * 返回hbase操作类
	 * 
	 * @return hbase操作类
	 */
	public CrudOperate getHbaseCrud() {
		return hbaseCrud;
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

	/**
	 * 返回是否保存到hbase中
	 * 
	 * @return 返回true表示同时保存到Hbase中，否则仅仅保存在Neo4J中。
	 */
	public boolean isSave2Hbase() {
		return save2Hbase;
	}
}
