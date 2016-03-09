package com.yt.business.repository.neo4j;

import com.yt.business.bean.AlongBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface FavoriteBeanRepository extends GraphRepository<FavoriteTuple> {
	/**
	 * 获取用户收藏信息
	 * @param userid
	 * @param startIndex
	 * @param size
	 * @return
	 */
	@Query("START place=node({0}) MATCH (place:PlaceBean)<-[:TO]-(route:RouteMainBean)<-[:BELONG]-(along:AlongBean)-[:BELONG]->(user:UserProfileBean) return along, route, user")
	public List<FavoriteTuple> getFavorites(Long userid, Long startIndex, int size);
}
