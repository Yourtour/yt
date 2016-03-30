package com.yt.business.service;

/**
 * 达人接口
 */

import java.util.List;

import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;

public interface IExpertService {
	/**
	 * 达人资格审核申请
	 * @param application
	 */
	public void saveApplication(ExpertApplicationBean application, Long userId) throws Exception;

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
	public List<UserProfileBean> getExperts(String placeIds,String services) throws Exception;

	/**
	 * 获取达人信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean getExpert(Long userId) throws Exception;


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