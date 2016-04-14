package com.yt.business.bean;

import com.yt.business.common.Constants;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

public class HotPlayingBean extends ContentBean {
	private static final long serialVersionUID = 5630893348353039711L;
	private static final String INDEX_NAME = "hotPlaying";

	public HotPlayingBean() {
		super();
	}
}
