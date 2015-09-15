package com.yt.business.bean;

import com.yt.business.common.Constants.ResType;
import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

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
public class ResourceBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -8980153602025087935L;

	private @HbaseColumn(name = "img")
	String imageUrl; // 图片
	private @HbaseColumn(name = "type")
	ResType type; // 类型
	private @HbaseColumn(name = "otime")
	String openTime; // 开放时间 hh24:mi
	private @HbaseColumn(name = "ctime")
	String closeTime; // 关闭时间 hh24:mi
	private @HbaseColumn(name = "tintr")
	String trafficIntro; // 公交信息
	private @HbaseColumn(name = "pay")
	String payment; // 支付信息
	private @HbaseColumn(name = "star")
	int star; // 星级
	private @HbaseColumn(name = "memb")
	boolean member; // 是否会员
	private @HbaseColumn(name = "tele")
	String phone; // 联系电话
	private @HbaseColumn(name = "addr")
	String address; // 地址
	private @HbaseColumn(name = "www")
	String website; // 网址
	private @HbaseColumn(name = "pos")
	String position; // 位置信息
	private @HbaseColumn(name = "pc")
	String postCode; // 邮编
	private @HbaseColumn(name = "anum")
	int arriveNum; // 到达人数
	private @HbaseColumn(name = "cscore")
	double commentScore; // 点评分数
	private @HbaseColumn(name = "cnum")
	int commentNum; // 点评数
	private @HbaseColumn(name = "fnum")
	int favoriteNum; // 收藏数
	private @HbaseColumn(name = "snum")
	int shareNum; // 分享数
	private @HbaseColumn(name = "bmemo")
	String bookingMemo; // 预订须知
	private @HbaseColumn(name = "tips")
	String tips; // 贴士
	private @HbaseColumn(name = "stat")
	Status status;

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

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
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
}
