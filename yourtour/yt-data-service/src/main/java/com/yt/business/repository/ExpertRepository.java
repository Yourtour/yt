package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;
import com.yt.business.neo4j.repository.UserTuple;

import java.util.List;

public interface ExpertRepository extends CrudAllInOneOperate {
	/**
	 *
	 * @param expertId
	 * @return
	 */
	public List<ExpertServiceBean> getServices(Long expertId);

	/**
	 *
	 * @param expertId
	 * @return
	 */
	public List<RouteMainBean> getRoutes(Long expertId);

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
