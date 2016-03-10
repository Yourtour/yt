package com.yt.business.service.impl;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.repository.neo4j.ServiceBeanRepository;
import com.yt.business.repository.neo4j.RouteServiceTuple;
import com.yt.business.service.IService;
import com.yt.core.utils.CollectionUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl extends ServiceBase implements IService {
	private static final Log LOG = LogFactory.getLog(ServiceImpl.class);

	@Autowired
	private ServiceBeanRepository serviceBeanRepository;

	@Autowired
	private CrudOperate<ExpertServiceBean> expertServiceCrudOperate;

	@Autowired
	private CrudOperate<RouteServiceBean> routeServiceCrudOperate;

	public ServiceImpl() {
	}

	@Override
	public void saveExpertService(ExpertServiceBean service, Long userId) throws Exception {
		expertServiceCrudOperate.save(service);
	}

	@Override
	public void deleteExpertService(Long serviceId, Long userId) throws Exception {
		ExpertServiceBean service = expertServiceCrudOperate.get(serviceId);

		expertServiceCrudOperate.delete(service);
	}

	@Override
	public ExpertServiceBean getExpertService(Long serviceId) throws Exception {
		return expertServiceCrudOperate.get(serviceId);
	}

	@Override
	public List<ExpertServiceBean> getExpertServices(Long uid) throws Exception {
		return serviceBeanRepository.getExpertServices(uid);
	}

	@Override
	public void deleteRouteService(Long routeId, Long serviceId, Long userId) throws Exception {
		RouteServiceBean service = routeServiceCrudOperate.get(serviceId);

		routeServiceCrudOperate.delete(service);
	}

	@Override
	public void saveRouteService(RouteServiceBean service, Long userId) throws Exception {
		routeServiceCrudOperate.save(service);
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
