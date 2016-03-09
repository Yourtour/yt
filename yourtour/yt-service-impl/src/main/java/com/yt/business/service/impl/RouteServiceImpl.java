package com.yt.business.service.impl;

import com.yt.business.bean.*;
import com.yt.business.repository.neo4j.RouteActivityRepository;
import com.yt.business.repository.neo4j.RouteBeanRepository;
import com.yt.business.repository.neo4j.RouteBeanRepository.OwnerRouteTuple;
import com.yt.business.repository.neo4j.RouteTuple;
import com.yt.business.service.IRouteService;
import com.yt.core.utils.StringUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
public class RouteServiceImpl extends BaseServiceImpl implements IRouteService {
	private static final Log LOG = LogFactory.getLog(RouteServiceImpl.class);

	@Autowired
	private RouteBeanRepository repository;
	
	@Autowired
	private RouteActivityRepository activityRepository;

	@Autowired
	private CrudOperate<RouteMainBean> routeCrudOperate;

	@Autowired
	private CrudOperate<RouteActivityBean> activityCrudOperate;

	@Autowired
	private CrudOperate<RouteProvisionBean> provisionCrudOperate;

	@Autowired
	private CrudOperate<RouteScheduleBean> scheduleCrudOperate;

	@Autowired
	private CrudOperate<ResourceActivityItemBean> activityItemCrudOperate;



	public RouteServiceImpl() {
		super();
	}

	@Override
	public void deleteRoute(Long routeId, Long userId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);

		routeCrudOperate.delete(route);
	}

	@Override
	public void saveRoute(RouteMainBean route, Long userId) throws Exception {
		routeCrudOperate.save(route);
	}

	@Override
	public RouteMainBean getRoute(Long routeId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);
		if (route != null) {
			List<RouteScheduleBean> schedules = route.getSchedules();
			if (schedules != null && schedules.size() > 0) {
				List<RouteScheduleBean> newSchedules = new Vector<RouteScheduleBean>(
						schedules.size());
				for (RouteScheduleBean schedule : schedules) {
					RouteScheduleBean newSchedule = scheduleCrudOperate.get(schedule.getId());
					List<RouteActivityBean> activities = newSchedule
							.getActivities();
					if (activities != null && activities.size() > 0) {
						List<RouteActivityBean> newActivities = new Vector<RouteActivityBean>(
								activities.size());
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
	public List<RouteMainBean> getRoutes(Long userId) throws Exception {
		List<OwnerRouteTuple> routes = repository.getOwnedRoutes(userId);
		List<RouteMainBean> list = new Vector<RouteMainBean>(routes.size());
		for (OwnerRouteTuple bean : routes) {
			if (bean.route == null) {
				continue;
			}

			if(StringUtils.isNotNull(bean.getImageUrl())) {
				bean.route.setImageUrl(bean.imageUrl);
			}
			bean.route.setImpression(bean.impression);
			list.add(bean.route);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Found %d RouteMainBean by UserBean(%d)",
					list.size(), userId));
		}
		return list;
	}

	@Override
	public void saveSchedule(RouteScheduleBean schedule, Long uid) throws Exception {

	}

	@Override
	public void deleteSchedule(Long routeId, Long scheduleId, Long uid) throws Exception {

	}

	@Override
	public RouteActivityBean getScheduleActivity(Long activityId) throws Exception {
		RouteActivityBean activity = activityCrudOperate.get(activityId);

		if(activity != null){
			List<RouteActivityItemBean> items = activity.getItems();
			if(items != null){
				for(RouteActivityItemBean item : items){
					if(item.getResourceActivityItemId() == null) continue;
					item.setResourceActivityItem(activityItemCrudOperate.get(item.getResourceActivityItemId(), false));
				}
			}
		}

		return activity;
	}

	@Override
	public void saveScheduleActivity(Long routeId, RouteActivityBean activity, Long userId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);
		if(route == null){
			throw new Exception("No Route found for id=" + routeId);
		}

		activityCrudOperate.save(activity);
	}

	@Override
	public void deleteScheduleActivity(Long routeId, Long activityId, Long uid) throws Exception {

	}

	@Override
	public void deleteScheduleActivityItem(Long routeId, Long activityId, Long itemId, Long userId) throws Exception {

	}


	@Override
	public void saveRouteProvision(Long routeId, RouteProvisionBean provision, Long userId) throws Exception {
		RouteMainBean route = routeCrudOperate.get(routeId);
		if(route == null){
			throw new Exception("No Route found for id=" + routeId);
		}

		provisionCrudOperate.save(provision);
	}
	@Override
	public void deleteRouteProvision(Long routeId, Long provisionId, Long userId) throws Exception {

	}

	@Override
	public void saveRouteMainAndSchedules(RouteMainBean route, Long userId)
			throws Exception {
		// 如果存在日程，则先保存日程信息
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

		for (RouteScheduleBean scheduleBean : route.getSchedules()) {
			scheduleCrudOperate.save(scheduleBean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save RouteScheduleBean[%d] success.",
						scheduleBean.getId()));
			}
		}

		routeCrudOperate.save(route);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Save RouteMainBean[%d] success.",
					route.getId()));
		}
	}

	@Override
	public RouteMainBean cloneRoute(Long sourceId, RouteMainBean target, Long userId) throws Exception {
		this.saveRouteMainAndSchedules(target, userId);

		RouteMainBean source = this.getRoute(sourceId);
		if(source == null){
			throw new Exception("No route found for id=" + sourceId);
		}

		List<RouteProvisionBean> sProvisions = source.getProvisions();
		List<RouteProvisionBean> tProvisions = new ArrayList<>();
		if(sProvisions != null){
			for(RouteProvisionBean sProvision : sProvisions){
				RouteProvisionBean tProvision = (RouteProvisionBean) sProvision.clone();
				tProvision.setUpdatedUserId(userId);
				tProvision.setCreatedUserId(userId);
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
					tActivity.setUpdatedUserId(userId);
					tActivity.setCreatedUserId(userId);
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
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor, int limit) throws Exception {
		return null;
	}

	@Override
	public List<RouteMainBean> getRoutes(Long[] placeIds, int duration, Long nextCursor, int limit) throws Exception {
		return null;
	}
}
