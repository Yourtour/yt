package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.PlaceBean;

public interface PlaceBeanRepository extends GraphRepository<PlaceBean> {
	@Query("MATCH (p:PlaceBean{root: true})  RETURN p")
	public List<PlaceBean> getAllRootPlaces();
	
	@Query("START p=node({0}) MATCH p<-[:PARENT]-subplace RETURN subplace")
	public List<PlaceBean> getAllSubPlaces(Long graphId);
	
	@Query("START p=node({0}) MATCH p-[:CHILDREN]->parent-[:CHILDREN]->child RETURN parent, child")
	public List<PlaceTuple> getPlaces(Long graphId);
}
