package com.yt.business;

import com.yt.neo4j.bean.Neo4jBaseBean;

/**
 * 可以收藏的实体接口
 * Created by 林平 on 2016/3/7.
 */
public interface FavoriteBaseBean extends Neo4jBaseBean {
    public int getFavoriteNum();

    public void setFavoriteNum(int favoriteNum);
}
