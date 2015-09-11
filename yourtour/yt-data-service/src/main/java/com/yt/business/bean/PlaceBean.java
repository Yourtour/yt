package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

/**
 * 该实体定义目的地数据信息。
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
 * <td>2015年6月2日</td>
 * <td>Tony.Zhang</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年6月28日</td>
 * <td>John Peng</td>
 * <td>根据定稿的hbase和neo4j操作进行了修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_SYS_PLACE_INFO")
@NodeEntity
public class PlaceBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -6977525800090683657L;

	private @HbaseColumn(name = "code")
	@Indexed
	String code = ""; // 代码
	private @HbaseColumn(name = "shor")
	@Indexed
	String shorter = ""; // 简称
	private @HbaseColumn(name = "name")
	@Indexed
	String name = ""; // 名称
	private @HbaseColumn(name = "memo")
	@Indexed
	String memo = ""; // 备注
	private @HbaseColumn(name = "recm")
	int recommended = 0; // 是否推荐 0:不推荐 1：推荐
	private @HbaseColumn(name = "stat")
	@Indexed
	Status status;

	private @HbaseColumn(name = "root")
	boolean root = false; // 是否为根节点

	private transient boolean hasChild = false;

	private transient PlaceBean parent = null;

	public PlaceBean() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShorter() {
		return shorter;
	}

	public void setShorter(String shorter) {
		this.shorter = shorter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getRecommended() {
		return recommended;
	}

	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public PlaceBean getParent() {
		return parent;
	}

	public void setParent(PlaceBean parent) {
		this.parent = parent;
	}

	public boolean hasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
}
