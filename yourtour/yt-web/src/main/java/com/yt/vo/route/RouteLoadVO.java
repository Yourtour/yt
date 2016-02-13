package com.yt.vo.route;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.common.Constants;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.DateUtils;
import com.yt.core.utils.StringUtils;

public class RouteLoadVO implements Serializable {
	private static final long serialVersionUID = -5772201577521181151L;

	private static enum TYPE{Provision, ProvisionItem, Schedule, ScheduleItem, ScheduleJoin};
	
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
		//return route.getImageUrl();
		
		return "/resources/images/yunnan.jpg";
	}

	public int getDuration(){
		return route.getDuration();
	}

	public String getStartDate(){
		return DateUtils.formatDate(route.getStartDate());
	}

	public String getEndDate(){
		return DateUtils.formatDate(route.getEndDate());
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
		group.setTitle("准备事项");
		group.setType(TYPE.Provision);
		group.setFirst(true);
		provisions.add(group);

		List<RouteProvisionBean> beans = this.route.getProvisions();
		if(CollectionUtils.isNotEmpty(beans)) {
			for (RouteProvisionBean provisionBean : this.route.getProvisions()) {
				RouteSchedule provision = new RouteSchedule();
				provision.setParentId(group.getId());
				provision.setTitle(provisionBean.getTitle());
				provision.setMemo(provisionBean.getMemo());
				provision.setType(TYPE.ProvisionItem);
				provision.setIndex(provisionBean.getIndex());
				provision.setId(provisionBean.getGraphId().toString());
				provisions.add(provision);
			}

			provisions.get(provisions.size() -1 ).setLast(true);
		}

		return provisions;
	}
	
	private List<RouteSchedule> getActivities(){
		List<RouteSchedule> schedules = new ArrayList<>();

		int index = 1;
		List<RouteScheduleBean> scheduleBeans = this.route.getSchedules();
		if(CollectionUtils.isNotEmpty(scheduleBeans)) {
			Collections.sort(scheduleBeans, new Comparator<RouteScheduleBean>() {
				@Override
				public int compare(RouteScheduleBean o1, RouteScheduleBean o2) {
					return o1.getDate().compareTo(o2.getDate());
				}
			});

			RouteActivityBean activityBean = null;
			for (RouteScheduleBean scheduleBean : scheduleBeans) {
				RouteSchedule group = new RouteSchedule();
				group.setId(scheduleBean.getGraphId().toString());

				group.setTitle("DAY " + String.valueOf(index++));
				group.setStartTime(DateUtils.formatDate(scheduleBean.getDate()));
				group.setPlaces(scheduleBean.getPlaces());
				group.setMemo(scheduleBean.getMemo());
				group.setType(TYPE.Schedule);
				group.setDate(scheduleBean.getDate());
				group.setFirst(true);
				schedules.add(group);

				List<RouteActivityBean> activities = scheduleBean.getActivities();
				if (CollectionUtils.isNotEmpty(activities)) {
					Collections.sort(activities, new Comparator<RouteActivityBean>() {
						@Override
						public int compare(RouteActivityBean o1, RouteActivityBean o2) {
							return o1.getStartTime().compareTo(o2.getStartTime());
						}
					});

					for(int i  = 0; i < activities.size(); i++){
						activityBean = activities.get(i);

						RouteSchedule activity = new RouteSchedule();
						activity.setTitle(activityBean.getTitle());
						activity.setMemo(activityBean.getMemo());
						activity.setType(TYPE.ScheduleItem);
						activity.setParentId(group.getId());
						activity.setId(activityBean.getGraphId().toString());
						activity.setDate(activityBean.getDate());
						activity.setStartTime(getTime(activityBean.getStartTime()));
						activity.setEndTime(getTime(activityBean.getEndTime()));
						activity.setIndex(activityBean.getIndex());

						if (StringUtils.isNull(activityBean.getPrice()) || StringUtils.isNull(activityBean.getCurrency())) {
							activity.setPrice("无价格信息");
						} else {
							Constants.Currency currency = Constants.Currency.getCurrency(activityBean.getCurrency());
							activity.setPrice(currency.symbol + " " + activityBean.getPrice() + currency.unit + "/人");
						}

						ResourceBean resource = activityBean.getResource();
						if (resource != null) {
							activity.setImageUrl(activityBean.getResource().getImageUrl());
							activity.setResourceId(resource.getGraphId().toString());
							activity.setResourceType(resource.getType().toString());
							activity.setCommentNum(resource.getCommentNum());
							activity.setShareNum(resource.getShareNum());
							activity.setFavoriteNum(resource.getFavoriteNum());
							activity.setRankScore(resource.getRankScore());
						}

						if( i > 0){
							RouteSchedule traffic = new RouteSchedule();
							traffic.setType(TYPE.ScheduleJoin);
							traffic.setTitle("1.5km");
							traffic.setMemo("建议步行");
							schedules.add(traffic);
						}
						schedules.add(activity);
					}

					schedules.get(schedules.size() - 1).setLast(true);
				}
			}
		}

		return schedules;
	}
	
	private class RouteSchedule{
		private String 	id;
		private String  parentId;
		private String  resourceId;
		private String  resourceType;
		private String  imageUrl;
		private String 	title;
		private String	memo;
		private String  places;
		private TYPE 	type;
		private String 	status;
		private Long    date;
		private String 	startTime;
		private String 	endTime;
		private String  duration;
		private String 	address;
		private int 	index;
		private String 	price;
		private float rankScore;
		private int thumbupNum;
		private int favoriteNum;
		private int shareNum;
		private int commentNum;
		private boolean first = false;
		private boolean last = false;

		public RouteSchedule(){}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

		public String getResourceId() {
			return resourceId;
		}

		public void setResourceId(String resourceId) {
			this.resourceId = resourceId;
		}

		public String getResourceType() {
			return resourceType;
		}

		public void setResourceType(String resourceType) {
			this.resourceType = resourceType;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
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

		public Long getDate() {
			return date;
		}

		public void setDate(long date) {
			this.date = date;
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
			return StringUtils.isNull(memo)?"":memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public float getRankScore() {
			return rankScore;
		}

		public void setRankScore(float rankScore) {
			this.rankScore = rankScore;
		}

		public int getThumbupNum() {
			return thumbupNum;
		}

		public void setThumbupNum(int thumbupNum) {
			this.thumbupNum = thumbupNum;
		}

		public int getFavoriteNum() {
			return favoriteNum;
		}

		public void setFavoriteNum(int favoriteNum) {
			this.favoriteNum = favoriteNum;
		}

		public int getShareNum() {
			return shareNum;
		}

		public void setShareNum(int shareNum) {
			this.shareNum = shareNum;
		}

		public int getCommentNum() {
			return commentNum;
		}

		public void setCommentNum(int commentNum) {
			this.commentNum = commentNum;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public boolean isFirst() {
			return first;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public boolean isLast() {
			return last;
		}

		public void setLast(boolean last) {
			this.last = last;
		}
	}

	private String getTime(String value){
		if(StringUtils.isNull(value)) return "00:00";

		String[] values = value.split(" ");
		String time = null;
		if(values.length == 2){
			time = values[1];
		}else{
			time = values[0];
		}
		System.out.println(time);
		return time.substring(0, 5);
	}
}
