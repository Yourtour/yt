package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 社区主题对象
 * Created by 林平 on 2016/2/25.
 */
@HbaseTable(name = "T_COMM_THEME_INFO")
@NodeEntity
public class ThemeBean  extends BaseBeanImpl {
    private String  title; //题目
    private String  memo;  //内容
    private String  images; //图片
    private String  address; //发布地址

    private Constants.ThemeType  type;  //主题类型,
    private int     thumbupNum; //点赞数
    private int     readNum;    //阅读数
    private int     favoriteNum; //关注数
    private int     replyNum;   //回复数

    @Neo4jRelationship(relationship=Constants.RELATION_TYPE_PUBLISH, type = UserProfileBean.class, direction = Direction.OUTGOING)
    private UserProfileBean user = null;

    @Neo4jRelationship(relationship=Constants.RELATION_TYPE_BELONG, type = PlaceBean.class, direction = Direction.OUTGOING)
    private PlaceBean place;

    public ThemeBean(){}

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Constants.ThemeType getType() {
        return type;
    }

    public void setType(Constants.ThemeType type) {
        this.type = type;
    }

    public int getThumbupNum() {
        return thumbupNum;
    }

    public void setThumbupNum(int thumbupNum) {
        this.thumbupNum = thumbupNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(int favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public UserProfileBean getUser() {
        return user;
    }

    public void setUser(UserProfileBean user) {
        this.user = user;
    }

    public PlaceBean getPlace() {
        return place;
    }

    public void setPlace(PlaceBean place) {
        this.place = place;
    }
}
