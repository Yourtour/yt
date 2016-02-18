package com.yt.business.neo4j.repository;

import java.util.List;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.RouteMainBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.PlaceBean;

public interface PlaceBeanRepository extends GraphRepository<PlaceBean> {
	@Query("MATCH (p:PlaceBean{root: true})  RETURN p")
	public List<PlaceBean> getAllRootPlaces();
	
	@Query("START p=node({0}) MATCH p<-[:PARENT]-subplace RETURN subplace")
	public List<PlaceBean> getAllSubPlaces(Long graphId);
	
	@Query("START p=node({0}) MATCH p-[:CHILDREN]->parent-[:CHILDREN]->child RETURN parent, child")
	public List<PlaceTuple> getPlaces(Long rootId);

	@Query("MATCH(p:PlaceBean) <-[:PARENT]-parent<-[:PARENT]-child WHERE p.code={0} RETURN parent, child")
	public List<PlaceTuple> getPlaces(String code);

	@Query("START user=NODE({0}) MATCH user-[:FOLLOWING]->(place:PlaceBean) RETURN place")
	public List<PlaceBean> getRecommendPlaces(Long userId);

	@Query("START source=NODE({0}) MATCH source-[:RECOMMENDED]->(target:PlaceBean) RETURN target")
	public List<PlaceBean> getRelatedPlaces(Long placeId);

	/**
	 * 分页获取目的地达人
	 * @param placeId
	 * @param startIndex
	 * @param limit
	 * @return
	 */
	@Query("START place=NODE({0}) MATCH place<-[:AT]->(profile:UserProfileBean)-[R:IS]-(expert:ExpertBean) RETURN profile, expert ORDER BY R.LEVEL SKIP {1} LIMIT {2}")
	public List<ExpertTuple> getExperts(Long placeId, int startIndex, int limit);

	/**
	 * 分页获取目的地推荐行程
	 * @param placeId
	 * @param startIndex
	 * @param limit
	 * @return
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(user:UserProfileBean)-[:RECOMMEND]->(route:RouteMainBean) RETURN user, route")
	public List<RouteTuple> getRoutes(Long placeId, int startIndex, int limit);
}