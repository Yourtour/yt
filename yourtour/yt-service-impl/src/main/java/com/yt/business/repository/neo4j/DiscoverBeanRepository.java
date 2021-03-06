package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.DiscoverBean;

/**
 * 大家都在玩相关的数据查询接口定义。
 * 
 * @author John.Peng
 * 
 */
public interface DiscoverBeanRepository extends
		GraphRepository<DiscoverBean> {
	/**
	 * 获取点评分数前n名大家都在玩
	 * 
	 * @param n
	 *            前n名
	 * @return 符合条件的推荐大家都在玩
	 */
	@Query("MATCH (n:DiscoverBean) "
			+ "RETURN n ORDER BY n.commentScore DESC LIMIT {1}")
	public List<DiscoverBean> getRecommendDiscovers(int n);
}
