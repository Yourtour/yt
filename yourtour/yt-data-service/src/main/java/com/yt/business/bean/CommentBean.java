package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseDictBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_COMMENT_INFO")
@NodeEntity
public class CommentBean extends BaseDictBeanImpl {
	private static final long serialVersionUID = -2639574489334772005L;

	public CommentBean() {
	}
}
