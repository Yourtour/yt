package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.HotelResourceBean;

public interface HotelResourceBeanRepository extends
		GraphRepository<HotelResourceBean> {
	/**
	 * 根据GraphId获取指定的宾馆，并关联查询对应的目的地
	 * 
	 * @param graphId
	 *            图ID
	 * @return 已经关联了目的地信息的宾馆
	 */
	@Query("START hotel=node({0}) MATCH hotel-[:AT]->place RETURN hotel, place")
	public HotelResourcePlaceTuple getHotelByGraphId(Long graphId);

	/**
	 * 根据分页要求查询所有的宾馆
	 * 
	 * @param skip
	 *            分页起始记录数
	 * @param limit
	 *            本页最大记录数
	 * @return 该页的宾馆
	 */
	@Query("MATCH (hotel:HotelResourceBean)-[:AT]->place RETURN hotel, place SKIP {0} LIMIT {1}")
	public List<HotelResourcePlaceTuple> getHotelsByPage(long skip, long limit);
}
