package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_HOTPLAYING_INFO")
@NodeEntity
public class HotPlayingBean extends ContentBean {
	private static final long serialVersionUID = 5630893348353039711L;
	private static final String INDEX_NAME = "hotPlaying";

	public HotPlayingBean() {
		super();
	}
}
