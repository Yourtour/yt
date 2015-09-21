package com.yt.business.neo4j.repository;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.PlaceBean;

@QueryResult
public class HotelResourcePlaceTuple {
	@ResultColumn("hotel")
	private HotelResourceBean hotel;

	@ResultColumn("place")
	private PlaceBean place;

	public HotelResourceBean getHotel() {
		return hotel;
	}

	public void setHotel(HotelResourceBean hotel) {
		this.hotel = hotel;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}
}