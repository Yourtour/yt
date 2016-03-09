package com.yt.business.service.impl;

import com.yt.business.bean.RouteMemberBean;
import com.yt.business.service.IRouteMemberService;
import com.yt.business.service.IService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteMemberServiceImpl extends BaseServiceImpl implements IRouteMemberService {
	private static final Log LOG = LogFactory.getLog(RouteMemberServiceImpl.class);

	@Autowired
	private IService routeServiceRepo;

	@Override
	public void saveMember(RouteMemberBean member, Long userId) throws Exception {

	}

	@Override
	public void deleteMember(Long routeId, Long memberId, Long userId) throws Exception {

	}

	@Override
	public List<RouteMemberBean> getMembers(Long routeId) throws Exception {
		return null;
	}
}
