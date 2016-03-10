package com.yt.business.service.impl;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.RouteRepository;
import com.yt.business.repository.neo4j.RouteRepository.OwnerRouteTuple;
import com.yt.business.repository.neo4j.RouteTuple;
import com.yt.business.service.IRouteService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
import com.yt.core.utils.CollectionUtils;
import com.yt.neo4j.repository.CrudOperate;
import com.yt.neo4j.repository.RelationshipCrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteServiceImpl extends ServiceBase implements IRouteService {
	private static final Log LOG = LogFactory.getLog(RouteServiceImpl.class);

	@Autowired
	private RouteRepository repository;
	
	@Autowired
	private CrudOperate<RouteMainBean> routeCrudOperate;

	@Autowired
	private CrudOperate<RouteActivityBean> activityCrudOperate;

	@Autowired
	private CrudOperate<RouteProvisionBean> provisionCrudOperate;

	@Autowired
	private CrudOperate<RouteScheduleBean> scheduleCrudOperate;

	@Autowired
	private CrudOperate<RouteActivityItemBean> activityItemCrudOperate;

	@Autowired
	private CrudOperate<ResourceActivityItemBean> resourceActivityItemCrudOperate;

	@Autowired
	private RelationshipCrudOperate<UserProfileBean, RouteMainBean> memberRelationship;

	public RouteServiceImpl() {
		super();
	}

	@Override
	public void deleteRoute(Long routeId, Long operatorId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);

		this.updateBaseInfo(route, operatorId);

		routeCrudOperate.delete(route);
	}

	@Override
	public void saveRoute(RouteMainBean route, Long operatorId) throws Exception {
		this.updateBaseInfo(route, operatorId);

		routeCrudOperate.save(route);
	}

	@Override
	public void saveRouteMainAndSchedules(RouteMainBean route, Long operatorId) throws Exception {
		// 如果存在日程，则先删除原先的日程信息
		boolean isNew = route.isNew();
		if(! isNew) {
			Long routeId = route.getId();
			List<RouteScheduleBean> schedules = repository.getRouteSchedules(routeId);
			if(schedules != null){
				for(RouteScheduleBean schedule : schedules){
					scheduleCrudOperate.delete(schedule);
				}
			}
		}

		this.updateBaseInfo(route, operatorId);
		routeCrudOperate.save(route);

		if(isNew){ //保存用户在行程中的成员关系
			UserProfileBean user = new UserProfileBean(operatorId);
			Map<String, Object> member = new HashMap<>();
			member = new HashMap<>();
			member.put("permission", "W");
			member.put("role", Constants.GroupRole.LEADER.code);

			memberRelationship.createRelation(user, route, Constants.RELATION_TYPE_MEMBER, Direction.INCOMING, member);
		}
	}

	@Override
	public RouteMainBean cloneRoute(Long sourceId, RouteMainBean target, Long operatorId) throws Exception {
		this.saveRouteMainAndSchedules(target, operatorId);

		RouteMainBean source = this.getRoute(sourceId);
		if(source == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		List<RouteProvisionBean> sProvisions = source.getProvisions();
		List<RouteProvisionBean> tProvisions = new ArrayList<>();
		if(sProvisions != null){
			for(RouteProvisionBean sProvision : sProvisions){
				RouteProvisionBean tProvision = (RouteProvisionBean) sProvision.clone();

				this.updateBaseInfo(tProvision, operatorId);
				tProvision.setRouteMain(target);

				provisionCrudOperate.save(tProvision);
				tProvisions.add(tProvision);
			}

			target.setProvisions(tProvisions);
		}

		List<RouteScheduleBean> sSchedules = source.getSchedules();
		List<RouteScheduleBean> tSchedules = target.getSchedules();
		if(sSchedules != null){
			int len = tSchedules.size();
			for(int index = 0; index < len && index < sSchedules.size(); index++){
				List<RouteActivityBean> sActivities = sSchedules.get(index).getActivities();
				List<RouteActivityBean> tActivities = new ArrayList<>();

				for(RouteActivityBean sActivity : sActivities){
					RouteActivityBean tActivity = (RouteActivityBean) sActivity.clone();
					tActivity.setUpdatedUserId(operatorId);
					tActivity.setCreatedUserId(operatorId);
					tActivity.setSchedule(tSchedules.get(index));

					activityCrudOperate.save(tActivity);
					tActivities.add(tActivity);
				}

				tSchedules.get(index).setActivities(tActivities);
			}
		}

		routeCrudOperate.save(target);

		return this.getRoute(target.getId());
	}

	@Override
	public List<RouteMainBean> getRoutes(Long userId) throws Exception {
		RouteMainBean route = null;
		List<RouteMainBean> list = new ArrayList<RouteMainBean>();

		List<OwnerRouteTuple> routes = repository.getRoutes(userId, 0l , 20, "MEMBER|LEADER");
		for (OwnerRouteTuple bean : routes) {
			route = bean.getRoute();
			if (route == null) {
				continue;
			}

			RouteMemberBean member = new RouteMemberBean(route.getId());
			member.setImageUrl(bean.getImageUrl());
			member.setRole(bean.getRole());
			member.setImpression(bean.impression);
			member.setPermission(bean.permission);

			route.setMember(member);

			list.add(route);
		}
		return list;
	}

	@Override
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor, int limit) throws Exception {
		List<RouteMainBean> routes = new ArrayList<>();

		List<RouteTuple> tuples = repository.getRecommendRoutes(placeId, nextCursor, limit);
		if(CollectionUtils.isNotEmpty(tuples)){
			for(RouteTuple tuple : tuples){
				routes.add(tuple.getRoute());
			}
		}

		return routes;
	}

	@Override
	public List<RouteMainBean> getRoutes(Long[] placeIds, int duration, Long nextCursor, int limit) throws Exception {
		List<RouteMainBean> routes = new ArrayList<>();

		List<RouteTuple> tuples = repository.getRecommendRoutes(placeIds, duration, nextCursor, limit);
		if(CollectionUtils.isNotEmpty(tuples)){
			for(RouteTuple tuple : tuples){
				routes.add(tuple.getRoute());
			}
		}

		return routes;
	}

	@Override
	public RouteMainBean getRoute(Long routeId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);

		if (route != null) {
			List<RouteScheduleBean> schedules = route.getSchedules();
			if (schedules != null && schedules.size() > 0) {
				List<RouteScheduleBean> newSchedules = new Vector<RouteScheduleBean>(schedules.size());
				for (RouteScheduleBean schedule : schedules) {
					RouteScheduleBean newSchedule = scheduleCrudOperate.get(schedule.getId());
					List<RouteActivityBean> activities = newSchedule.getActivities();
					if (activities != null && activities.size() > 0) {
						List<RouteActivityBean> newActivities = new Vector<RouteActivityBean>(activities.size());
						for (RouteActivityBean activity : activities) {
							RouteActivityBean newActivity = activityCrudOperate.get(activity.getId());
							newActivities.add(newActivity);
						}

						newSchedule.getActivities().clear();
						newSchedule.getActivities().addAll(newActivities);
					}
					newSchedules.add(newSchedule);
				}
				route.getSchedules().clear();
				route.getSchedules().addAll(newSchedules);
			}
		}
		return route;
	}

	@Override
	public void saveSchedule(RouteScheduleBean schedule, Long operatorId) throws Exception {
		this.updateBaseInfo(schedule, operatorId);

		scheduleCrudOperate.save(schedule);
	}

	@Override
	public void deleteSchedule(Long routeId, Long scheduleId, Long uoperatorIdid) throws Exception {
		RouteScheduleBean schedule = scheduleCrudOperate.get(scheduleId);
		if(schedule == null){
			LOG.warn(String.format("No Schedule found for route=[%d] and schedule=[%d]", routeId, scheduleId));

			return;
		}

		scheduleCrudOperate.delete(schedule);
	}

	@Override
	public RouteActivityBean getScheduleActivity(Long activityId) throws Exception {
		RouteActivityBean activity = activityCrudOperate.get(activityId);

		if(activity != null){
			List<RouteActivityItemBean> items = activity.getItems();
			if(items != null){
				for(RouteActivityItemBean item : items){
					if(item.getResourceActivityItemId() == null) continue;

					item.setResourceActivityItem(resourceActivityItemCrudOperate.get(item.getResourceActivityItemId(), false));
				}
			}
		}

		return activity;
	}

	@Override
	public void saveScheduleActivity(Long routeId, RouteActivityBean activity, Long operatorId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);
		if(route == null){
			throw new Exception("No Route found for id=" + routeId);
		}

		this.updateBaseInfo(activity, operatorId);

		activityCrudOperate.save(activity);
	}

	@Override
	public void deleteScheduleActivity(Long routeId, Long activityId, Long operatorId) throws Exception {
		RouteActivityBean activity = this.activityCrudOperate.get(activityId);
		if(activity == null){
			LOG.warn(String.format("No activity found for route=[%d] and activity=[%d]", routeId, activityId));
			return;
		}

		this.updateBaseInfo(activity, operatorId);
		this.activityCrudOperate.delete(activity);
	}

	@Override
	public void deleteScheduleActivityItem(Long routeId, Long activityId, Long itemId, Long operatorId) throws Exception {
		RouteActivityItemBean item = this.activityItemCrudOperate.get(itemId);
		if(item == null){
			LOG.warn(String.format("No activity item found for route=[%d], activity=[%d], itemId=%d ", routeId, activityId, itemId));
			return;
		}

		this.updateBaseInfo(item, operatorId);
		this.activityItemCrudOperate.delete(item);
	}


	@Override
	public void saveRouteProvision(Long routeId, RouteProvisionBean provision, Long operatorId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);
		if(route == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		this.updateBaseInfo(provision, operatorId);
		provisionCrudOperate.save(provision);
	}

	@Override
	public void deleteRouteProvision(Long routeId, Long provisionId, Long operatorId) throws Exception {
		RouteProvisionBean provision = provisionCrudOperate.get(provisionId);
		if(provision == null){
			LOG.warn(String.format("No provision found for route=[%d], provision=[%d]", routeId, provisionId));
			return;
		}

		this.updateBaseInfo(provision, operatorId);
		provisionCrudOperate.delete(provision);
	}
}
