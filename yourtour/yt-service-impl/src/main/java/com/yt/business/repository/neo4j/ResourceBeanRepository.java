package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.ResourceBean;

/**
 * Created by 林平 on 2016/2/21.
 */
public interface ResourceBeanRepository extends GraphRepository<ResourceBean> {
	/**
	 * 计数指定目的地的资源数量
	 * 
	 * @param placeId
	 *            目的地ID
	 * @return 资源数量
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(resource:ResourceBean) RETURN COUNT(resource)")
	public int count(Long placeId);

	/**
	 * 计数指定目的地的指定资源类型的资源数量
	 * 
	 * @param placeId
	 *            目的地ID
	 * @param type
	 *            资源类型
	 * @return 资源数量
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(resource:ResourceBean) WHERE resource.type = {1} RETURN COUNT(resource)")
	public int count(Long placeId, String type);

	/**
	 * 计数指定类型的资源数量
	 * 
	 * @param type
	 *            指定的资源类型
	 * @return 资源数量
	 */
	@Query("MATCH (r:ResourceBean) WHERE r.type = {0} RETURN COUNT(r)")
	public int count(String type);

	/**
	 * 分页获取指定类型的资源
	 * 
	 * @param type
	 *            资源类型
	 * @param nextCursor
	 *            开始获取数据的游标
	 * @param limit
	 *            获取数据行数
	 * @return 符合条件的资源列表
	 */
	@Query("MATCH (r:ResourceBean) WHERE r.type={0} RETURN r ORDER BY r.id SKIP {1} LIMIT {2}")
	public List<ResourceBean> getResources(String type, Long nextCursor,
			int limit);

	/**
	 * 分页获取指定目的地的资源
	 * 
	 * @param placeId
	 *            目的地ID
	 * @param nextCursor
	 *            开始获取数据的游标
	 * @param limit
	 *            获取数据行数
	 * @return 符合条件的资源列表
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(resource:ResourceBean) RETURN resource ORDER BY resource.id SKIP {1} LIMIT {2}")
	public List<ResourceBean> getPlaceResources(Long placeId, Long nextCursor,
			int limit);

	/**
	 * 分页获取指定目的地的指定类型资源
	 * 
	 * @param placeId
	 *            目的地ID
	 * @param type
	 *            资源类型
	 * @param nextCursor
	 *            开始获取数据的游标
	 * @param limit
	 *            获取数据行数
	 * @return 符合条件的资源列表
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(resource:ResourceBean) WHERE resource.type = {1} RETURN resource "
			+ "ORDER BY resource.id SKIP {2} LIMIT {3}")
	public List<ResourceBean> getPlaceResources(Long placeId, String type,
			Long nextCursor, int limit);
}
