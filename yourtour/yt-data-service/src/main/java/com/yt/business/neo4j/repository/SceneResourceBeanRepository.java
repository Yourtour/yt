package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.SceneResourceBean;

public interface SceneResourceBeanRepository extends
		GraphRepository<SceneResourceBean> {
	/**
	 * 根据GraphId获取指定的景点，并关联查询对应的目的地
	 * 
	 * @param graphId
	 *            图ID
	 * @return 已经关联了目的地信息的景点
	 */
	@Query("START scene=node({0}) MATCH scene-[:AT]->place RETURN scene, place")
	public SceneResourcePlaceTuple getSceneByGraphId(Long graphId);

	/**
	 * 根据指定的目的地ID，获取该目的地下的所有景点
	 * 
	 * @param placeId
	 *            目的地ID
	 * @return 已经关联了目的地信息的景点列表
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(scene:SceneResourceBean) RETURN scene, place")
	public List<SceneResourcePlaceTuple> getSceneByPlace(Long placeId);

	/**
	 * 根据分页要求查询所有的景点
	 * 
	 * @param skip
	 *            分页起始记录数
	 * @param limit
	 *            本页最大记录数
	 * @return 该页的景点
	 */
	@Query("MATCH (scene:SceneResourceBean)-[:AT]->place RETURN scene, place SKIP {0} LIMIT {1}")
	public List<SceneResourcePlaceTuple> getScenesByPage(long skip, long limit);

	/**
	 * 在指定的线路节点中根据指定范围的天数，找出相关的线路
	 * 
	 * @param lineIds
	 *            线路节点ID，采用逗号分割的字符串
	 * @param minPeriodSecond
	 *            最小的行程天数（单位为秒）
	 * @param maxPeriodSecond
	 *            最大的行程天数（单位为秒）
	 * @return 符合条件行程的关联线路
	 */
	// TODO 关联查询出目的地
	@Query("START line=node({0}) MATCH line-[:RELATED]->(route) WHERE route.period >= {1} AND route.period <= {2} RETURN distinct(line)")
	public List<LineBean> findLineByLineName(String lineIds,
			int minPeriodSecond, int maxPeriodSecond);

	/**
	 * 根据指定的景点ID，在图中搜索相关联的满足条件的行程关联的线路。
	 * 
	 * @param sceneIds
	 *            景点I D
	 * @param minPeriodSecond
	 *            行程最小天数（单位秒）
	 * @param maxPeriodSecond
	 *            行程最大天数（单位秒）
	 * @return 符合条件的线路
	 */
	// TODO 关联查询出目的地
	@Query("START scene=node({0}) MATCH scene<-[:contain]-(route)->[:RELATED]-(line) WHERE route.period >= {1} AND route.period <= {2} RETURN distinct(line)")
	public List<LineBean> findLineBySceneName(String sceneIds,
			int minPeriodSecond, int maxPeriodSecond);
}
