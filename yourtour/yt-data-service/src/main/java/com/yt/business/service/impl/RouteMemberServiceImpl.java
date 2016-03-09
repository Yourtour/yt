package com.yt.business.service.impl;

import java.util.List;

import com.yt.business.service.IRouteMemberService;
import com.yt.business.service.IRouteServiceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.RouteMemberBean;

@Component
public class RouteMemberServiceImpl extends CrudAllInOneOperateImpl implements
		IRouteMemberService {
	private static final Log LOG = LogFactory
			.getLog(RouteMemberServiceImpl.class);

	@Autowired
	private IRouteServiceService routeServiceRepo;

	@Override
	public List<RouteMemberBean> getRouteMembers(Long routeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
