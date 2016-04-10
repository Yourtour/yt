package com.yt.business.bean;

import java.util.ArrayList;
import java.util.List;

import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

@HbaseTable(name = "T_ROUTE_MAIN")
@NodeEntity
@JsonRootName("route")
public class RouteMainBean extends SocialBeanImpl implements Cloneable{
	private static final long serialVersionUID = -2071225440268179136L;
	private static final String INDEX_NAME = "route";

	public static enum Status{
		DRAFT, //草稿
		PUBLISHED, //发布
		WITHDRAW  //撤回
	}

	@HbaseColumn(name = "name")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String name; // 行程名称
	private String 	lineName; //线路名称
	private String imageUrl;

	@HbaseColumn(name = "sdt")
	private Long startDate = null; // 行程开始日期
	private Long endDate = null; // 行程结束日期
	private int  duration = 0; // 行程持续时间

	private String fromPlace; //出发地（冗余）
	private String toPlaces; //目的地（冗余）

	private String 	feature; //特色推荐
	private String  reason;  //推荐理由
	private String  suitable;  //适合人群
	private String  promise;  //服务承诺
	private String  attentions;  //注意事项
	private String	provisions; //准备事项

	private String  charge; //费用
	private String	chargeIncludes; //费用包括
	private String  chargeExcludes; //费用不包括

	private int  adultNum;  //成人数
	private int  childNum;	//儿童数
	private int  olderNum; //老人数

	private String tags;  //标签，数据冗余

	private Status status;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_FROM, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean fromPlaceBean = null; // 行程出发地点

	private transient PlaceBean startPlaceBean = null; //行程起始地点
	
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_TO, type = PlaceBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<PlaceBean> toPlaceBeans = null; //目的地

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = DictBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<DictBean> tagBeans = null; //标签

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteScheduleBean> schedules = null; // 行程包含的日程

	private transient UserProfileBean user = null; // 行程相关人,缺省可以表示行程创建者

	public RouteMainBean() {
		super();
	}

	public RouteMainBean(Long id) {
		super(id);
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

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
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

	public PlaceBean getStartPlaceBean() {
		return startPlaceBean;
	}

	public void setStartPlaceBean(PlaceBean startPlaceBean) {
		this.startPlaceBean = startPlaceBean;
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

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
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

	public List<DictBean> getTagBeans() {
		return tagBeans;
	}

	public void setTagBeans(List<DictBean> tagBeans) {
		this.tagBeans = tagBeans;
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

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSuitable() {
		return suitable;
	}

	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}

	public String getPromise() {
		return promise;
	}

	public void setPromise(String promise) {
		this.promise = promise;
	}

	public String getAttentions() {
		return attentions;
	}

	public void setAttentions(String attentions) {
		this.attentions = attentions;
	}

	public String getProvisions() {
		return provisions;
	}

	public void setProvisions(String provisions) {
		this.provisions = provisions;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getChargeIncludes() {
		return chargeIncludes;
	}

	public void setChargeIncludes(String chargeIncludes) {
		this.chargeIncludes = chargeIncludes;
	}

	public String getChargeExcludes() {
		return chargeExcludes;
	}

	public void setChargeExcludes(String chargeExcludes) {
		this.chargeExcludes = chargeExcludes;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}

	public Status getStatus() {
		return status == null ? Status.PUBLISHED : status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		RouteMainBean clone = (RouteMainBean) super.clone();
		clone.setId(-1l);
		clone.setRowKey("");

		if(this.startPlaceBean != null) {
			clone.startPlaceBean = new PlaceBean(this.startPlaceBean.getId());
		}

		List<PlaceBean> cloneToPlaces = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(toPlaceBeans)){
			for(PlaceBean place : toPlaceBeans){
				cloneToPlaces.add(new PlaceBean(place.getId()));
			}

			clone.toPlaceBeans = cloneToPlaces;
		}

		List<RouteScheduleBean> cloneSchedules = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(schedules)){
			RouteScheduleBean cloneSchedule = null;
			for(RouteScheduleBean scheduleBean : schedules){
				cloneSchedule = (RouteScheduleBean) scheduleBean.clone();
				cloneSchedule.setRouteMain(clone);
				cloneSchedules.add(cloneSchedule);
			}

			clone.schedules = cloneSchedules;
		}

		return clone;
	}
}
