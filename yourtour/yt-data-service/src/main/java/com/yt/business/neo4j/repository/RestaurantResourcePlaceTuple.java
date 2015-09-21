package com.yt.business.neo4j.repository;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RestaurantResourceBean;

@QueryResult
public class RestaurantResourcePlaceTuple {
	@ResultColumn("hotel")
	private RestaurantResourceBean restaurant;

	@ResultColumn("place")
	private PlaceBean place;

	public RestaurantResourceBean getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantResourceBean restaurant) {
		this.restaurant = restaurant;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}
}