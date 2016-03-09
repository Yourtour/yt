package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;

public interface LineBeanRepository extends GraphRepository<LineBean> {
	/**
	 * 根据指定的线路ID，获取该线路中包括的景点
	 * 
	 * @param lineId
	 *            线路ID
	 * @return 线路包含的景点
	 */
	@Query("START line=node({0}) MATCH line-[:CONTAIN]->(scene:SceneResourceBean) return scene")
	public List<SceneResourceBean> getScenesByLine(Long lineId);

	/**
	 * 根据指定的线路ID，获取该线路中包括的宾馆
	 * 
	 * @param lineId
	 *            线路ID
	 * @return 线路包含的宾馆
	 */
	@Query("START line=node({0}) MATCH line-[:CONTAIN]->(hotel:HotelResourceBean) return hotel")
	public List<HotelResourceBean> getHotelsByLine(Long lineId);

	/**
	 * 根据指定的线路ID，获取该线路中包括的饭店
	 * 
	 * @param lineId
	 *            线路ID
	 * @return 线路包含的饭店
	 */
	@Query("START line=node({0}) MATCH line-[:CONTAIN]->(restaurant:RestaurantResourceBean) return restaurant")
	public List<RestaurantResourceBean> getRestaurantsByLine(Long lineId);
	
	/**
	 * 根据目的地在图中查询线路数据
	 * @param placeId
	 * @return
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(line:LineBean) return line")
	public List<LineBean> getLinesByPlace(Long placeId);
}
