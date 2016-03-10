package com.yt.business.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

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
public class PlaceBean extends SocialBeanImpl {
	private static final long serialVersionUID = -6977525800090683657L;
	
	@HbaseColumn(name = "shor")
	@Indexed
	private String shorter = ""; // 简称

	private String name;
	private String code;
	
	private String imageUrl;

	private boolean home; //是否国内
	
	@HbaseColumn(name = "memo")
	private String memo = ""; // 备注

	@HbaseColumn(name = "recm")
	private int recommended = 0; // 是否推荐 0:不推荐 1：推荐

	@HbaseColumn(name = "root")
	private boolean root = false; // 是否为根节点

	@HbaseColumn(name = "leaf")
	private boolean leaf = true; // 是否为叶子节点

	private int goneNum = 0;  //去过人数

	private int goingNum = 0;  //想去人数

	private int alongNum = 0; //结伴条数

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_PARENT, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean parent = null;
	
	private transient List<PlaceBean> subs = new ArrayList<PlaceBean>();

	private transient List<? extends ResourceBean> resources = null; //关联游玩资源

	public PlaceBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShorter() {
		return shorter;
	}

	public void setShorter(String shorter) {
		this.shorter = shorter;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public int getGoneNum() {
		return goneNum;
	}

	public void setGoneNum(int goneNum) {
		this.goneNum = goneNum;
	}

	public int getGoingNum() {
		return goingNum;
	}

	public void setGoingNum(int goingNum) {
		this.goingNum = goingNum;
	}

	public int getAlongNum() {
		return alongNum;
	}

	public void setAlongNum(int alongNum) {
		this.alongNum = alongNum;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<? extends ResourceBean> getResources() {
		return resources;
	}

	public void setResources(List<? extends ResourceBean> resources) {
		this.resources = resources;
	}

	@Override
	public int hashCode() {
		return super.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(obj == this) return true;
		
		PlaceBean target =(PlaceBean) obj;
		return target.getId().equals(super.getId());
	}
}
