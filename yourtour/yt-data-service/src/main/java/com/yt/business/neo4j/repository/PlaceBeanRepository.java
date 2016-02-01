package com.yt.business.neo4j.repository;

import java.util.List;

import com.yt.business.bean.LineBean;
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

	@Query("START source=NODE({0}) MATCH source-[:RELATED]->(target:PlaceBean) RETURN target")
	public List<PlaceBean> getRelatedPlaces(Long placeId);

	@Query("START place=NODE({0}) MATCH place<-[:AT]->(profile:UserProfileBean)-[:IS]->(expert:ExpertBean) RETURN profile, expert skip {1} limit {2}")
	public List<ExpertTuple> getExperts(Long placeId, int startIndex, int limit);

	@Query("START place=NODE({0}) MATCH (line:LineBean)-[:AT]->place RETURN line skip {1} limit {2}")
	public List<LineBean> getLines(Long placeId, int startIndex, int limit);
}