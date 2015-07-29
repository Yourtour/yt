package com.yt.business.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.RouteBean;

public interface RouteBeanRepository extends GraphRepository<RouteBean> {
	/**
	 * 在指定的行程节点中根据指定范围的天数，找出相关的线路
	 * 
	 * @param nodeIds
	 *            行程节点ID，采用逗号分割的字符串
	 * @param minDayNum
	 *            最小的行程天数
	 * @param maxDayNum
	 *            最大的行程天数
	 * @return 符合条件行程的关联线路
	 */
	@Query("START r=node({0}) MATCH u<-[:associated]-(lines) WHERE u.dayNum >= {1} AND u.dayNum <= {2} RETURN lines")
	public List<LineBean> findTheDayNumLine(String nodeIds, int minDayNum,
			int maxDayNum);
}
