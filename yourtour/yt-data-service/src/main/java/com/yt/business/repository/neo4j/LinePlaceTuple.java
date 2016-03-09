package com.yt.business.repository.neo4j;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;

@QueryResult
public class LinePlaceTuple {
	@ResultColumn("line")
	private LineBean line;

	@ResultColumn("place")
	private PlaceBean place;

	public LineBean getLine() {
		return line;
	}

	public void setLine(LineBean line) {
		this.line = line;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}
}