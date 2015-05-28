package com.yt.dal.hbase;

import java.io.Serializable;

/**
 * hbase实体类的基类，所有hbase实体类均应该从BaseBean继承实现。
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
 * <td>2015年5月16日</td>
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
public abstract class BaseBean implements Serializable {
	private static final long serialVersionUID = -1098345715801304322L;
	private String rowKey;

	/**
	 * 获取hbase实体对象的行键
	 * @return hbase实体对象的行键
	 */
	public String getRowKey() {
		return rowKey;
	}

	/**
	 * 设置hbase实体对象的行键
	 * @param rowKey  hbase实体对象的行键
	 */
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

}
