package com.yt.business.service.impl;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.RouteMainBeanRepository;
import com.yt.business.service.IRouteService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.neo4j.repository.CrudOperate;
import com.yt.neo4j.repository.RelationshipCrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RouteServiceImpl extends ServiceBase implements IRouteService {
	private static final Log LOG = LogFactory.getLog(RouteServiceImpl.class);

	@Autowired
	private RouteMainBeanRepository repository;

	@Autowired
	private CrudOperate<RouteMainBean> routeCrudOperate;

	@Autowired
	private CrudOperate<RouteScheduleBean> scheduleCrudOperate;

	@Autowired
	private CrudOperate<RouteScheduleItemBean> activityItemCrudOperate;

	@Autowired
	private CrudOperate<ResourceActivityItemBean> resourceActivityItemCrudOperate;

	@Autowired
	private RelationshipCrudOperate<UserProfileBean, RouteMainBean> memberRelationship;

	@Autowired
	private RelationshipCrudOperate<PlaceBean, RouteMainBean> placeRelationship;

	public RouteServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IRouteService#isRouteMember(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public boolean isRouteMember(Long routeId, Long userId) {
		return repository.isRouteMember(routeId, userId) > 0;
	}

	@Override
	public void deleteRouteInfoes(Long[] routeIds, Long operatorId) throws Exception {
		for(Long routeId : routeIds){
			RouteMainBean route = routeCrudOperate.get(routeId, false);
			this.updateBaseInfo(route, operatorId);

			route.setDeleted(true);
			routeCrudOperate.save(route, false);
		}
	}

	@Override
	public void saveRouteInfo(RouteMainBean route, String relationship, Long operatorId)
	throws Exception {
		boolean isNew = route.isNew();
		RouteMainBean saved = null;
		if(! isNew){
			//解除目的地关系
			List<PlaceBean> places = repository.getPlaces(route.getId());
			if(CollectionUtils.isNotEmpty(places)){
				for(PlaceBean place : places){
					placeRelationship.deleteRelation(place, route, Constants.RELATION_TYPE_TO);
				}
			}

			saved = this.routeCrudOperate.get(route.getId(), false);
			if(saved != null){
				BeanUtils.merge(route, saved);
			}
		}

		if(saved == null){
			saved = route;
		}

		//保存行程信息
		List<RouteScheduleBean> schedules = route.getSchedules();
		route.setSchedules(null); //不自动保存日程信息
		saved.setToPlaceBeans(route.getToPlaceBeans());
		this.updateBaseInfo(saved, operatorId);
		routeCrudOperate.save(saved);

		if(isNew) {
			// 保存用户和行程中关系
			UserProfileBean user = new UserProfileBean(operatorId);
			memberRelationship.createRelation(user, route, relationship, Direction.INCOMING);
		}

		//保存日程信息
		List<RouteScheduleBean> existedSchedules = this.repository.getSchedules(route.getId());
		if (CollectionUtils.isEmpty(existedSchedules) && CollectionUtils.isNotEmpty(schedules)) {
			int index = 1;
			for (RouteScheduleBean schedule : schedules) {
				schedule.setRouteMain(route);
				schedule.setIndex(index);
				schedule.setType(RouteScheduleBean.ScheduleType.DAY);
				schedule.setName("D" + index);
				super.updateBaseInfo(schedule, operatorId);
				scheduleCrudOperate.save(schedule);

				index++;
			}
		}
	}

	@Override
	public RouteMainBean cloneRouteInfo(Long sourceId, String relationship, Long operatorId) throws Exception {
		RouteMainBean source = this.getRouteInfo(sourceId, 2);
		if (source == null) {
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		RouteMainBean target = (RouteMainBean) source.clone();
		this.saveRouteInfo(target, relationship, operatorId);

		return target;
	}

	@Override
	public RouteMainBean cloneRouteInfo(Long sourceId, RouteMainBean target, String relation, Long operatorId) throws Exception {
		RouteMainBean source = this.getRouteInfo(sourceId, 2);
		if (source == null) {
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		List<RouteScheduleBean> sSchedules = source.getSchedules();
		List<RouteScheduleBean> tSchedules = target.getSchedules();
		if (sSchedules != null) {
			int len = tSchedules.size();
			for (int index = 0; index < len && index < sSchedules.size(); index++) {
			}
		}

		routeCrudOperate.save(target);

		// 保存用户在行程中的成员关系
		UserProfileBean user = new UserProfileBean(operatorId);
		memberRelationship.createRelation(user, target, relation, Direction.INCOMING);

		return this.getRouteInfo(target.getId(), 1);
	}

	@Override
	public PagingDataBean<List<RouteMainBean>> getUserRouteInfoes(Long userId, String relationship, PagingConditionBean condition, Map<String, Object> params) throws Exception {
		int total = condition.getTotal();
		if (total <= 0) {
			total = repository.getRouteNum4User(userId, relationship);
		}

		List<RouteMainBean> list = repository.getRoutes(userId, condition.getNextCursor(), condition.getLimit(), relationship);

		return new PagingDataBean<>(total,list);
	}

	@Override
	public PagingDataBean<List<RouteMainBean>> getUserRouteInfoes(Long userId, String relationship, PagingConditionBean condition) throws Exception {
		int total = condition.getTotal();
		if (total <= 0) {
			total = repository.getRouteNum4User(userId, relationship);
		}

		List<RouteMainBean> list = repository.getRoutes(userId, condition.getNextCursor(), condition.getLimit(), relationship);

		return new PagingDataBean<>(total,list);
	}

	@Override
	public PagingDataBean<List<RouteMainBean>> getPlaceRouteInfoes(Long placeId, PagingConditionBean condition) throws Exception {
		return null;
	}

	@Override
	public PagingDataBean<List<RouteMainBean>> getPlaceRouteInfoes(Long[] placeIds, int duration, PagingConditionBean condition) throws Exception {
		return null;
	}

	@Override
	public RouteMainBean getRouteInfo(Long routeId, int mode) throws Exception {
		RouteMainBean route = null;
		switch (mode){
			case 0:
				route = routeCrudOperate.get(routeId, false);
				break;
			case 1:
				route = routeCrudOperate.get(routeId, true);
				break;
			case 2:
				route =  routeCrudOperate.get(routeId, true);
				List<RouteScheduleBean> schedules = route.getSchedules();
				if(CollectionUtils.isNotEmpty(schedules)){
					for(RouteScheduleBean schedule : schedules){
						if(! schedule.getType().equals(RouteScheduleBean.ScheduleType.DAY)){
							schedule.setResource(this.repository.getResource(schedule.getId()));
						}
					}
				}

				break;
		}

		return route;
	}

	@Override
	public void publishRouteInfoes(Long[] routeIds, Long userId) throws Exception {
		RouteMainBean route = null;
		for(Long routeId : routeIds){
			route = routeCrudOperate.get(routeId, false);
			if(route != null){
				route.setStatus(RouteMainBean.Status.PUBLISHED);
				super.updateBaseInfo(route, userId);
				this.routeCrudOperate.save(route, false);
			}
		}
	}

	@Override
	public void withdrawRouteInfoes(Long[] routeIds, Long userId) throws Exception {
		RouteMainBean route = null;
		for(Long routeId : routeIds){
			route = routeCrudOperate.get(routeId, false);
			if(route != null){
				route.setStatus(RouteMainBean.Status.WITHDRAW);
				super.updateBaseInfo(route, userId);
				this.routeCrudOperate.save(route, false);
			}
		}
	}

	@Override
	public List<UserProfileBean> getRouteMember(Long routeId) throws Exception {
		return repository.getRouteMemberUserProfiles(routeId);
	}

	@Override
	public void saveRouteSchedule(RouteScheduleBean schedule, Long operatorId)
	throws Exception {
		RouteScheduleBean saved = null;
		if(! schedule.isNew()){
			saved = scheduleCrudOperate.get(schedule.getId(), false);

			BeanUtils.merge(schedule, saved);
		}else{
			saved = schedule;
		}

		this.updateBaseInfo(saved, operatorId);

		scheduleCrudOperate.save(saved);
	}

	@Override
	public void deleteRouteSchedule(Long routeId, String type, Long scheduleId, Long operatorId)
			throws Exception {
		RouteScheduleBean schedule = scheduleCrudOperate.get(scheduleId);
		if (schedule == null) {
			LOG.warn(String.format("No Schedule found for route=[%d] and schedule=[%d]",
					routeId, scheduleId));

			return;
		}
		scheduleCrudOperate.delete(schedule);

		//删除日程下的活动安排
		if(type.equalsIgnoreCase(RouteScheduleBean.ScheduleType.DAY.toString())){
			List<RouteScheduleBean> activities = this.repository.getScheduleActivities(scheduleId);
			if(CollectionUtils.isNotEmpty(activities)){
				for(RouteScheduleBean activity : activities) {
					scheduleCrudOperate.delete(activity);
				}
			}
		}
	}

	@Override
	public List<RouteScheduleBean> getRouteSchedules(Long routeId) throws Exception {
		List<RouteScheduleBean> schedules = this.repository.getSchedules(routeId);
		if(CollectionUtils.isNotEmpty(schedules)){
			for(RouteScheduleBean schedule : schedules){
				schedule.setResource(this.repository.getResource(schedule.getId()));
			}
		}

		return schedules;
	}
}
