package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertApprovementBean;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.neo4j.repository.ExpertServiceRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpertRepositoryImpl extends CrudAllInOneOperateImpl implements
		ExpertRepository {
	private static final Log LOG = LogFactory.getLog(ExpertRepositoryImpl.class);

	@Autowired
	private ExpertServiceRepository expertServiceRepository;

	public ExpertRepositoryImpl() {
		super();
	}

	@Override
	public List<ExpertServiceBean> getServices(Long expertId) {
		return expertServiceRepository.getServices(expertId);
	}

	@Override
	public void saveApplication(ExpertApplicationBean application,String operator) throws Exception {
		super.save(application, operator);
	}

	@Override
	public ExpertApplicationBean getApplication(Long userId) throws Exception {
		return expertServiceRepository.getApplication(userId);
	}

	@Override
	public void saveApprovement(ExpertApprovementBean approvement,String operator) throws Exception {
		super.save(approvement, operator);
	}

	@Override
	public List<RouteMainBean> getRoutes(Long expertId) {
		return expertServiceRepository.getRoutes(expertId);
	}
}