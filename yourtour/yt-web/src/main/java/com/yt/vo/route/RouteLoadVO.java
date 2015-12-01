package com.yt.vo.route;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.DateUtils;

public class RouteLoadVO implements Serializable {
	private static final long serialVersionUID = -5772201577521181151L;

	private static enum TYPE{Provision, ProvisionItem, Schedule, ScheduleItem};
	
	private RouteMainBean route;
	
	public RouteLoadVO(RouteMainBean route) {
		this.route = route;
	}
	
	public String getId(){
		return this.route.getGraphId().toString();
	}
	
	public String getName() {
		return route.getName();
	}

	public String getLineName() {
		return route.getLineName();
	}

	public String getImageUrl() {
		return route.getImageUrl();
	}
	
	public List<RouteSchedule> getSchedules(){
		List<RouteSchedule> schedules = new ArrayList<>();
		
		schedules.addAll(this.getProvisions());
		
		schedules.addAll(this.getActivities());
		
		return schedules;
	}
	
	private List<RouteSchedule> getProvisions(){
		List<RouteSchedule> provisions = new ArrayList<>();
		
		RouteSchedule group = new RouteSchedule();
		group.setId("0");
		group.setTitle("游前准备");
		group.setType(TYPE.Provision);
		provisions.add(group);
		
		for(RouteProvisionBean provisionBean : this.route.getProvisions()){
			RouteSchedule provision = new RouteSchedule();
			provision.setTitle(provisionBean.getTitle());
			provision.setMemo(provisionBean.getMemo());
			provision.setType(TYPE.ProvisionItem);
			provision.setId(provisionBean.getGraphId().toString());
			provisions.add(provision);
		}
		return provisions;
	}
	
	private List<RouteSchedule> getActivities(){
		List<RouteSchedule> schedules = new ArrayList<>();
		
		for(RouteScheduleBean scheduleBean : this.route.getSchedules()){
			RouteSchedule group = new RouteSchedule();
			group.setId(scheduleBean.getGraphId().toString());
			group.setTitle(DateUtils.formatDate(scheduleBean.getDate()));
			group.setStartTime(DateUtils.formatDate(scheduleBean.getDate()));
			group.setPlaces(scheduleBean.getPlaces());
			group.setMemo(scheduleBean.getMemo());
			group.setType(TYPE.Schedule);
			
			schedules.add(group);
			
			if(CollectionUtils.isNotEmpty(scheduleBean.getActivities())){
				for(RouteActivityBean activityBean : scheduleBean.getActivities()){
					RouteSchedule activity = new RouteSchedule();
					activity.setTitle(activityBean.getName());
					activity.setMemo(activityBean.getMemo());
					group.setType(TYPE.ScheduleItem);
					activity.setId(activityBean.getGraphId().toString());
					schedules.add(activity);
				}
			}
		}
		
		return schedules;
	}
	
	private class RouteSchedule{
		private String 	id;
		private String 	title;
		private String	memo;
		private String  places;
		private TYPE 	type;
		private String 	status;
		private String 	startTime;
		private String 	endTime;
		private String 	duration;
		private String 	address;
		
		public RouteSchedule(){}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getType() {
			return type.toString();
		}

		public void setType(TYPE type) {
			this.type = type;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPlaces() {
			return places;
		}

		public void setPlaces(String places) {
			this.places = places;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}
		
	}
}
