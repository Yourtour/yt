package com.yt.business.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.GroupRole;
import com.yt.business.neo4j.repository.RouteActivityRepository;
import com.yt.business.neo4j.repository.RouteActivityTuple;
import com.yt.business.neo4j.repository.RouteBeanRepository;

@Component
public class RouteRepositoryImpl extends CrudAllInOneOperateImpl implements
		RouteRepository {
	private static final Log LOG = LogFactory.getLog(RouteRepositoryImpl.class);

	@Autowired
	private RouteBeanRepository repository;
	
	@Autowired
	private RouteActivityRepository activityRepository;

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
							RouteActivityBean newActivity = (RouteActivityBean)super.get(RouteActivityBean.class, activity.getGraphId());
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
		UserProfileBean profileBean = (UserProfileBean) super.get(UserProfileBean.class,
				userId, false);
		List<RouteMainBean> routes = repository.getRoutesByOwner(userId);
		List<RouteMainBean> list = new Vector<RouteMainBean>(routes.size());
		Map<String, Object> relation = null;
		for (RouteMainBean bean : routes) {
			relation = super.getRelation(bean, profileBean,Constants.RELATION_TYPE_HAS);
			if(relation != null){
				if(relation.get("imageUrl") != null){
					bean.setImageUrl(relation.get("imageUrl").toString());
				}
				
				if(relation.get("impression") != null)
					bean.setImpression(relation.get("impression").toString());
			}
					
			list.add(bean);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Found %d RouteMainBean by UserBean(%d)",
					list.size(), userId));
		}
		return list;
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
		RouteActivityTuple tuple = activityRepository.getRouteActivity(activityId);
		
		tuple.getActivity().setResource(tuple.getResource());
		
		return tuple.getActivity();
	}
}
