package com.yt.business.repository.neo4j;

import com.yt.business.FavoriteBaseBean;
import com.yt.business.bean.AlongBean;
import com.yt.business.bean.FavoriteBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@QueryResult
public class FavoriteTuple {
	@ResultColumn("subject")
	private FavoriteBaseBean subject;

	@ResultColumn("favorite")
	private FavoriteBean favorite;

	public FavoriteTuple(){
	}

	public FavoriteBaseBean getSubject() {
		return subject;
	}

	public void setSubject(FavoriteBaseBean subject) {
		this.subject = subject;
	}

	public FavoriteBean getFavorite() {
		return favorite;
	}

	public void setFavorite(FavoriteBean favorite) {
		this.favorite = favorite;
	}
}