package com.yt.rest.resource;

import com.yt.business.bean.LaunchBean;
import com.yt.business.service.IBaseService;
import com.yt.response.ResponseDataVO;
import com.yt.vo.LaunchVO;
import com.yt.vo.HomeVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Component
@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeRestResource {
	private static final Log LOG = LogFactory.getLog(HomeRestResource.class);

	@Autowired
	private IBaseService service;

	/**
	 * 获取首页信息
	 * @param placeId
	 * @param request
	 * @return
	 */
	@Path("/home")
	@GET
	public ResponseDataVO<HomeVO> getHomeInfo(@PathParam("placeId") String placeId, @Context HttpServletRequest request) {
		HomeVO homeVO = new HomeVO();

		return new ResponseDataVO<HomeVO>(homeVO);
	}

	/**
	 * APP 启动调用接口
	 * @param accessId
	 * @param lastAccessDate
	 * @param version
	 * @return
	 * @throws Exception
	 */
	@Path("launch/{accessId}/{lastAccessDate}/{version}")
	@GET
	public ResponseDataVO<LaunchVO> launchApp(@PathParam("accessId") String accessId,
											  @PathParam("lastAccessDate") Long lastAccessDate,
											  @PathParam("version") String version) throws Exception {

		LaunchBean bean = this.service.launch(accessId, lastAccessDate, version);

		return new ResponseDataVO<LaunchVO>(LaunchVO.transform(bean));
	}
}
