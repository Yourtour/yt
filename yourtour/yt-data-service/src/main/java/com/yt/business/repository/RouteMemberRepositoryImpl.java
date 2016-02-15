package com.yt.business.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.RouteMemberBean;

@Component
public class RouteMemberRepositoryImpl extends CrudAllInOneOperateImpl implements
	RouteMemberRepository {
	private static final Log LOG = LogFactory
			.getLog(RouteMemberRepositoryImpl.class);

	@Autowired
	private ServiceRepository routeServiceRepo;

	@Override
	public List<RouteMemberBean> getRouteMembers(Long routeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
