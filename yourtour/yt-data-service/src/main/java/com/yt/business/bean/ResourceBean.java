package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseDictBeanImpl;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.ResType;
import com.yt.business.common.Constants.Status;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.neo4j.annotation.Neo4jRelationship;

import java.util.List;

/**
 * 资源bean，定义了各类资源的公共信息，不直接创建表，被后续其他资源对象继承（如：景点、宾馆、饭店等）
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
 * <td>2015年7月29日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和neo4j的操作模式进行修改完善，并抽象为一个基类。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@NodeEntity
public class ResourceBean extends BaseDictBeanImpl {
	private static final long serialVersionUID = -8980153602025087935L;

	@HbaseColumn(name = "img")
	private String imageUrl; // 图片

	@HbaseColumn(name = "type")
	private ResType type; // 类型

	@HbaseColumn(name = "otime")
	private String openTime; // 开放时间 hh24:mi

	@HbaseColumn(name = "tintr")
	private String trafficIntro; // 公交信息

	private String price;

	private String currency;

	@HbaseColumn(name = "pay")
	private String payment; // 支付信息

	@HbaseColumn(name = "star")
	private int star; // 星级

	@HbaseColumn(name = "memb")
	private boolean member; // 是否会员

	@HbaseColumn(name = "tele")
	private String phone; // 联系电话

	@HbaseColumn(name = "addr")
	private String address; // 地址

	@HbaseColumn(name = "www")
	private String website; // 网址

	@HbaseColumn(name = "pos")
	private String position; // 位置信息

	@HbaseColumn(name = "pc")
	private String postCode; // 邮编

	@HbaseColumn(name = "anum")
	private int arriveNum; // 到达人数

	@HbaseColumn(name = "cscore")
	private double commentScore; // 点评分数

	@HbaseColumn(name = "cnum")
	private int commentNum; // 点评数

	@HbaseColumn(name = "fnum")
	private int favoriteNum; // 收藏数

	@HbaseColumn(name = "snum")
	private int shareNum; // 分享数

	private float rankScore;

	@HbaseColumn(name = "bmemo")
	private String bookingMemo; // 预订须知

	@HbaseColumn(name = "tips")
	private String tips; // 贴士

	@HbaseColumn(name = "stat")
	private Status status;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean place = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RECOMMEND, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean user = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = ActivityItemBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<ActivityItemBean> activities = null;

	public ResourceBean() {
		super();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ResType getType() {
		return type;
	}

	public void setType(ResType type) {
		this.type = type;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getTrafficIntro() {
		return trafficIntro;
	}

	public void setTrafficIntro(String trafficIntro) {
		this.trafficIntro = trafficIntro;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getArriveNum() {
		return arriveNum;
	}

	public void setArriveNum(int arriveNum) {
		this.arriveNum = arriveNum;
	}

	public double getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(double commentScore) {
		this.commentScore = commentScore;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getShareNum() {
		return shareNum;
	}

	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
	}

	public String getBookingMemo() {
		return bookingMemo;
	}

	public void setBookingMemo(String bookingMemo) {
		this.bookingMemo = bookingMemo;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public float getRankScore() {
		return rankScore;
	}

	public void setRankScore(float rankScore) {
		this.rankScore = rankScore;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}

	public List<ActivityItemBean> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityItemBean> activities) {
		this.activities = activities;
	}
}
