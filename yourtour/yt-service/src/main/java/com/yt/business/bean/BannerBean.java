package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.CommentBaseBean;
import com.yt.business.SocialBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_BANNER_INFO")
@NodeEntity
public class BannerBean extends SocialBeanImpl {
	private static final long serialVersionUID = -2639574489334772005L;

	private String title;
	private String imageUrl;
	private String memo;

	public BannerBean() {
	}
}
