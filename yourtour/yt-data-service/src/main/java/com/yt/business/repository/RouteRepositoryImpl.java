package com.yt.business.repository;

import java.util.*;

import com.yt.business.bean.*;
import com.yt.business.neo4j.repository.*;
import com.yt.core.utils.DateUtils;
import com.yt.core.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.GroupRole;
import com.yt.business.neo4j.repository.RouteBeanRepository.OwnerRouteTuple;

@Component
public class RouteRepositoryImpl extends CrudAllInOneOperateImpl implements
		RouteRepository {
	private static final Log LOG = LogFactory.getLog(RouteRepositoryImpl.class);

	@Autowired
	private RouteBeanRepository repository;
	
	@Autowired
	private RouteActivityRepository activityRepository;

	@Autowired
	private ResourceActivityItemRepository resourceActivityItemRepository;

	@Autowired
	private ExpertServiceRepository expertServiceRepository;

	public RouteRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RouteRepository#getCompleteRoute(java.lang
	 * .Long)
	 */
	@Override
	public RouteMainBean getCompleteRoute(Long routeId) throws Exception {
		RouteMainBean route = (RouteMainBean) super.get(RouteMainBean.class,
				routeId);
		if (route != null) {
			List<RouteScheduleBean> schedules = route.getSchedules();
			if (schedules != null && schedules.size() > 0) {
				List<RouteScheduleBean> newSchedules = new Vector<RouteScheduleBean>(
						schedules.size());
				for (RouteScheduleBean schedule : schedules) {
					RouteScheduleBean newSchedule = (RouteScheduleBean) super
							.get(RouteScheduleBean.class, schedule.getGraphId());
					List<RouteActivityBean> activities = newSchedule
							.getActivities();
					if (activities != null && activities.size() > 0) {
						List<RouteActivityBean> newActivities = new Vector<RouteActivityBean>(
								activities.size());
						for (RouteActivityBean activity : activities) {
							RouteActivityBean newActivity = (RouteActivityBean) super
									.get(RouteActivityBean.class,
											activity.getGraphId());
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
	public RouteActivityBean getCompleteActivity(Long activityId) throws Exception {
		RouteActivityBean activity = (RouteActivityBean) super.get(RouteActivityBean.class,activityId);

		if(activity != null){
			List<RouteServiceBean> services = activity.getServices();
			if(services != null){
				for(RouteServiceBean service : services){
					if(service.getExpertServiceId() == null) continue;
					service.setService((ExpertServiceBean) this.get(ExpertServiceBean.class, service.getExpertServiceId(), false));
				}
			}

			List<RouteActivityItemBean> items = activity.getItems();
			if(items != null){
				for(RouteActivityItemBean item : items){
					if(item.getResourceActivityItemId() == null) continue;
					item.setResourceActivityItem((ResourceActivityItemBean) this.get(ResourceActivityItemBean.class, item.getResourceActivityItemId(), false));
				}
			}

		}

		return activity;
	}

	/*
             * (non-Javadoc)
             *
             * @see
             * com.yt.business.repository.RouteRepository#getRoutePeople(java.lang.Long,
             * com.yt.business.common.Constants.GroupRole)
             */
	@Override
	public List<UserProfileBean> getRouteMember(Long routeId) throws Exception {
		return repository.getRouteMember(routeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RouteRepository#getRoutesByOwner(java.lang
	 * .Long)
	 */
	@Override
	public List<RouteMainBean> getRoutesByOwner(Long userId) throws Exception {
		List<OwnerRouteTuple> routes = repository.getRoutesByOwner(userId);
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
	public void saveScheduleActivity(Long routeId, RouteActivityBean activity, String operator) throws Exception {
		RouteMainBean route = (RouteMainBean)this.get(RouteMainBean.class, routeId);
		if(route == null){
			throw new Exception("No Route found for id=" + routeId);
		}

		route.setStep(1);
		super.save(route, false, operator);

		super.save(activity, operator);
	}

	@Override
	public void saveRouteProvision(Long routeId, RouteProvisionBean provision, String operator) throws Exception {
		RouteMainBean route = (RouteMainBean)this.get(RouteMainBean.class, routeId);
		if(route == null){
			throw new Exception("No Route found for id=" + routeId);
		}

		route.setStep(1);
		super.save(route, false, operator);

		super.save(provision, operator);
	}

	/*
             * (non-Javadoc)
             *
             * @see
             * com.yt.business.repository.RouteRepository#saveRouteMainAndSchedules(
             * com.yt.business.bean.RouteMainBean, java.lang.String)
             */
	@Override
	public void saveRouteMainAndSchedules(RouteMainBean route, String operator)
			throws Exception {
		// 如果存在日程，则先保存日程信息
		boolean isNew = route.isNew();

		if(! isNew) {
			Long routeId = route.getGraphId();
			List<RouteScheduleBean> schedules = repository.getRouteSchedules(routeId);
			if(schedules != null){
				for(RouteScheduleBean schedule : schedules){
					super.delete(schedule);
				}
			}
		}

		for (RouteScheduleBean scheduleBean : route.getSchedules()) {
			super.save(scheduleBean, operator);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save RouteScheduleBean[%d] success.",
						scheduleBean.getGraphId()));
			}
		}

		super.save(route, operator);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Save RouteMainBean[%d] success.",
					route.getGraphId()));
		}
		/*for (RouteScheduleBean scheduleBean : route.getSchedules()) {
			super.save(scheduleBean, operator);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save RouteScheduleBean[%d] success.",
						scheduleBean.getGraphId()));
			}
		}*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RouteRepository#saveRoutePersonRelation(com
	 * .yt.business.bean.RouteMainBean, com.yt.business.bean.UserAccountBean,
	 * com.yt.business.common.Constants.GroupRole)
	 */
	@Override
	public void saveRoutePersonRelation(RouteMainBean route,
			UserAccountBean user, GroupRole groupRole) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("groupRole", groupRole.code);
		super.createRelation(route, user, Constants.RELATION_TYPE_HAS,
				Direction.OUTGOING, properties);
	}

	@Override
	public RouteActivityBean getRouteActivity(Long activityId) throws Exception {
		return (RouteActivityBean) this.get(RouteActivityBean.class, activityId);
	}

	@Override
	public List<RouteMainBean> getRecommendRoutes(Long[] placeIds) throws Exception {
		List<RouteMainBean> routes = new ArrayList<>();

		List<RouteTuple> tuples = this.repository.getRecommendRoutes(placeIds);
		if(tuples != null){
			RouteMainBean route = null;
			for(RouteTuple tuple : tuples){
				route = tuple.getRoute();
				route.setOwner(tuple.getOwner());

				routes.add(route);
			}
		}

		return routes;
	}

	@Override
	public RouteMainBean getRecommendRoute(Long routeId) throws Exception {
		RouteTuple tuple = this.repository.getRecommendRoute(routeId);
		if(tuple != null){
			RouteMainBean route = tuple.getRoute();
			route.setOwner(tuple.getOwner());

			return route;
		}

		return null;
	}

	@Override
	public RouteMainBean cloneRoute(Long sourceId, Long targetId, String userId) throws Exception {
		RouteMainBean source = this.getCompleteRoute(sourceId);
		if(source == null){
			throw new Exception("No route found for id=" + sourceId);
		}

		RouteMainBean target = this.getCompleteRoute(targetId);
		if(target == null){
			throw new Exception("No route found for id=" + targetId);
		}

		List<RouteProvisionBean> sProvisions = source.getProvisions();
		List<RouteProvisionBean> tProvisions = new ArrayList<>();
		if(sProvisions != null){
			for(RouteProvisionBean sProvision : sProvisions){
				RouteProvisionBean tProvision = (RouteProvisionBean) sProvision.clone();
				tProvision.setUpdatedUserId(userId);
				tProvision.setCreatedUserId(userId);
				tProvision.setRouteMain(target);

				this.save(tProvision, userId);
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

					this.save(tActivity, userId);
					tActivities.add(tActivity);
				}

				tSchedules.get(index).setActivities(tActivities);
			}
		}

		target.setStep(1);
		this.save(target, userId);

		return target;
	}
}
