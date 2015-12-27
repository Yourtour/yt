package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.RouteMemberBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.neo4j.repository.RouteMemberBeanRepository;
import com.yt.business.neo4j.repository.RouteServiceBeanRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteServiceRepositoryImpl extends CrudAllInOneOperateImpl implements
		RouteServiceRepository {
	private static final Log LOG = LogFactory.getLog(RouteServiceRepositoryImpl.class);

	@Autowired
	private RouteServiceBeanRepository routeServiceBeanRepository;

	@Override
	public List<RouteServiceBean> getServices(Long expertId) throws Exception {
		return routeServiceBeanRepository.getServices(expertId);
	}
}
