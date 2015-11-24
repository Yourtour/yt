package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

@HbaseTable(name = "T_ROUTE_PROVISION")
@NodeEntity
public class RouteProvisionBean extends BaseBeanImpl {

	private static final long serialVersionUID = 5125188883896673886L;
	private static final String INDEX_NAME = "routeProvision";

	@HbaseColumn(name = "title")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String title; // 行程准备名称

	@HbaseColumn(name = "memo")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String memo; // 行程准备备注

	@HbaseColumn(name = "idx")
	private int index = 1; // 行程准备排序号

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.INCOMING)
	private transient RouteMainBean routeMain = null; // 行程准备关联的行程

	public RouteProvisionBean() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public RouteMainBean getRouteMain() {
		return routeMain;
	}

	public void setRouteMain(RouteMainBean routeMain) {
		this.routeMain = routeMain;
	}

}
