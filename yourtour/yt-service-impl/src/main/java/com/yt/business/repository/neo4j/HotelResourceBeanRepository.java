package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.HotelResourceBean;

public interface HotelResourceBeanRepository extends
		GraphRepository<HotelResourceBean> {
	/**
	 * 根据指定的目的地ID，获取该目的地下的所有宾馆
	 * 
	 * @param placeId
	 *            目的地ID
	 * @return 已经关联了目的地信息的宾馆列表
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(hotel:HotelResourceBean) RETURN hotel, place")
	public List<HotelResourcePlaceTuple> getHotelByPlace(Long placeId);
}
