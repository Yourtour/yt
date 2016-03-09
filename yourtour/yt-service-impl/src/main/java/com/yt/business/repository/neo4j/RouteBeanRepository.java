package com.yt.business.repository.neo4j;

import java.util.List;

import com.yt.business.bean.RouteMemberBean;
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

		@ResultColumn("r.permission")
		public String permission; //R:只读 W:修改

		@ResultColumn("r.role") //参照 Constants.GroupRole枚举定义
		private String role;

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

		public String getPermission() {
			return permission;
		}

		public void setPermission(String permission) {
			this.permission = permission;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public RouteMainBean getRoute() {
			return route;
		}

		public void setRoute(RouteMainBean route) {
			this.route = route;
		}
	}

	/**
	 * 获取和指定用户相关的行程。
	 * @param userId
	 * @return
	 */
	@Query("START user=node({0}) MATCH user-[r:MEMBER]->(route:RouteMainBean) where r.role=~{4} RETURN r.imageUrl, r.impression, r.permission, r.role, route")
	public List<OwnerRouteTuple> getRoutes(Long userId, Long startIndex, int limit, String roles);

	/**
	 * 获取达人推荐的行程
	 * @param userId
	 * @return
	 */
	@Query("START user=node({0}) MATCH user-[r:RECOMMEND]->(route:RouteMainBean) RETURN route")
	public List<RouteMainBean> getRecommendRoutes(Long userId);

	/**
	 * 获取目的地行程
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START user=node({0}) MATCH user-[r:RECOMMEND]->(route:RouteMainBean) RETURN route, user")
	public List<RouteTuple> getRecommendRoutes(Long placeId, Long nextCursor, int limit);

	/**
	 * 获取目的地行程
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@Query("START user=node({0}) MATCH user-[r:RECOMMEND]->(route:RouteMainBean) RETURN route, user")
	public List<RouteTuple> getRecommendRoutes(Long[] placeId, int duration, Long nextCursor, int limit);

	 /**
	 * 根据指定的行程和成员角色，返回该行程中的用户。
	 * @param routeId
	 * @return
	 */
	@Query("START route=node({0}) MATCH route -[r:MEMBER]- (user:UserProfileBean) RETURN r.role, user")
	public List<RouteMemberBean> getRouteMember(Long routeId);

	/**
	 *
	 * @param routeId
	 * @return
	 */
	@Query("START route=node({0}) MATCH route -- (schedules:RouteScheduleBean) return schedules")
	public List<RouteScheduleBean> getRouteSchedules(Long routeId);
}
