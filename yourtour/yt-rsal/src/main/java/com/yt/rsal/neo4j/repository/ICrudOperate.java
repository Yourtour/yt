/** 
 * @(#)ICrudOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.rsal.neo4j.repository;

import java.util.List;

import com.yt.rsal.neo4j.bean.INeo4JBaseBean;

/**
 * 定义了针对Neo4J的CRUD操作的接口。
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
 * <td>2015年6月21日</td>
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
public interface ICrudOperate {

	/**
	 * 获取指定Neo4J实体个数
	 * 
	 * @param clazz
	 *            Neo4J实体类
	 * @return 实体个数
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public long count(Class<? extends INeo4JBaseBean> clazz) throws Exception;

	/**
	 * 获取指定行键的Neo4J实体对象
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @param rowKey
	 *            行键
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public INeo4JBaseBean get(Class<? extends INeo4JBaseBean> clazz,
			String rowKey) throws Exception;

	public List<? extends INeo4JBaseBean> get(
			Class<? extends INeo4JBaseBean> clazz) throws Exception;

	/**
	 * 删除指定行键的Neo4J实体对象。
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @param rowKey
	 *            行键
	 * @throws Exception
	 *             删除过程中发生的异常
	 */
	public void delete(Class<? extends INeo4JBaseBean> clazz, String rowKey)
			throws Exception;

	public void delete(Class<? extends INeo4JBaseBean> clazz) throws Exception;

	/**
	 * 保存一个指定的Neo4J实体对象
	 * 
	 * @param neo4jBean
	 *            Neo4J实体对象
	 * @return 保存后的实体对象
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public INeo4JBaseBean save(INeo4JBaseBean neo4jBean) throws Exception;

	/**
	 * 保存一个指定的Neo4J实体对象
	 * 
	 * @param neo4jBean
	 *            Neo4J实体对象
	 * @param saveFail2Hbase
	 *            如果设置为true，加入保存过程中失败，将会在hbase中保存一个Neo4JSaveFailRecordBean对象，
	 *            便于后续补救处理。
	 * @return 保存后的实体对象
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public INeo4JBaseBean save(INeo4JBaseBean neo4jBean, boolean saveFail2Hbase)
			throws Exception;
}
