package com.yt.business.service.impl;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteMemberBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.RouteMainBeanRepository;
import com.yt.business.service.IRouteMemberService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
import com.yt.neo4j.repository.CrudOperate;
import com.yt.neo4j.repository.RelationshipCrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteMemberServiceImpl extends ServiceBase implements IRouteMemberService {
	private static final Log LOG = LogFactory.getLog(RouteMemberServiceImpl.class);

	@Autowired
	private RouteMainBeanRepository repository;

	@Autowired
	private CrudOperate<RouteMainBean> routeCrudOperate;

	@Autowired
	private CrudOperate<UserProfileBean> userCrudOperate;

	@Autowired
	private RelationshipCrudOperate<UserProfileBean, RouteMainBean> memberRelationship;

	@Override
	public void saveMember(Long routeId, Long userId,  RouteMemberBean member, Long operatorId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId, false);
		if(route == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		UserProfileBean user = userCrudOperate.get(userId, false);
		if(user == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		Map<String, Object> props = new HashMap<>();
		props.put("role", member.getRole());

		memberRelationship.createRelation(user, route, Constants.RELATION_TYPE_MEMBER, Direction.INCOMING, props);
	}

	@Override
	public void deleteMember(Long routeId, Long userId, Long operatorId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId, false);
		if(route == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		UserProfileBean user = userCrudOperate.get(userId, false);
		if(user == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		memberRelationship.deleteRelation(user, route, Constants.RELATION_TYPE_MEMBER);
	}

	@Override
	public List<RouteMemberBean> getMembers(Long routeId) throws Exception {
		List<RouteMemberBean> members = repository.getRouteMember(routeId);

		return members;
	}
}
