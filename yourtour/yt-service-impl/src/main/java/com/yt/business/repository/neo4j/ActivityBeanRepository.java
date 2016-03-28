package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.ActivityBean;

public interface ActivityBeanRepository extends GraphRepository<ActivityBean> {
	/**
	 * 获取状态为“发布”的活动列表
	 * 
	 * @return 活动列表
	 */
	@Query("MATCH (a:ActivityBean) WHERE a.status='RELEASED' RETURN a")
	public List<ActivityBean> getReleasedActivities();
}
