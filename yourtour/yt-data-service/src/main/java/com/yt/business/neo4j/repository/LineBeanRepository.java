package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;

public interface LineBeanRepository extends GraphRepository<LineBean> {
	/**
	 * 删除指定的线路跟景点之间的关系
	 * 
	 * @param lineId
	 *            线路ID
	 */
	@Query("START line=node({0}) MATCH line-[r:CONTAIN]->(s:SceneResourceBean) DELETE r")
	public void deleteLine2SceneRelationship(Long lineId);

	/**
	 * 删除指定的线路跟宾馆之间的关系
	 * 
	 * @param lineId
	 *            线路ID
	 */
	@Query("START line=node({0}) MATCH line-[r:CONTAIN]->(s:HotelResourceBean) DELETE r")
	public void deleteLine2HotelRelationship(Long lineId);

	/**
	 * 删除指定的线路跟饭店之间的关系
	 * 
	 * @param lineId
	 *            线路ID
	 */
	@Query("START line=node({0}) MATCH line-[r:CONTAIN]->(s:RestaurantResourceBean) DELETE r")
	public void deleteLine2RestaurantRelationship(Long lineId);

	/**
	 * 根据GraphId获取指定的线路，并关联查询对应的目的地
	 * 
	 * @param graphId
	 *            图ID
	 * @return 已经关联了目的地信息的线路
	 */
	@Query("START line=node({0}) MATCH line-[:AT]->place RETURN line, place")
	public LinePlaceTuple getLineByGraphId(Long graphId);

	/**
	 * 根据分页要求查询所有的线路
	 * 
	 * @param skip
	 *            分页起始记录数
	 * @param limit
	 *            本页最大记录数
	 * @return 该页的线路
	 */
	@Query("MATCH (line:LineBean)-[:AT]->place RETURN line, place SKIP {0} LIMIT {1}")
	public List<LinePlaceTuple> getLinesByPage(long skip, long limit);

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
