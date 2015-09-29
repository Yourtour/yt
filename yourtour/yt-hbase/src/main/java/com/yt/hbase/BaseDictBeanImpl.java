package com.yt.hbase;

import com.yt.hbase.annotation.HbaseColumn;

/**
 * hbase中字典类实体的抽象类，定义了常规字典类实体必须包含的数据内容。
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
 * <td>2015年5月28日</td>
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
public abstract class BaseDictBeanImpl extends BaseBeanImpl implements
		BaseDictBean {
	private static final long serialVersionUID = 9059455163441467114L;

	@HbaseColumn(name = "code")
	private String code = "";

	@HbaseColumn(name = "name")
	private String name = "";

	/**
	 * 默认构造方法
	 */
	public BaseDictBeanImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseDictBean#getCode()
	 */
	@Override
	public String getCode() {
		return code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseDictBean#setCode(java.lang.String)
	 */
	@Override
	public void setCode(String code) {
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseDictBean#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseDictBean#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

}
