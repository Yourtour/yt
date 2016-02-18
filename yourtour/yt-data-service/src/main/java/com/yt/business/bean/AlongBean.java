package com.yt.business.bean;

import java.util.List;

import com.yt.business.common.Constants;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants.AlongIntentionType;
import com.yt.business.common.Constants.Status;
import com.yt.business.neo4j.repository.CommentTuple;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;

/**
 * 该实体定义了系统中的行程的结伴信息。结伴信息和行程以及结伴发布者之间的关系通过图状数据库Neo4j存储
 * 
 * @author Tony.Zhang
 * 
 */
@HbaseTable(name = "T_ROUTE_ALONG_INFO")
@NodeEntity
public class AlongBean extends BaseBeanImpl {
	private static final long serialVersionUID = -3433522673262851121L;

	@HbaseColumn(name = "name")
	private String title;

	@HbaseColumn(name = "img")
	private String imageUrls; // 图片

	@HbaseColumn(name = "itnt")
	private AlongIntentionType intention; // 结伴目的

	@HbaseColumn(name = "dline")
	// 截止期限
	private long deadLine;

	private int  num;

	@HbaseColumn(name = "rdesc")
	// 要求描述
	private String memo;

	@HbaseColumn(name = "lnla")
	// 经纬度
	private String longLat;

	@HbaseColumn(name = "addr")
	// 位置描述
	private String address;

	@HbaseColumn(name = "rnum")
	private int readNum; // 浏览数

	@HbaseColumn(name = "cnum")
	private int commentNum; // 评论数

	private int followedNum; //关注人数

	private int applyNum;  //报名人数

	@HbaseColumn(name = "stat")
	private Status status;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_BELONG, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean publisher; // 结伴信息发布者信息

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_BELONG, type = RouteMainBean.class, direction = Direction.OUTGOING)
	private transient  RouteMainBean route;

	public AlongBean() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public AlongIntentionType getIntention() {
		return intention;
	}

	public void setIntention(AlongIntentionType intention) {
		this.intention = intention;
	}

	public long getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(long deadLine) {
		this.deadLine = deadLine;
	}

	public String getLongLat() {
		return longLat;
	}

	public void setLongLat(String longLat) {
		this.longLat = longLat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getFollowedNum() {
		return followedNum;
	}

	public void setFollowedNum(int followedNum) {
		this.followedNum = followedNum;
	}

	public int getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}

	public UserProfileBean getPublisher() {
		return publisher;
	}

	public void setPublisher(UserProfileBean publisher) {
		this.publisher = publisher;
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}
}
