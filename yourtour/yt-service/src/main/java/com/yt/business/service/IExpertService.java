package com.yt.business.service;

/**
 * 达人接口
 */

import com.yt.business.bean.*;

import java.util.List;

public interface IExpertService {
	/**
	 * 达人申请
	 * @param application
	 */
	public void saveApplication(ExpertApplicationBean application, Long userId) throws Exception;

	/**
	 * 大人说申请审批
	 * @param approvement
	 */
	public void saveApprovement(Long applicationId, ExpertApprovementBean approvement, Long userId) throws Exception;

	/**
	 * 获取申请信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ExpertApplicationBean getApplication(Long userId) throws Exception;

	/**
	 * 获取目的地提供指定服务的达人
	 * @param placeIds
	 * @param services
	 * @return
	 * @throws Exception
	 */
	public List<ExpertBean> getExperts(String placeIds,String services) throws Exception;

	/**
	 * 获取达人信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ExpertBean getExpert(Long userId) throws Exception;


	/**
	 * 达人推荐的行程
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
}