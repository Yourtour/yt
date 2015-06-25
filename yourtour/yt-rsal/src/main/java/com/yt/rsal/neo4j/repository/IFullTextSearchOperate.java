/** 
 * @(#)IFullTextSearchOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.rsal.neo4j.repository;

import java.util.List;

import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

/**
 * 定义了基于Neo4j的全文检索的接口方法。
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
 * <td>2015年6月25日</td>
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
public interface IFullTextSearchOperate {

	public class QueryTerm {
		public String key = "", value = "*";

		public QueryTerm(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * 根据指定的全文检索条件在指定的实体上进行全文检索，只能使用一组条件。
	 * 
	 * @param clazz
	 *            指定的实体类
	 * @param term
	 *            指定的一组条件
	 * @return 满足条件的实体对象集合
	 * @throws Exception
	 *             查询过程中发生的异常
	 */
	public List<Neo4JBaseBean> query(Class<? extends Neo4JBaseBean> clazz,
			QueryTerm term) throws Exception;

	/**
	 * 根据指定的全文检索条件在指定的实体上进行全文检索，可以使用多组条件。
	 * 
	 * @param clazz
	 *            指定的实体类
	 * @param terms
	 *            检索条件集合
	 * @return 满足条件的实体对象集合
	 * @throws Exception
	 *             查询过程中发生的异常
	 */
	public List<Neo4JBaseBean> query(Class<? extends Neo4JBaseBean> clazz,
			List<QueryTerm> terms) throws Exception;

}
