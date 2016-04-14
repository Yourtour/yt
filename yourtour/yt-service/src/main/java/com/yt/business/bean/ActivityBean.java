package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 系统发布的活动信息，包括：平台活动和达人活动等。
 * 
 * Created by John.Peng on 2016/3/12.
 */

@HbaseTable(name = "T_ACTIVITY_INFO")
@NodeEntity
public class ActivityBean extends ContentBean {
	private static final long serialVersionUID = -5019182758425160992L;

	private Long 	startTime;		//开始时间
	private Long 	endTime;		//结束时间

	public ActivityBean() {
		super();
	}
}
