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
public abstract class BaseBean implements Serializable, IBaseBean,
		Comparable<BaseBean> {
	private static final long serialVersionUID = -1098345715801304322L;
	private String rowKey;

	/**
	 * 默认构造方法
	 */
	public BaseBean() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IBaseBean#getRowKey()
	 */
	@Override
	public String getRowKey() {
		return rowKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IBaseBean#setRowKey(java.lang.String)
	 */
	@Override
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BaseBean o) {
		if (o == null) {
			return 1;
		}
		String src = this.getRowKey();
		String tar = o.getRowKey();
		if (src == null && tar == null) {
			return 0;
		} else if (src != null) {
			return src.compareTo(tar);
		} else {
			return - tar.compareTo(src);
		}
	}
	
}
