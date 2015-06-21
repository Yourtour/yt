/** 
 * @(#)IBaseBean.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.dal.hbase;


/**
 * 定义hbase实体类的基本接口，仅仅包括了rowKey的操作定义。
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
public interface IBaseBean {

	/**
	 * 获取hbase实体对象的行键
	 * @return hbase实体对象的行键
	 */
	public abstract String getRowKey();

	/**
	 * 设置hbase实体对象的行键
	 * @param rowKey  hbase实体对象的行键
	 */
	public void setRowKey(String rowKey);

}