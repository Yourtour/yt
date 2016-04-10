package com.yt.business.repository.neo4j;

import com.yt.business.bean.DictBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface DictBeanRepository extends GraphRepository<DictBean> {
	/**
	 * 获取状态为“发布”的活动列表
	 * 
	 * @return 活动列表
	 */
	@Query("MATCH (bean:DictBean) WHERE bean.type={0} RETURN bean")
	public List<DictBean> getDictInfoes(String type);
}
