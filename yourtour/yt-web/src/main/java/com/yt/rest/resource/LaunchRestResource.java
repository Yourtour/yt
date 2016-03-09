package com.yt.rest.resource;

import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.service.ILaunchService;
import com.yt.response.ResponseDataVO;
import com.yt.vo.LaunchVO;
import com.yt.vo.place.PlaceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("app")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LaunchRestResource {
	private static final Log LOG = LogFactory.getLog(LaunchRestResource.class);

	@Autowired
	private ILaunchService launchService;

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
		LaunchBean bean = this.launchService.launch(accessId, lastAccessDate, version);

		return new ResponseDataVO<LaunchVO>(LaunchVO.transform(bean));
	}
}
