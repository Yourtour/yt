package com.yt.rest.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.service.IUserService;
import com.yt.response.ResponseVO;

@Component
@Path("app/common")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommonRestResource extends RestResource {

	@Autowired
	private IUserService userService;

	@GET
	@Path("/collect")
	public ResponseVO collectSomething(@QueryParam("type") String type,
			@QueryParam("id") long id, @Context HttpServletRequest request)
			throws Exception {
		long userId = super.getCurrentUserId(request);
		userService.collectSomething(userId, type, id);
		return new ResponseVO();
	}

	@GET
	@Path("/uncollect")
	public ResponseVO uncollectSomething(@QueryParam("type") String type,
			@QueryParam("id") long id, @Context HttpServletRequest request)
			throws Exception {
		long userId = super.getCurrentUserId(request);
		userService.uncollectSomething(userId, type, id);
		return new ResponseVO();
	}

	@GET
	@Path("/watch")
	public ResponseVO watchSomeone(@QueryParam("id") long id,
			@Context HttpServletRequest request) throws Exception {
		long userId = super.getCurrentUserId(request);
		userService.watchSomeone(userId, id);
		return new ResponseVO();
	}

	@GET
	@Path("/unwatch")
	public ResponseVO unwatchSomeone(@QueryParam("id") long id,
			@Context HttpServletRequest request) throws Exception {
		long userId = super.getCurrentUserId(request);
		userService.unwatchSomeone(userId, id);
		return new ResponseVO();
	}

}
