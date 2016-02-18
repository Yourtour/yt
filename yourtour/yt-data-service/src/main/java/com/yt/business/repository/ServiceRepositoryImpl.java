package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.neo4j.repository.ExpertServiceTuple;
import com.yt.business.neo4j.repository.ServiceBeanRepository;
import com.yt.business.neo4j.repository.RouteServiceTuple;
import com.yt.core.utils.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceRepositoryImpl extends CrudAllInOneOperateImpl implements
		ServiceRepository {
	private static final Log LOG = LogFactory.getLog(ServiceRepositoryImpl.class);

	@Autowired
	private ServiceBeanRepository serviceBeanRepository;

	@Override
	public List<ExpertServiceBean> getExpertServices(Long expertId) throws Exception {
		return serviceBeanRepository.getExpertServices(expertId);
	}

	@Override
	public List<ExpertServiceBean> getPlaceServices(Long[] placeIds, Long nextCursor, int limit) throws Exception {
		List<ExpertServiceBean> services = new ArrayList<>();

		List<ExpertServiceTuple> tuples = serviceBeanRepository.getPlaceServices(placeIds, nextCursor, limit);
		if(CollectionUtils.isNotEmpty(tuples)){
			for(ExpertServiceTuple tuple : tuples){
				services.add(tuple.getService());
			}
		}
		return services;
	}

	@Override
	public List<RouteServiceBean> getRouteServices(Long routeId) throws Exception {
		List<RouteServiceBean> services = new ArrayList<>();

		List<RouteServiceTuple> tuples = serviceBeanRepository.getRouteServices(routeId);
		if(CollectionUtils.isNotEmpty(tuples)){
			for(RouteServiceTuple tuple : tuples){
				services.add(tuple.getRouteService());
			}
		}

		return services;
	}
}
