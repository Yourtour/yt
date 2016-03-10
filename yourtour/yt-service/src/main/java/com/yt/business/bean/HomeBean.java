package com.yt.business.bean;

import com.yt.business.BusinessBeanImpl;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.AlongIntentionType;
import com.yt.business.common.Constants.Status;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.List;

/**
 * 该实体定义了有服务层获取的首页数据
 * 
 * @author Tony.Zhang
 * 
 */
public class HomeBean extends BusinessBeanImpl {
	private static final long serialVersionUID = -3433522673262851121L;

	private List<RouteMainBean> recommendedRoutes;  //推荐行程

	private List<RouteMainBean> hotRoutes;  //热门行程（大家都在玩的行程）

	private List<ConsultBean> consults;  //资讯信息

	private List<BannerBean> banners; //Banner信息

	public HomeBean() {
		super();
	}

	public List<RouteMainBean> getRecommendedRoutes() {
		return recommendedRoutes;
	}

	public void setRecommendedRoutes(List<RouteMainBean> recommendedRoutes) {
		this.recommendedRoutes = recommendedRoutes;
	}

	public List<RouteMainBean> getHotRoutes() {
		return hotRoutes;
	}

	public void setHotRoutes(List<RouteMainBean> hotRoutes) {
		this.hotRoutes = hotRoutes;
	}

	public List<ConsultBean> getConsults() {
		return consults;
	}

	public void setConsults(List<ConsultBean> consults) {
		this.consults = consults;
	}

	public List<BannerBean> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerBean> banners) {
		this.banners = banners;
	}
}
