package com.yt.business.repository.neo4j;

import java.util.List;

import com.yt.business.bean.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PlaceBeanRepository extends GraphRepository<PlaceBean> {
	/**
	 * 获取所有目的地
	 * @return
	 */
	@Query("MATCH (place:PlaceBean) RETURN place")
	public List<PlaceBean> getAllPlaces();

	/**
	 * 获取所有推荐的目的地
	 * 
	 * @return 目的地列表
	 */
	@Query("MATCH (p:PlaceBean) WHERE p.recommend=true RETURN p")
	public List<PlaceBean> getRecommendPlaces();

	/**
	 * 分页获取目的地达人
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START place=NODE({0}) MATCH place<-[:AT]->(profile:UserProfileBean)-[R:IS]-(expert:ExpertBean) RETURN profile, expert ORDER BY R.LEVEL SKIP {1} LIMIT {2}")
	public List<ExpertTuple> getExperts(Long placeId, Long nextCursor, int limit);

	/**
	 * 分页获取目的地游玩资源
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(resource:ResourceBean) RETURN resource ORDER BY resource.star SKIP {1} LIMIT {2}")
	public List<ResourceBean> getResources(Long placeId, Long nextCursor,
			int limit);

	/**
	 * 分页获取目的地推荐行程
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(user:UserProfileBean)-[:RECOMMEND]->(route:RouteMainBean) RETURN user, route")
	public List<RouteTuple> getRoutes(Long placeId, Long nextCursor, int limit);
}