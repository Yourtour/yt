package com.yt.business.service.impl;

import com.yt.business.bean.*;
import com.yt.business.repository.neo4j.ExpertBeanRepository;
import com.yt.business.repository.neo4j.ExpertTuple;
import com.yt.business.repository.neo4j.RouteRepository;
import com.yt.business.repository.neo4j.RouteTuple;
import com.yt.business.service.IExpertService;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpertServiceImpl extends ServiceBase implements IExpertService {
	private static final Log LOG = LogFactory.getLog(ExpertServiceImpl.class);

	@Autowired
	private CrudOperate<ExpertApplicationBean> applicationCrudOperate;

	@Autowired
	private CrudOperate<ExpertApprovementBean> approveCrudOperate;

	@Autowired
	private CrudOperate<ExpertBean> expertCrudOperate;

	@Autowired
	private CrudOperate<ExpertServiceBean> serviceCrudOperate;

	@Autowired
	private ExpertBeanRepository expertBeanRepository;

	@Autowired
	private RouteRepository routeRepository;

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
	public List<RouteMainBean> getRecommendRoutes(Long uid, Long nextCursor, int limit) throws Exception {
		List<RouteMainBean> routes = new ArrayList<>();

		List<RouteTuple> tuples = routeRepository.getRecommendRoutes(uid, nextCursor, limit);
		if(CollectionUtils.isNotEmpty(tuples)){
			for(RouteTuple tuple : tuples){
				routes.add(tuple.getRoute());
			}
		}

		return routes;
	}

	@Override
	public List<RouteMainBean> getServicedRoutes(Long expertId, Long nextCursor, int limit) throws Exception {
		List<RouteMainBean> routes = new ArrayList<>();

		List<RouteRepository.OwnerRouteTuple> tuples = this.routeRepository.getRoutes(expertId, nextCursor, limit, "EXPERT");
		if(CollectionUtils.isNotEmpty(tuples)){
			for(RouteRepository.OwnerRouteTuple tuple : tuples){
				routes.add(tuple.getRoute());
			}
		}

		return routes;
	}

	@Override
	public void saveApplication(ExpertApplicationBean application, Long uid) throws Exception {
		this.applicationCrudOperate.save(application);
	}

	@Override
	public ExpertApplicationBean getApplication(Long userId) throws Exception {
		return expertBeanRepository.getApplication(userId);
	}

	@Override
	public void saveApprovement(Long applicationId, ExpertApprovementBean approvement, Long userId) throws Exception {
		this.approveCrudOperate.save(approvement);

		ExpertBean expert = this.expertCrudOperate.get(approvement.getApplication().getExpertId());
		expert.setResult(approvement.getResult());
		this.expertCrudOperate.save(expert);
	}

	@Override
	public ExpertBean getExpert(Long userId) throws Exception {
		ExpertBean expert = this.expertCrudOperate.get(userId);

		return expert;
	}


}
