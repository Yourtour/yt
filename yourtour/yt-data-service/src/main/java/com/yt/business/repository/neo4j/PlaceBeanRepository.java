package com.yt.business.repository.neo4j;

import java.util.List;

import com.yt.business.bean.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PlaceBeanRepository extends GraphRepository<PlaceBean> {
	@Query("START p=node({0}) MATCH p<-[:PARENT]-subplace RETURN subplace")
	public List<PlaceBean> getAllSubPlaces(Long graphId);
	
	@Query("MATCH(p:PlaceBean) <-[:PARENT]-parent<-[:PARENT]-child WHERE p.code={0} RETURN parent, child")
	public List<PlaceTuple> getPlaces(String code);

	@Query("START source=NODE({0}) MATCH source-[:RECOMMENDED]->(target:PlaceBean) RETURN target")
	public List<PlaceBean> getRelatedPlaces(Long placeId);

	/**
	 * 分页获取目的地景点资源
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START place=NODE({0}) MATCH place<-[:AT]->(resource:SceneResourceBean) RETURN resource")
	public List<SceneResourceBean> getSceneResoruces(Long placeId, Long nextCursor, int limit);

	/**
	 * 分页获取目的地达人
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START place=NODE({0}) MATCH place<-[:AT]->(profile:UserProfileBean)-[R:IS]-(expert:ExpertBean) RETURN profile, expert ORDER BY R.LEVEL SKIP {1} LIMIT {2}")
	public List<ExpertTuple> getExperts(Long placeId, Long nextCursor, int limit);

	/**
	 * 分页获取目的地推荐行程
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(user:UserProfileBean)-[:RECOMMEND]->(route:RouteMainBean) RETURN user, route")
	public List<RouteTuple> getRoutes(Long placeId, Long nextCursor, int limit);
}