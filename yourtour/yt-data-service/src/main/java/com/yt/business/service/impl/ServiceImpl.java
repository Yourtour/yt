package com.yt.business.service.impl;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.repository.neo4j.ServiceBeanRepository;
import com.yt.business.repository.neo4j.RouteServiceTuple;
import com.yt.business.service.IService;
import com.yt.core.utils.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl extends BaseServiceImpl implements IService {
	private static final Log LOG = LogFactory.getLog(ServiceImpl.class);

	@Autowired
	private ServiceBeanRepository serviceBeanRepository;

	@Override
	public void saveService(RouteServiceBean service, Long userId) throws Exception {

	}

	@Override
	public void deleteService(Long routeId, Long serviceId, Long userId) throws Exception {

	}

	@Override
	public List<ExpertServiceBean> getExpertServices(Long expertId) throws Exception {
		return serviceBeanRepository.getExpertServices(expertId);
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
