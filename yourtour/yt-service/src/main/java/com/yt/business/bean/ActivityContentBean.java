package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by 林平 on 2016/3/26.
 */
@HbaseTable(name = "T_ACTIVITY_CONTENT_INFO")
@NodeEntity
public class ActivityContentBean  extends BaseBeanImpl {
    private String  title; //标题
    private String  subTitle; //副标题
    private String  content; //内容
    private Type    type;
    private String  imageUrl; //图片
    private int     index; //显示位置

    public static enum Type{Title, Text, Image}

    public  ActivityContentBean(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
