package com.yt.business.repository.neo4j;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.PlaceBean;

@QueryResult
public class PlaceTuple {
	@ResultColumn("parent")
	private PlaceBean parent;

	@ResultColumn("child")
	private PlaceBean child;
	
	public PlaceTuple(){
	}

	public PlaceBean getParent() {
		return parent;
	}

	public void setParent(PlaceBean parent) {
		this.parent = parent;
	}

	public PlaceBean getChild() {
		return child;
	}

	public void setChild(PlaceBean child) {
		this.child = child;
	}
	
	
}