package com.yt.business.bean;

import java.util.List;

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
	private String name;
	
	@HbaseColumn(name = "img")
	private String imageUrl; // 图片

	@HbaseColumn(name = "itnt")
	private AlongIntentionType intention; // 结伴目的
	
	@HbaseColumn(name = "dline") //截止期限
	private long deadLine;
	
	@HbaseColumn(name = "gdesc") //团队描述
	private String	groupDesc;
	
	@HbaseColumn(name = "rdesc") //要求描述
	private String	requestDesc;
	
	@HbaseColumn(name = "adesc") //结伴描述
	private String	alongDesc;

	@HbaseColumn(name = "lnla") //经纬度
	private String longLat;
	
	@HbaseColumn(name = "addr") //位置描述
	private String address;
	
	@HbaseColumn(name = "rnum")
	private int readNum; // 阅读数

	@HbaseColumn(name = "cnum")
	private int commentNum; // 评论数

	@HbaseColumn(name = "stat")
	private Status status;
	
	private transient RouteBean route;  //结伴信息关联的行程
	private transient UserBean  publisher;  //结伴信息发布者信息
	private transient List<CommentTuple> comments; //评论信息
	private transient List<UserBean> following; //关注人员
	
	public AlongBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getRequestDesc() {
		return requestDesc;
	}

	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

	public String getAlongDesc() {
		return alongDesc;
	}

	public void setAlongDesc(String alongDesc) {
		this.alongDesc = alongDesc;
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

	public RouteBean getRoute() {
		return route;
	}

	public void setRoute(RouteBean route) {
		this.route = route;
	}

	public UserBean getPublisher() {
		return publisher;
	}

	public void setPublisher(UserBean publisher) {
		this.publisher = publisher;
	}

	public List<CommentTuple> getComments() {
		return comments;
	}

	public void setComments(List<CommentTuple> comments) {
		this.comments = comments;
	}

	public List<UserBean> getFollowing() {
		return following;
	}

	public void setFollowing(List<UserBean> following) {
		this.following = following;
	}
}
