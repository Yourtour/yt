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
import com.yt.vo.member.UserVO;
import com.yt.vo.route.RouteMemberVO;

@Component
@Path("route/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberRestResource extends BaseRestResource{
	private static final Log LOG = LogFactory.getLog(MemberRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private RouteRepository routeRepository;

	/**
	 * 获取行程伙伴
	 * @param request
	 * @return
	 */
	@GET
	@Path("/{id}/query")
	public ResponseDataVO<List<RouteMemberVO>> getRouteMembers(@PathParam("id") String routeId, @Context HttpServletRequest request) {
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
	 * @param member
	 * @return
	 */
	@GET
	@Path("/{routeId}/{memberId}/delete")
	public ResponseVO deleteRouteMember(@Context HttpServletRequest request, RouteMemberVO member){
		return null;
	}
}

