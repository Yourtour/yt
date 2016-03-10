package com.yt.business;

import com.yt.neo4j.Neo4jBaseBean;

/**
 * 可以点评的实体接口
 * Created by 林平 on 2016/3/7.
 */
public interface CommentBaseBean extends Neo4jBaseBean {
    public int getCommentNum();

    public void setCommentNum(int commentNum);
}
