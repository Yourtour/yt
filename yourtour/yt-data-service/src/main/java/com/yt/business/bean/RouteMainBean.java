package com.yt.business.bean;

import java.util.ArrayList;
import java.util.List;

import com.yt.business.BusinessBeanImpl;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

@HbaseTable(name = "T_ROUTE_MAIN")
@NodeEntity
@JsonRootName("route")
public class RouteMainBean extends BusinessBeanImpl {
	private static final long serialVersionUID = -2071225440268179136L;
	private static final String INDEX_NAME = "route";

	@HbaseColumn(name = "name")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String name; // 行程名称
	private String 	lineName; //线路名称
	private String 	reason; //推荐理由
	private String 	feature; //行程特点
	private String 	chargeMemo; //费用说明
	private String	withdrawMemo; //退改说明
	private String  useMemo; //使用说明
	private String  orderMemo; //预订须知

	@HbaseColumn(name = "sdt")
	private long startDate = 0; // 行程开始日期
	private long endDate = 0; // 行程结束日期
	private int  duration = 0; // 行程持续时间

	private String fromPlace; //出发地（冗余）
	private String toPlaces; //目的地（冗余）

	private int  adultNum;  //成人数
	private int  childNum;	//儿童数
	private int  olderNum; //老人数
	private int  budget; //预算

	private String imageUrl;
	private transient String impression;

	private String tags;  //标签，数据冗余
	private String bestTime; //最佳时间

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_FROM, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean fromPlaceBean = null; // 行程出发地点
	
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_TO, type = PlaceBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<PlaceBean> toPlaceBeans = null; //目的地

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteScheduleBean> schedules = null; // 行程包含的日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteProvisionBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteProvisionBean> provisions = null; // 行程包含的准备

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean owner = null; // 行程所有者

	private UserProfileBean user = null;

	public RouteMainBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public PlaceBean getFromPlaceBean() {
		return fromPlaceBean;
	}

	public void setFromPlaceBean(PlaceBean fromPlaceBean) {
		this.fromPlaceBean = fromPlaceBean;
	}

	public List<PlaceBean> getToPlaceBeans() {
		return toPlaceBeans;
	}

	public void setToPlaceBeans(List<PlaceBean> toPlaceBeans) {
		this.toPlaceBeans = toPlaceBeans;
	}

	public void setSchedules(List<RouteScheduleBean> schedules) {
		this.schedules = schedules;
	}

	public List<RouteScheduleBean> getSchedules() {
		if(schedules == null) schedules = new ArrayList<>();
		
		return schedules;
	}

	public void setProvisions(List<RouteProvisionBean> provisions) {
		this.provisions = provisions;
	}

	public List<RouteProvisionBean> getProvisions() {
		if(provisions == null) provisions = new ArrayList<>();
		
		return provisions;
	}

	public UserProfileBean getOwner() {
		return owner;
	}

	public void setOwner(UserProfileBean owner) {
		this.owner = owner;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public int getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(int adultNum) {
		this.adultNum = adultNum;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public int getOlderNum() {
		return olderNum;
	}

	public void setOlderNum(int olderNum) {
		this.olderNum = olderNum;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlaces() {
		return toPlaces;
	}

	public void setToPlaces(String toPlaces) {
		this.toPlaces = toPlaces;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getChargeMemo() {
		return chargeMemo;
	}

	public void setChargeMemo(String chargeMemo) {
		this.chargeMemo = chargeMemo;
	}

	public String getWithdrawMemo() {
		return withdrawMemo;
	}

	public void setWithdrawMemo(String withdrawMemo) {
		this.withdrawMemo = withdrawMemo;
	}

	public String getUseMemo() {
		return useMemo;
	}

	public void setUseMemo(String useMemo) {
		this.useMemo = useMemo;
	}

	public String getBestTime() {
		return bestTime;
	}

	public void setBestTime(String bestTime) {
		this.bestTime = bestTime;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}
