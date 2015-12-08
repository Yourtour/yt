package com.yt.business.repository;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.neo4j.repository.RouteBeanRepository;

@Component
public class RouteRepositoryImpl extends CrudAllInOneOperateImpl implements
		RouteRepository {
	private static final Log LOG = LogFactory.getLog(RouteRepositoryImpl.class);

	@Autowired
	private RouteBeanRepository repository;

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
		RouteMainBean route = (RouteMainBean)super.get(RouteMainBean.class, routeId);
		if (route != null) {
			List<RouteScheduleBean> schedules = route.getSchedules();
			if (schedules != null && schedules.size() > 0) {
				List<RouteScheduleBean> newSchedules = new Vector<RouteScheduleBean>(schedules.size());
				for (RouteScheduleBean schedule : schedules) {
					RouteScheduleBean newSchedule = (RouteScheduleBean)super.get(RouteScheduleBean.class, schedule.getGraphId());
					List<RouteActivityBean> activities = newSchedule.getActivities();
					if (activities != null && activities.size()>0) {
						List<RouteActivityBean> newActivities = new Vector<RouteActivityBean>(activities.size());
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
	 * com.yt.business.repository.RouteRepository#getRoutesByOwner(java.lang
	 * .Long)
	 */
	@Override
	public List<RouteMainBean> getRoutesByOwner(Long userId) throws Exception {
		List<RouteMainBean> routes = repository.getRoutesByOwner(userId);
		List<RouteMainBean> list = new Vector<RouteMainBean>(routes.size());
		for (RouteMainBean bean : routes) {
			bean = (RouteMainBean) super.loadRelations(bean);
			if (bean == null) {
				continue;
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

}
