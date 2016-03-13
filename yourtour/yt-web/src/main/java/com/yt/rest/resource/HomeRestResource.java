package com.yt.rest.resource;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.yt.utils.SessionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.BannerBean;
import com.yt.business.bean.HotPlayingBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.VersionBean;
import com.yt.business.service.IHomeService;
import com.yt.core.common.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.vo.home.LaunchVO;
import com.yt.vo.home.RecommendInHomeVO;

@Component
@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeRestResource {
	private static final Log LOG = LogFactory.getLog(HomeRestResource.class);

	@Autowired
	private IHomeService homeService;

	@Path("/home/{lastModifiedTime}")
	@GET
	public ResponseDataVO<RecommendInHomeVO> getHomeDate(@PathParam("lastModifiedTime") Long lastModifiedTime) throws Exception{
		Map<String, Object> recommends = homeService.getHomeData(SessionUtils.getCurrentLoginUser(), lastModifiedTime);
		List<BannerBean> banners = (List<BannerBean>) recommends
				.get(IHomeService.KEY_BANNERS);
		List<RouteMainBean> routes = (List<RouteMainBean>) recommends
				.get(IHomeService.KEY_ROUTES);
		List<HotPlayingBean> hotPlayings = (List<HotPlayingBean>) recommends
				.get(IHomeService.KEY_HOTPLAYINGS);
		return new ResponseDataVO<RecommendInHomeVO>(
				RecommendInHomeVO.transform(banners, routes, hotPlayings));
	}

	/**
	 *
	 */
	@Path("/{devType}/{version}/launch")
	@GET
	public ResponseDataVO<LaunchVO> launchApp(@PathParam("devType") String devType,
											  @PathParam("version") String version,
											  @Context HttpServletRequest request) throws Exception {
		String accessToken = request.getHeader("Access-Token");
		Map<String, Object> map = homeService.launch(accessToken, devType, version);
		LaunchBean launch = (LaunchBean) map.get(IHomeService.KEY_LAUNCHBEAN);
		VersionBean versionBean = (VersionBean) map.get(IHomeService.KEY_VERSIONBEAN);
		ActivityBean activity = (ActivityBean) map.get(IHomeService.KEY_ACTIVITYBEAN);
		return new ResponseDataVO<LaunchVO>(LaunchVO.transform(launch, versionBean, activity));
	}
}
