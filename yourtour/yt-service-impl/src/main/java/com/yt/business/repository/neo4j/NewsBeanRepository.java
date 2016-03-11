package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.NewsBean;

/**
 * 旅游资讯相关的数据查询接口定义。
 * 
 * @author John.Peng
 * 
 */
public interface NewsBeanRepository extends GraphRepository<NewsBean> {
	/**
	 * 获取当前有效（有效期前且发布）的点评分数前n名资讯
	 * 
	 * @param time
	 *            指定的时间
	 * @param n
	 *            前n名
	 * @return 符合条件的推荐资讯
	 */
	@Query("MATCH (n:NewsBean) "
			+ "WHERE n.status = 'RELEASED' AND n.endTime < {0} RETURN n "
			+ "ORDER BY n.commentScore DESC LIMIT {1}")
	public List<NewsBean> getRecommendNews(long time, int n);
}
