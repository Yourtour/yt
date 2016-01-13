package com.yt.neo4j.bean;

import org.springframework.data.neo4j.annotation.Indexed;

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
public abstract class Neo4jBaseDictBeanImpl extends Neo4jBaseBeanImpl implements
		Neo4jBaseDictBean {
	private static final long serialVersionUID = 9059455163441467114L;

	@Indexed(unique = true)
	private String code = "";

	@Indexed
	private String name = "";

	/**
	 * 默认构造方法
	 */
	public Neo4jBaseDictBeanImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseDictBean#getCode()
	 */
	@Override
	public String getCode() {
		return code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseDictBean#setCode(java.lang.String)
	 */
	@Override
	public void setCode(String code) {
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseDictBean#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseDictBean#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.bean.Neo4jBaseBeanImpl#compareTo(com.yt.neo4j.bean.Neo4jBaseBean
	 * )
	 */
	@Override
	public int compareTo(Neo4jBaseBean o) {
		if (o == null) {
			return 1;
		}
		String src = this.getCode();
		String tar = ((Neo4jBaseDictBean) o).getCode();
		if (src == null && tar == null) {
			return 0;
		} else if (src != null) {
			return src.compareTo(tar);
		} else {
			return -tar.compareTo(src);
		}
	}
}
