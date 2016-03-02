package com.yt.vo;

import java.io.Serializable;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.common.Constants;
import com.yt.core.utils.StringUtils;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.vo.member.UserVO;
import com.yt.vo.route.RouteVO;

public class AlongVO extends BaseVO{
	private String title;
	private String imageUrls; // 图片
	private String intention; // 结伴目的
	private long deadLine;
	private int  num;
	private String memo;
	private String longLat;
	private String address;
	private int readNum; // 浏览数
	private int commentNum; // 评论数
	private int favoriteNum; //关注人数
	private int applyNum;  //报名人数
	private UserVO user;
	private RouteVO route;

	public static AlongVO transform(AlongBean along){
		AlongVO vo = new AlongVO();

		vo.fromBean(along);
		vo.setTitle(along.getTitle());
		vo.setAddress(along.getAddress());
		vo.setMemo(along.getMemo());
		vo.setDeadLine(along.getDeadLine());
		vo.setApplyNum(along.getApplyNum());
		vo.setNum(along.getNum());
		vo.setReadNum(along.getReadNum());
		vo.setFavoriteNum(along.getFavoriteNum());
		vo.setImageUrls(along.getImageUrls());
		vo.setCommentNum(along.getCommentNum());
		vo.setIntention(along.getIntention().name);
		vo.setLongLat(along.getLongLat());
		vo.setRoute(RouteVO.transform(along.getRoute()));
		vo.setUser(UserVO.transform(along.getPublisher()));
		return vo;
	}

	public static void transform(AlongVO vo, AlongBean bean){
		bean.setTitle(vo.getTitle());
		bean.setAddress(vo.getAddress());
		bean.setMemo(vo.getMemo());
		bean.setDeadLine(vo.getDeadLine());
		bean.setNum(bean.getNum());
		bean.setImageUrls(bean.getImageUrls());
		bean.setIntention(Constants.AlongIntentionType.valueOf(vo.getIntention()));
		bean.setLongLat(bean.getLongLat());
	};

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

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getIntentionName(){
		Constants.AlongIntentionType type = Constants.AlongIntentionType.get(this.intention);
		return type == null ? "" : type.name;
	}

	public String getIntentionCode(){
		Constants.AlongIntentionType type = Constants.AlongIntentionType.get(this.intention);
		return type == null ? "" : type.code;
	}

	public String getIntention() {
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}

	public long getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(long deadLine) {
		this.deadLine = deadLine;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public RouteVO getRoute() {
		return route;
	}

	public void setRoute(RouteVO route) {
		this.route = route;
	}
}
