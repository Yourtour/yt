package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;
import com.yt.business.neo4j.repository.UserTuple;

import java.util.List;

public interface ExpertRepository extends CrudAllInOneOperate {
	/**
	 *
	 * @param placeIds
	 * @param duration
	 * @return
	 * @throws Exception
	 */
	public List<ExpertBean> getExperts(String placeIds, String duration) throws Exception;

	/**
	 * 达人提供的服务
	 * @param expertId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<ExpertServiceBean> getServices(Long expertId, Long nextCursor, int limit) throws Exception;

	/**
	 * 达人推荐的服务
	 * @param expertId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRecommendRoutes(Long expertId, Long nextCursor, int limit) throws Exception;

	/**
	 * 达人服务的行程
	 * @param expertId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getServicedRoutes(Long expertId, Long nextCursor, int limit) throws Exception;

	/**
	 *
 	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ExpertApplicationBean getApplication(Long userId) throws Exception;

	/**
	 *
	 * @param application
	 */
	public void saveApplication(ExpertApplicationBean application,String operator) throws Exception;

	/**
	 *
	 * @param approvement
	 */
	public void saveApprovement(ExpertApprovementBean approvement,String operator) throws Exception;
}