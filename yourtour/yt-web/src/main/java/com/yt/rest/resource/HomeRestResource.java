package com.yt.rest.resource;

import com.yt.response.ResponseDataVO;
import com.yt.vo.home.HomeVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Component
@Path("/app/home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeRestResource {
	private static final Log LOG = LogFactory.getLog(HomeRestResource.class);

	@Path("place/{placeId}/query")
	@GET
	public ResponseDataVO<HomeVO> getHomeInfo(@PathParam("placeId") String placeId, @Context HttpServletRequest request) {
		HomeVO homeVO = new HomeVO();

		return new ResponseDataVO<HomeVO>(homeVO);
	}
}
