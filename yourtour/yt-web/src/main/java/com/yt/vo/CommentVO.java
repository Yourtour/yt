package com.yt.vo;

import com.yt.business.bean.CommentBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.member.UserProfileVO;
import com.yt.vo.member.UserVO;

import java.io.Serializable;

public class CommentVO extends BaseVO{
	private double 	score = 0d;
	private double   healthScore = 0d;
	private double   trafficScore = 0d;
	private	double	facilityScore = 0d;
	private double	environmentScore = 0d;

	private String 	memo;
	private String 	imageUrls;
	private int		thumbup = 0;
	private String	replied = "";
	private String	tags;
	private String 	createdDate;
	private UserVO user;
	private int recommendedIndex = 0;

	public CommentVO() {
		super();
	}

	public static CommentVO transform(CommentBean bean){
		CommentVO comment = new CommentVO();

		comment.setImageUrls(bean.getImageUrls());
		comment.setMemo(bean.getMemo());
		comment.setReplied(bean.getReplied());
		comment.setScore(bean.getScore());
		comment.setHealthScore(bean.getHealthScore());
		comment.setEnvironmentScore(bean.getEnvironmentScore());
		comment.setFacilityScore(bean.getFacilityScore());
		comment.setTrafficScore(bean.getTrafficScore());
		comment.setTags(bean.getTags());
		comment.setThumbup(bean.getThumbup());
		comment.setCreatedDate(DateUtils.formatDate(bean.getCreatedTime()));
		comment.setRecommendedIndex(bean.getRecommendedIndex());
		comment.setUser(UserVO.transform(bean.getUser()));


		return comment;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImageUrls() {
		return imageUrls == null ? "" : imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getThumbup() {
		return thumbup;
	}

	public void setThumbup(int thumbup) {
		this.thumbup = thumbup;
	}

	public String getReplied() {
		return replied;
	}

	public void setReplied(String replied) {
		this.replied = replied;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getRecommendedIndex() {
		return recommendedIndex;
	}

	public void setRecommendedIndex(int recommendedIndex) {
		this.recommendedIndex = recommendedIndex;
	}

	public boolean isImage(){
		return ! this.getImageUrls().equals("");
	}

	public boolean isGood(){
		return this.score >= 4;
	}

	public boolean isMedium(){
		return this.score >= 3 && this.score < 4;
	}

	public boolean isBad(){
		return this.score < 3;
	}
}
