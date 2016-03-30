package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

public class HotPlayingBean extends SocialBeanImpl {
	private static final long serialVersionUID = 5630893348353039711L;
	private static final String INDEX_NAME = "hotPlaying";

	private String imageUrl;
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String feature;
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String content;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean place;
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean expert;

	public HotPlayingBean() {
		super();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public UserProfileBean getExpert() {
		return expert;
	}

	public void setExpert(UserProfileBean expert) {
		this.expert = expert;
	}

}
