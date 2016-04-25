package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.ContentBean;

public interface ContentBeanRepository extends GraphRepository<ContentBean> {
	/**
	 * 获取类型未游徒推荐并发布的内容
	 * 
	 * @return 游徒推荐列表
	 */
	@Query("MATCH (c:ContentBean) WHERE c.status='RELEASED' AND c.category = 'YT_RECOMMEND' "
			+ "RETURN c ORDER BY c.commentScore DESC LIMIT {0}")
	public List<ContentBean> getRecommendContents(int limit);
}
