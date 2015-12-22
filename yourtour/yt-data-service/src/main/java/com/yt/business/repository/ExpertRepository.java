package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertApprovementBean;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.neo4j.repository.UserTuple;

import java.util.List;

public interface ExpertRepository extends CrudAllInOneOperate {
	/**
	 *
	 * @param expertId
	 * @return
	 */
	public List<ExpertServiceBean> getServices(Long expertId);
}
