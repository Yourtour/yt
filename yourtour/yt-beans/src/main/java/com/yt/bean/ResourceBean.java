package com.yt.bean;

import com.yt.common.Constants.ResType;
import com.yt.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * @author Tony.Zhang
 * 
 * 资源bean，定义了资源信息
 *
 */
@HbaseTable(name = "T_RESOURCE_INFO")
public class ResourceBean extends BaseBean {
	private static final long serialVersionUID = -8980153602025087935L;
	
	private 	@HbaseColumn(name = "name")				String 	name; 	//名称
	private 	@HbaseColumn(name = "intr")				String 	intro; 	//简介
	private 	@HbaseColumn(name = "url")			String 	imageUrl; 	//图片
	private 	@HbaseColumn(name = "type")			ResType 	type; 	//类型 
	private 	@HbaseColumn(name = "otime")				String 	openTime; 	//开发时间 hh24:mi
	private 	@HbaseColumn(name = "ctime")				String 	closeTime; 	//关闭时间 hh24:mi
	private 	@HbaseColumn(name = "pintr")				String 	priceIntro; 	//价格信息
	private 	@HbaseColumn(name = "tintr")				String 	trafficeIntro; 	//公交信息
	private 	@HbaseColumn(name = "star")			int  star; 	//星级 
	private	@HbaseColumn(name = "memb")			int  member; 	//是否会员
	private	@HbaseColumn(name = "phone")			String phone; 	//联系电话
	private	@HbaseColumn(name = "addr")			String address; 	//地址
	private	@HbaseColumn(name = "pos")			String position; 	//位置信息
	private	@HbaseColumn(name = "pc")			String postCode; 	//邮编
	private	@HbaseColumn(name = "anum")			int arriveNum; 	//到达人数
	private	@HbaseColumn(name = "cscore")			int commentScore; 	//点评分数
	private	@HbaseColumn(name = "cnum")			int commentNum; 	//点评数
	private	@HbaseColumn(name = "fnum")			int favoriteNum; 	//收藏数
	private	@HbaseColumn(name = "snum")			int shareNum; 	//分享数
	private 	@HbaseColumn(name = "bmemo")			String bookingMemo; 	//预订须知
	private 	@HbaseColumn(name = "tips")			String tips; 	//贴士
	private 	@HbaseColumn(name = "cuid")			String createdUserId = "";
	private 	@HbaseColumn(name = "ct")				long createdTime;
	private 	@HbaseColumn(name = "uuid")			String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")				long updatedTime;
	private 	@HbaseColumn(name = "stat")			Status	status;
	
	public ResourceBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public String getPriceIntro() {
		return priceIntro;
	}

	public void setPriceIntro(String priceIntro) {
		this.priceIntro = priceIntro;
	}

	public String getTrafficeIntro() {
		return trafficeIntro;
	}

	public void setTrafficeIntro(String trafficeIntro) {
		this.trafficeIntro = trafficeIntro;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
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

	public int getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(int commentScore) {
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

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
