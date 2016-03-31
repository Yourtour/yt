package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertContentApplicationBean;
import com.yt.business.bean.ExpertRouteBean;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;

public interface ExpertBeanRepository extends
		GraphRepository<ExpertServiceBean> {
	/**
	 * 获取有效达人数量
	 * 
	 * @return 达人数量
	 */
	@Query("MATCH (expert:UserProfileBean)<-[:BELONG]-(account:UserAccountBean) "
			+ "WHERE account.type = 'EXPERT' AND NOT expert.isDeleted RETURN COUNT(expert)")
	public int countValidatedExperts();

	/**
	 * 获取有效达人的信息列表
	 * 
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("MATCH (expert:UserProfileBean)<-[:BELONG]-(account:UserAccountBean) "
			+ "WHERE account.type = 'EXPERT' AND NOT expert.isDeleted "
			+ "RETURN expert ORDER BY expert.id")
	public List<UserProfileBean> getValidatedExperts(long nextCursor, int limit);

	@Query("START profile=NODE({0}) MATCH profile-[:IS]->expert return expert, profile")
	public ExpertTuple getExpert(Long expertId);

	@Query("START place=node({0}) MATCH place<-[:AT]-(profile:UserProfileBean) return profile")
	public List<ExpertTuple> getExperts(Long[] places);

	/**
	 * 查找目的地提供服务的达人
	 * 
	 * @param place
	 * @param service
	 * @return
	 */
	@Query("start place=node({0}) MATCH (expert:ExpertBean)<-[:IS]-(profile:UserProfileBean)-[:AT]->place return expert, profile")
	public List<ExpertTuple> getExperts(Long[] place, Long[] service);

	/**
	 * 获取指定达人的所有服务信息
	 * 
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[:HAS]->(expertService:ExpertServiceBean) RETURN expertService")
	public List<ExpertServiceBean> getServices(Long[] expertId);

	/**
	 * 获取指定达人的资格申请信息
	 * 
	 * @param userId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[:HAS]->(application:ExpertApplicationBean) RETURN application")
	public ExpertApplicationBean getApplication(Long expertId);

	/**
	 * 获取指定达人的内容审查申请信息列表
	 * 
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[:HAS]->(content:ExpertContentApplicationBean) RETURN content")
	public List<ExpertContentApplicationBean> getContentApplications(
			Long expertId);

	/**
	 * 获取达人推荐行程
	 * 
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[:HAS]->(route:ExpertRouteBean) RETURN route")
	public List<ExpertRouteBean> getRoutes(Long expertId);

	/**
	 * 获取达人提供的服务
	 * 
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[:HAS]->(services:ExpertServiceBean) RETURN services")
	public List<ExpertServiceBean> getServices(Long expertId);

	/**
	 * 获取达人参与的行程
	 * 
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[p:PARTICIPATE]->(routes:RouteMainBean) where p.role='EXPERT' RETURN routes")
	public List<RouteMainBean> getParticipatedRoutes(Long expertId,
			int startIndex, int limits);
}
