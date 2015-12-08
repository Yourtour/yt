package com.yt.rest.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.repository.RouteRepository;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
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
	public ResponseDataVO<List<RouteMemberVO>> getRouteMembers(@Context HttpServletRequest request) {
		return null;
	}

	/**
	 * 添加伙伴
	 * @param request
	 * @param member
	 * @return
	 */
	@GET
	@Path("/save")
	public ResponseVO addRouteMember(@Context HttpServletRequest request, RouteMemberVO member){
		return null;
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
