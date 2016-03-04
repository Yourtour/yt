package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.RouteRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.vo.route.RouteMemberVO;
import com.yt.vo.route.RouteSettingVO;

@Component
@Path("/app/route/{routeId}/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(MemberRestResource.class);

	@Autowired
	private RouteRepository routeRepository;

	/**
	 * 获取行程伙伴
	 * @param request
	 * @return
	 */
	@GET
	@Path("/query")
	public ResponseDataVO<List<RouteMemberVO>> getRouteMembers(@PathParam("routeId") String routeId, @Context HttpServletRequest request) {
		try{
			List<RouteMemberVO> members = new ArrayList<>();
			
			Long rid = Long.valueOf(routeId);
			List<UserProfileBean> users = routeRepository.getRouteMember(rid);
			if(users != null){
				int index = 0;
				for(UserProfileBean user : users){
					RouteMemberVO vo = new RouteMemberVO(user);
					if(index == 0){
						vo.setRole(Constants.GroupRole.LEADER.code.toLowerCase());
					}
					
					if(index == 1){
						vo.setRole(Constants.GroupRole.EXPERT.code.toLowerCase());
					}
					
					members.add(vo);
					index++;
				}
			}
			return new ResponseDataVO<List<RouteMemberVO>>(members);
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseDataVO<List<RouteMemberVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 添加伙伴
	 * @param request
	 * @param member
	 * @return
	 */
	@POST
	@Path("/save")
	public ResponseVO addRouteMember(@Context HttpServletRequest request, RouteMemberVO member){
		try{
			long routeId = member.getRouteId();
			RouteMainBean route = (RouteMainBean) routeRepository.get(RouteMainBean.class, routeId, false);
			if(route == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}
			
			long userId = member.getUserId();
			UserProfileBean user = (UserProfileBean)routeRepository.get(UserProfileBean.class, userId, false);
			if(user == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}
			
			Map<String,Object> props = new HashMap<String, Object>();
			props.put("role", member.getRole());
			routeRepository.createRelation(route, user, Constants.RELATION_TYPE_PARTICIPATE, Direction.INCOMING,props);
			
			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when registering user account.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
	

	/**
	 * 删除伙伴
	 * @param request
	 * @return
	 */
	@GET
	@Path("/{userId}/delete")
	public ResponseVO deleteRouteMember(@Context HttpServletRequest request, @PathParam("userId") String userId, @PathParam("routeId") String routeId){
		try{
			RouteMainBean route = (RouteMainBean) routeRepository.get(RouteMainBean.class, Long.valueOf(routeId), false);
			if(route == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}
			
			UserProfileBean user = (UserProfileBean)routeRepository.get(UserProfileBean.class, Long.valueOf(userId), false);
			if(user == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}
			
			routeRepository.deleteRelation(route, user, Constants.RELATION_TYPE_PARTICIPATE);
			
			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when registering user account.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
	
	/**
	 * 添加伙伴
	 * @param request
	 * @param setting
	 * @return
	 */
	@POST
	@Path("/setting/save")
	public ResponseVO saveRouteSetting(@Context HttpServletRequest request, RouteSettingVO setting){
		try{
			long routeId = setting.getRouteId();
			RouteMainBean route = (RouteMainBean) routeRepository.get(RouteMainBean.class, routeId, false);
			if(route == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}
			
			long userId = setting.getUserId();
			UserProfileBean user = (UserProfileBean)routeRepository.get(UserProfileBean.class, userId, false);
			if(user == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}	
			
			Map<String,Object> props = routeRepository.getRelation(user, route, Constants.RELATION_TYPE_PARTICIPATE);
			if(props == null) props = new HashMap<>();
			
			props.put(setting.getAttr(), setting.getAttrValue());
			routeRepository.createRelation(route, user, Constants.RELATION_TYPE_HAS, Direction.OUTGOING,props);
			
			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when registering user account.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}