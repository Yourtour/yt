package com.yt.business.repository.neo4j;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.SceneResourceBean;

@QueryResult
public class SceneResourcePlaceTuple {
	@ResultColumn("scene")
	private SceneResourceBean scene;

	@ResultColumn("place")
	private PlaceBean place;

	public SceneResourceBean getScene() {
		return scene;
	}

	public void setScene(SceneResourceBean scene) {
		this.scene = scene;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}
}