package com.yt.hbase;

import java.io.Serializable;

import com.yt.hbase.annotation.HbaseColumn;

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
public abstract class BaseBeanImpl implements Serializable, BaseBean,
		Comparable<BaseBean> {
	private static final long serialVersionUID = -1098345715801304322L;

	private String rowKey;

	@HbaseColumn(name = "cuid")
	private String createdUserId = "";

	@HbaseColumn(name = "ct")
	private long createdTime;

	@HbaseColumn(name = "uuid")
	private String updatedUserId = "";

	@HbaseColumn(name = "ut")
	private long updatedTime;

	/**
	 * 默认构造方法
	 */
	public BaseBeanImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getRowKey()
	 */
	@Override
	public String getRowKey() {
		return rowKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setRowKey(java.lang.String)
	 */
	@Override
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getCreatedUserId()
	 */
	@Override
	public String getCreatedUserId() {
		return createdUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setCreatedUserId(java.lang.String)
	 */
	@Override
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getCreatedTime()
	 */
	@Override
	public long getCreatedTime() {
		return createdTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setCreatedTime(long)
	 */
	@Override
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getUpdatedUserId()
	 */
	@Override
	public String getUpdatedUserId() {
		return updatedUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setUpdatedUserId(java.lang.String)
	 */
	@Override
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getUpdatedTime()
	 */
	@Override
	public long getUpdatedTime() {
		return updatedTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setUpdatedTime(long)
	 */
	@Override
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	/*
	 * (non-Javadoc)
	 * 
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
			return -tar.compareTo(src);
		}
	}

}
