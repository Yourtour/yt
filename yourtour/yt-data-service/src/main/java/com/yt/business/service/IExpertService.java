package com.yt.business.service;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;

import java.util.List;

public interface IExpertService extends CrudAllInOneOperate {
	/**
	 * 获取目的地提供指定服务的达人
	 * @param placeIds
	 * @param services
	 * @return
	 * @throws Exception
	 */
	public List<ExpertBean> getExperts(String placeIds,String services) throws Exception;

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