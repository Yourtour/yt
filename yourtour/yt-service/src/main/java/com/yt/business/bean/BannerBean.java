package com.yt.business.bean;

import com.yt.business.BusinessBeanImpl;
import com.yt.business.CommentBaseBean;
import com.yt.hbase.annotation.HbaseTable;
import org.springframework.data.neo4j.annotation.NodeEntity;

@HbaseTable(name = "T_BANNER_INFO")
@NodeEntity
public class BannerBean extends BusinessBeanImpl {
	private static final long serialVersionUID = -2639574489334772005L;

	private String title;
	private String imageUrl;
	private String memo;

	public BannerBean() {
	}
}
