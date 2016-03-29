package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.BannerBean;

/**
 * 旅游资讯相关的数据查询接口定义。
 * 
 * @author John.Peng
 * 
 */
public interface BannerBeanRepository extends GraphRepository<BannerBean> {
	/**
	 * 获取当前有效（有效期前且发布）的点评分数前n名资讯
	 * 
	 * @param time
	 *            指定的时间
	 * @param n
	 *            前n名
	 * @return 符合条件的推荐资讯
	 */
	@Query("MATCH (b:BannerBean) "
			+ "WHERE b.status = 'RELEASED' AND b.endTime < {0} RETURN b AND NOT b.isDeleted "
			+ "ORDER BY b.commentScore DESC LIMIT {1}")
	public List<BannerBean> getRecommendBanners(long time, int n);

	/**
	 * 分页获取所有的Banner信息
	 * 
	 * @param nextCursor
	 *            当前开始游标
	 * @param limit
	 *            获取信息条数
	 * @return 符合条件的Banner信息列表
	 */
	@Query("MATCH (b:BannerBean) WHERE NOT b.isDeleted RETURN b ORDER BY b.startTime DESC SKIP {0} LIMIT {1}")
	public List<BannerBean> getBanners(Long nextCursor, int limit);
}
