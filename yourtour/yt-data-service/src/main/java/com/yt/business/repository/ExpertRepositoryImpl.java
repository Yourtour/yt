package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.*;
import com.yt.business.neo4j.repository.ExpertServiceRepository;
import com.yt.business.neo4j.repository.ExpertTuple;
import com.yt.core.utils.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
	public List<ExpertBean> getExperts(String placeIds, String duration) throws Exception {
		List<Long> places = new ArrayList<>();
		if(placeIds != null){
			String[] ids = placeIds.split(",");
			for(String id : ids){
				places.add(Long.valueOf(id));
			}
		}

		List<ExpertBean> experts = new ArrayList<>();
		List<ExpertTuple> tuples = expertServiceRepository.getExperts(places.toArray(new Long[]{}), Integer.valueOf(duration));
		if(CollectionUtils.isNotEmpty(tuples)){
			for(ExpertTuple tuple : tuples){
				ExpertBean expert = tuple.getExpert();
				expert.setProfile(tuple.getProfile());

				experts.add(expert);
			}
		}

		return experts;
	}

	@Override
	public List<ExpertServiceBean> getServices(Long expertId) {
		return expertServiceRepository.getServices(expertId);
	}

	@Override
	public void saveApplication(ExpertApplicationBean application,String operator) throws Exception {
		ExpertBean expert = application.getExpert();
		super.save(expert, operator);

		super.save(application, operator);
	}

	@Override
	public ExpertApplicationBean getApplication(Long userId) throws Exception {
		return expertServiceRepository.getApplication(userId);
	}

	@Override
	public void saveApprovement(ExpertApprovementBean approvement,String operator) throws Exception {
		super.save(approvement, operator);

		ExpertBean expert = (ExpertBean) this.get(ExpertBean.class, approvement.getApplication().getExpertId());
		expert.setResult(approvement.getResult());
		super.save(expert, expert.getCreatedUserId());
	}

	@Override
	public List<RouteMainBean> getRoutes(Long expertId) {
		return expertServiceRepository.getRoutes(expertId);
	}
}
