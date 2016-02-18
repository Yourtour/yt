package com.yt.vo.member;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.vo.BaseVO;

public class ExpertServiceVO extends BaseVO {
    private String title;
    private String fee;
    private String feeIncluding;
    private String feeExcluding;
    private String withdraw;
    private String memo;

    private String category;
    private String imageUrl;

    private double 	commentScore = 0f;
    private int 	commentNum; // 点评数

    private int     goodNum;	//好评
    private int		mediumNum;  //中评
    private int 	badNum;     //差评
    private int		imageNum;   //晒图

    private UserVO user;

    public ExpertServiceVO() {
    }

    public static ExpertServiceBean transform(ExpertServiceVO vo){
        ExpertServiceBean bean = new ExpertServiceBean();
        bean.setGraphId(vo.getId());
        bean.setTitle(vo.getTitle());
        bean.setFee(vo.getFee());
        bean.setMemo(vo.getMemo());
        bean.setWithdraw(vo.getWithdraw());
        bean.setFeeExcluding(vo.getFeeExcluding());
        bean.setFeeIncluding(vo.getFeeIncluding());

        return bean;
    }

    public static ExpertServiceVO transform(ExpertServiceBean bean){
        ExpertServiceVO valueObject = new ExpertServiceVO();

        valueObject.setId(bean.getGraphId());
        valueObject.setFee(bean.getFee());
        valueObject.setTitle(bean.getTitle());
        valueObject.setMemo(bean.getMemo());
        valueObject.setWithdraw(bean.getWithdraw());
        valueObject.setFeeExcluding(bean.getFeeExcluding());
        valueObject.setFeeIncluding(bean.getFeeIncluding());
        valueObject.setImageUrl(bean.getImageUrl());

        if(bean.getUser() != null){
            valueObject.setUser(UserVO.transform(bean.getUser()));
        }
        return valueObject;
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

    public String getFeeIncluding() {
        return feeIncluding;
    }

    public void setFeeIncluding(String feeIncluding) {
        this.feeIncluding = feeIncluding;
    }

    public String getFeeExcluding() {
        return feeExcluding;
    }

    public void setFeeExcluding(String feeExcluding) {
        this.feeExcluding = feeExcluding;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }
}
