package com.yt.business.bean;

import java.util.List;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.ResType;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

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
public class ResourceBean extends SocialBeanImpl {
	private static final long serialVersionUID = -8980153602025087935L;

	private String code;

	private String name;

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

	private int     goodNum;	//好评
	private int		mediumNum;  //中评
	private int 	badNum;     //差评
	private int		imageNum;   //晒图

	private double   healthScore = 0d;

	private double   trafficScore = 0d;

	private	double	facilityScore = 0d;

	private double	environmentScore = 0d;

	private double  serviceScore = 0d;

	@HbaseColumn(name = "bmemo")
	private String bookingMemo; // 预订须知

	@HbaseColumn(name = "tips")
	private String tips; // 贴士

	private String tags;

	private String feature;

	@HbaseColumn(name = "intr")
	private String intro; // 简介

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean place = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RECOMMEND, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean user = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = ResourceActivityItemBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<ResourceActivityItemBean> activities = null;

	public ResourceBean() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}

	public List<ResourceActivityItemBean> getActivities() {
		return activities;
	}

	public void setActivities(List<ResourceActivityItemBean> activities) {
		this.activities = activities;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getHealthScore() {
		return healthScore;
	}

	public void setHealthScore(double healthScore) {
		this.healthScore = healthScore;
	}

	public double getTrafficScore() {
		return trafficScore;
	}

	public void setTrafficScore(double trafficScore) {
		this.trafficScore = trafficScore;
	}

	public double getFacilityScore() {
		return facilityScore;
	}

	public void setFacilityScore(double facilityScore) {
		this.facilityScore = facilityScore;
	}

	public double getEnvironmentScore() {
		return environmentScore;
	}

	public void setEnvironmentScore(double environmentScore) {
		this.environmentScore = environmentScore;
	}

	public double getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(double serviceScore) {
		this.serviceScore = serviceScore;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	public int getMediumNum() {
		return mediumNum;
	}

	public void setMediumNum(int mediumNum) {
		this.mediumNum = mediumNum;
	}

	public int getBadNum() {
		return badNum;
	}

	public void setBadNum(int badNum) {
		this.badNum = badNum;
	}

	public int getImageNum() {
		return imageNum;
	}

	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
