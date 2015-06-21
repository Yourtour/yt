/** 
 * @(#)INeo4jBaseBean.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.rsal.neo4j.bean;

/**
 * Neo4J的实体类的接口定义，定义了访问GraphId的方法。
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
public interface INeo4JBaseBean {

	/**
	 * 获取Neo4J实体在图中的唯一ID，该ID被Neo4J在内部自动维护。
	 * 
	 * @return 实体对应的ID，如果ID为null，则表示该实体没有被Attach。
	 */
	public abstract Long getGraphId();

}