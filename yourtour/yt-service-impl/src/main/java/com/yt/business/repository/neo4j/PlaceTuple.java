package com.yt.business.repository.neo4j;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.PlaceBean;

@QueryResult
public class PlaceTuple {
	@ResultColumn("parentPlace")
	private PlaceBean parentPlace;

	@ResultColumn("place")
	private PlaceBean place;

	public PlaceTuple() {
	}

	public PlaceBean getParentPlace() {
		return parentPlace;
	}

	public void setParentPlace(PlaceBean parentPlace) {
		this.parentPlace = parentPlace;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

}