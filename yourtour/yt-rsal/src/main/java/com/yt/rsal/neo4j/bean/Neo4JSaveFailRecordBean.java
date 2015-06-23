package com.yt.rsal.neo4j.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 在进行Neo4J保存操作时失败时，描述相关失败Bean关键数据的实体对象。
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
 * <td>2015年6月9日</td>
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
@HbaseTable(name = "T_NEO4J_FAIL_RECORD")
public class Neo4JSaveFailRecordBean extends BaseBean {
	private static final long serialVersionUID = -1008019799410035917L;

	/**
	 * Neo4J中的操作类型，增、删、改。
	 */
	public enum OperateType {
		INSERT("insert", "插入"), UPDATE("update", "更新"), DELETE("delete", "删除");

		public String code;
		public String name;

		private OperateType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	@HbaseColumn(name = "cn")
	private String className;
	@HbaseColumn(name = "ot")
	private OperateType operateType = OperateType.INSERT;
	@HbaseColumn(name = "key")
	private String relatedRowkey;
	@HbaseColumn(name = "ut")
	private long updateTime;

	/**
	 * 默认构造方法
	 */
	public Neo4JSaveFailRecordBean() {
		super();
	}

	/**
	 * 获取操作对应的hbase实体类名
	 * 
	 * @return hbase实体类名
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * 设置操作对应的hbase实体类名
	 * 
	 * @param className
	 *            实体类名
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 获取操作对应类型
	 * 
	 * @return 操作类型
	 * @see OperateType
	 */
	public OperateType getOperateType() {
		return this.operateType;
	}

	/**
	 * 设置操作对应类型
	 * 
	 * @param operateType
	 *            操作类型
	 * @see OperateType
	 */
	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}

	/**
	 * 获取操作对应的关联行键
	 * 
	 * @return 行键
	 */
	public String getRelatedRowkey() {
		return this.relatedRowkey;
	}

	/**
	 * 设置操作对应的关联行键
	 * 
	 * @param relatedRowkey
	 *            行键
	 */
	public void setRelatedRowkey(String relatedRowkey) {
		this.relatedRowkey = relatedRowkey;
	}

	/**
	 * 获取操作成功时的时间
	 * 
	 * @return 更新时间
	 */
	public long getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置操作成功时的时间，仅仅在操作成功后更新
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

}