package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_DISCOVER_INFO")
@NodeEntity
public class DiscoverBean extends ContentBean {
	private static final long serialVersionUID = 5630893348353039711L;
	private static final String INDEX_NAME = "discover";

	public DiscoverBean() {
		super();
		super.setCategory(ContentCategory.DISCOVER);
	}
}
