package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.LineBean;

public interface LineBeanRepository extends GraphRepository<LineBean> {
	/**
	 * 根据分页要求查询所有的线路
	 * 
	 * @param skip
	 *            分页起始记录数
	 * @param limit
	 *            本页最大记录数
	 * @return 该页的线路
	 */
	@Query("MATCH (lines:LineBean) RETURN lines SKIP {0} LIMIT {1}")
	public List<LineBean> getLinesByPage(long skip, long limit);
}
