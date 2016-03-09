package com.yt.business.service.impl;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.*;
import com.yt.business.repository.neo4j.ExpertBeanRepository;
import com.yt.business.repository.neo4j.ExpertTuple;
import com.yt.business.repository.neo4j.RouteBeanRepository;
import com.yt.business.service.ICommentService;
import com.yt.business.service.IExpertService;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpertServiceImpl extends CrudAllInOneOperateImpl implements	IExpertService {
	private static final Log LOG = LogFactory.getLog(ExpertServiceImpl.class);

	@Autowired
	private ExpertBeanRepository expertBeanRepository;

	@Autowired
	private RouteBeanRepository routeBeanRepository;

	@Autowired
	private ICommentService ICommentService;

	public ExpertServiceImpl() {
		super();
	}

	@Override
	public List<ExpertBean> getExperts(String placeIds, String pServices) throws Exception {
		Long[] places = null, services = null;
		List<ExpertTuple> tuples = null;

		if(StringUtils.isNotNull(placeIds)){
			String[] ids = placeIds.split(",");
			places = new Long[ids.length];
			for(int index = 0; index < ids.length; index++){
				places[index] = Long.valueOf(ids[index]);
			}
		}

		if(StringUtils.isNull(pServices)){
			tuples = expertBeanRepository.getExperts(places);
		}else{
			String[] ids = pServices.split(",");
			services = new Long[ids.length];
			for(int index = 0; index < ids.length; index++){
				services[index] = Long.valueOf(ids[index]);
			}

			tuples = expertBeanRepository.getExperts(places,services);
		}

		List<ExpertBean> experts = new ArrayList<>();
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
	public List<ExpertServiceBean> getServices(Long expertId, Long nextCursor, int limit) throws Exception {
		return expertBeanRepository.getServices(expertId);
	}

	@Override
	public List<RouteMainBean> getRecommendRoutes(Long uid, Long nextCursor, int limit) throws Exception {
		return this.routeBeanRepository.getRoutesRecommendedByExpert(uid, nextCursor, limit);
	}

	@Override
	public List<RouteMainBean> getServicedRoutes(Long expertId, Long nextCursor, int limit) throws Exception {
		return this.routeBeanRepository.getRoutesParticipatedAsExpert(expertId, nextCursor, limit);
	}

	@Override
	public void saveApplication(ExpertApplicationBean application) throws Exception {
		ExpertBean expert = application.getExpert();
		super.save(expert);

		super.save(application);
	}

	@Override
	public ExpertApplicationBean getApplication(Long userId) throws Exception {
		return expertBeanRepository.getApplication(userId);
	}

	@Override
	public void saveApprovement(ExpertApprovementBean approvement) throws Exception {
		super.save(approvement);

		ExpertBean expert = (ExpertBean) this.get(ExpertBean.class, approvement.getApplication().getExpertId());
		expert.setResult(approvement.getResult());
		super.save(expert);
	}
}
