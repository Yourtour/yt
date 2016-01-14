package com.yt.business.neo4j.repository;

import java.util.List;

import com.yt.business.bean.RouteScheduleBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;

public interface RouteBeanRepository extends GraphRepository<RouteMainBean> {

	@QueryResult
	public class OwnerRouteTuple {
		@ResultColumn("r.imageUrl")
		public String imageUrl;
		@ResultColumn("r.impression")
		public String impression;
		@ResultColumn("route")
		public RouteMainBean route;

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getImpression() {
			return impression;
		}

		public void setImpression(String impression) {
			this.impression = impression;
		}

		public RouteMainBean getRoute() {
			return route;
		}

		public void setRoute(RouteMainBean route) {
			this.route = route;
		}
	}

	/**
	 * 根据指定的用户，返回该用户拥有的行程，同时返回包含在关系中的imageUrl和impression属性。
	 *
	 * @param userId
	 *            用户Id
	 * @return 指定用户拥有的行程、关系属性元组列表
	 */
	@Query("START owner=node({0}) MATCH owner<-[r:HAS]-(route:RouteMainBean) RETURN r.imageUrl, r.impression, route")
	public List<OwnerRouteTuple> getRoutesByOwner(Long userId);

	/**
	 * 根据指定的行程和成员角色，返回该行程中的用户。
	 *
	 * @param routeId
	 *            行程ID
	 * @param groupRole
	 *            成员角色
	 * @return 用户列表
	 */
	@Query("START route=node({0}) MATCH route -- (user:UserProfileBean) RETURN user")
	public List<UserProfileBean> getRouteMember(Long routeId);

	/**
	 *
	 * @param routeId
	 * @return
	 */
	@Query("START route=node({0}) MATCH route -- (schedule:RouteScheduleBean) delete schedule")
	public void deleteRouteSchedule(Long routeId);

	/**
	 *
	 * @param routeId
	 * @return
	 */
	@Query("START route=node({0}) MATCH route -- (schedules:RouteScheduleBean) return schedules")
	public List<RouteScheduleBean> getRouteSchedules(Long routeId);

	/**
	 *
	 * @param placeIds
	 * @return
	 */
	@Query("MATCH (expert:ExpertBean)<-[:IS]-(owner:UserProfileBean)-[:RECOMMEND]->(route) RETURN expert, owner, route")
	public List<RouteTuple> getRecommendRoutes(Long[] placeIds);

	/**
	 *
	 * @param routeId
	 * @return
	 */
	@Query("START route=node({0}) MATCH (expert:ExpertBean)<-[:IS]-(owner:UserProfileBean)-[:RECOMMEND]->(route) RETURN expert, owner, route")
	public RouteTuple getRecommendRoute(Long routeId);
}
