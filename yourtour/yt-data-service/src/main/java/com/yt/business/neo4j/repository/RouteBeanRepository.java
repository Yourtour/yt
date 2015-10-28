package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.RouteMainBean;

public interface RouteBeanRepository extends GraphRepository<RouteMainBean> {
	/**
	 * 根据指定的线路目的地和景点名称，查询符合条件的线路。
	 * 
	 * @param place
	 *            　指定的线路目的地列表
	 * @param min
	 *            　行程最小时间（单位秒）
	 * @param max
	 *            行程最大时间（单位秒）
	 * @param scene
	 *            指定的景点列表
	 * @return 符合条件的线路
	 */
	@Query("MATCH (line:LineBean)-[:RELATED]->(route) WHERE line.place IN {0} AND route.period >= {1} AND route.period <= {2} RETURN line "
			+ "UNION "
			+ "MATCH (scene:SceneResourceBean)<-[:CONTAIN]-(route)-[:RELATED]->(line) WHERE scene.name IN {3}"
			+ "AND route.period >= {1} AND route.period <= {2} RETURN line")
	public List<LineBean> query(String[] place, long min, long max,
			String[] scene);

	/**
	 * 根据指定的线路目的地和景点ID列表，查询符合条件的线路。<br>
	 * 景点ID列表在前面逻辑中采用全文检索的方式获取。
	 * 
	 * @param place
	 *            指定的线路目的地
	 * @param min
	 *            最小时间（单位秒）
	 * @param max
	 *            行程最大时间（单位秒）
	 * @param sceneIds
	 *            景点ID列表
	 * @return 符合条件的线路
	 */
	@Query("MATCH (line:LineBean)-[:RELATED]->(route) WHERE line.place IN {0} AND route.period >= {1} AND route.period <= {2} RETURN line "
			+ "UNION "
			+ "START scene=node({3}) MATCH (scene)<-[:CONTAIN]-(route)-[:RELATED]->(line) WHERE  route.period >= {1} AND route.period <= {2} RETURN line")
	public List<LineBean> query(String[] place, long min, long max,
			long[] sceneIds);
}
