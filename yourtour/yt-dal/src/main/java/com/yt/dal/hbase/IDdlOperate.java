package com.yt.dal.hbase;

import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * Hbase进行DDL操作（建表、删表、修改表）的接口定义。
 * <p>
 * <b>修改历史:</b>
 * <table border="1">
 * <tr>
 * <th>修改日期</th>
 * <th>修改人</th>
 * <th>描述</th>
 * </tr>
 * <tr>
 * <td>2015年5月12日</td>
 * <td>John Peng</td>
 * <td>初始创建</td>
 * </tr>
 * </table>
 * 
 * @author John Peng
 * 
 * @version 1.0
 * @since 1.0
 */
public interface IDdlOperate {
	/**
	 * 根据传入的表明创建表
	 * 
	 * @param tableName
	 *            表名
	 * @throws Exception
	 *             创建表过程中发生的异常
	 */
	public void createTable(String tableName) throws Exception;

	/**
	 * 根据传入的表名和列族名创建表
	 * 
	 * @param tableName
	 *            表名
	 * @param families
	 *            列族名数组
	 * @throws Exception
	 *             建表过程中发生的异常
	 */
	public void createTable(String tableName, String[] families)
			throws Exception;

	/**
	 * 根据指定类的注解来创建表，该类必须从BaseBean集成，且采用@HbaseTable和@HbaseColumn进行注解。
	 * 
	 * @param clazz
	 *            从BaseBean继承而来的实体类
	 * @throws Exception
	 *             创建表过程中发生的异常
	 * @see {@link #createTable(String)}
	 * @see HbaseTable
	 * @see HbaseColumn
	 */
	public void createTable(Class<? extends BaseBean> clazz) throws Exception;

	/**
	 * 根据指定表名删除表
	 * 
	 * @param tableName
	 *            表名
	 * @throws Exception
	 *             删除表过程中发生的异常
	 */
	public void dropTable(String tableName) throws Exception;

	/**
	 * 根据指定类的注解来删除表，该类必须从BaseBean集成，且采用@HbaseTable进行注解。
	 * 
	 * @param clazz
	 *            从BaseBean继承而来的实体类
	 * @throws Exception
	 *             删除表过程中发生的异常
	 * @see #dropTable(String)
	 * @see HbaseTable
	 */
	public void dropTable(Class<? extends BaseBean> clazz) throws Exception;

	/**
	 * 根据指定的表名判断表是否存在
	 * 
	 * @param tableName
	 *            表名
	 * @return 如果表存在，返回true，否则返回false。
	 * @throws Exception
	 *             判断过程中发生的异常
	 */
	public boolean tableExist(String tableName) throws Exception;

	/**
	 * 根据指定类的注解来判断表是否存在，该类必须从BaseBean集成，且采用@HbaseTable和进行注解。
	 * 
	 * @param clazz
	 *            从BaseBean继承而来的实体类
	 * @return 如果表存在，返回true，否则返回false。
	 * @throws Exception
	 *             判断过程中发生的异常
	 * @see #tableExist(String)
	 */
	public boolean tableExist(Class<? extends BaseBean> clazz) throws Exception;

	public void alterTable(String tableName, String family) throws Exception;

	public void alterTable(Class<? extends BaseBean> clazz) throws Exception;
}
