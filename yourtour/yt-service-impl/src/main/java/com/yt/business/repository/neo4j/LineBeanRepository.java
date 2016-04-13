package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;

public interface LineBeanRepository extends GraphRepository<LineBean> {
	/**
	 * 根据指定的线路ID，获取该线路中包括的指定类型的资源
	 * 
	 * @param lineId
	 *            线路ID
	 * @param type
	 *            资源类型
	 * @return 线路中包含的指定类型资源
	 */
	@Query("START line=node({0}) MATCH line-[:CONTAIN]->(resource:ResourceBean) WHERE resource.type={1} return resource")
	public List<ResourceBean> getResourceByType(Long lineId, ResourceType type);

	/**
	 * 根据目的地在图中查询线路数据
	 * 
	 * @param placeId
	 * @return
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(line:LineBean) return line")
	public List<LineBean> getLinesByPlace(Long placeId);
}
