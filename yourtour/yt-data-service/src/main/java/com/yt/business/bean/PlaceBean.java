package com.yt.business.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseDictBeanImpl;
import com.yt.business.common.Constants.Status;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;

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
@HbaseTable(name = "T_PLACE_INFO")
@NodeEntity
public class PlaceBean extends BaseDictBeanImpl {
	private static final long serialVersionUID = -6977525800090683657L;
	
	@HbaseColumn(name = "shor")
	@Indexed
	private String shorter = ""; // 简称
	
	@HbaseColumn(name = "memo")
	private String memo = ""; // 备注

	@HbaseColumn(name = "recm")
	private int recommended = 0; // 是否推荐 0:不推荐 1：推荐

	@HbaseColumn(name = "stat")
	@Indexed
	private Status status;

	@HbaseColumn(name = "root")
	private boolean root = false; // 是否为根节点

	@HbaseColumn(name = "leaf")
	private boolean leaf = true; // 是否为叶子节点

	private transient PlaceBean parent = null;
	
	private transient List<PlaceBean> subs = new ArrayList();

	public PlaceBean() {
		super();
	}

	public String getShorter() {
		return shorter;
	}

	public void setShorter(String shorter) {
		this.shorter = shorter;
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

	public List<PlaceBean> getSubs() {
		return subs;
	}

	public void setSubs(List<PlaceBean> subs) {
		this.subs = subs;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public void addSub(PlaceBean sub){
		this.subs.add(sub);
	}
}
