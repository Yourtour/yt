package com.yt.rest.resource;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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
public class HomeRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(HomeRestResource.class);

	@Autowired
	private IHomeService homeService;

	@Path("/home/recommend")
	@GET
	@SuppressWarnings("unchecked")
	public ResponseDataVO<RecommendInHomeVO> getRecommend(
			@Context HttpServletRequest request) {
		try {
			Map<String, Object> recommends = homeService.getRecommends();
			List<BannerBean> banners = (List<BannerBean>) recommends
					.get(IHomeService.KEY_BANNERS);
			List<RouteMainBean> routes = (List<RouteMainBean>) recommends
					.get(IHomeService.KEY_ROUTES);
			List<HotPlayingBean> hotPlayings = (List<HotPlayingBean>) recommends
					.get(IHomeService.KEY_HOTPLAYINGS);
			return new ResponseDataVO<RecommendInHomeVO>(
					RecommendInHomeVO.transform(banners, routes, hotPlayings));
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						"Fetch the recommends information in home page fail.",
						ex);
			}
			return new ResponseDataVO<RecommendInHomeVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * APP 启动调用接口
	 * 
	 * @param version
	 * @return
	 * @throws Exception
	 */
	@Path("/app/launch")
	@GET
	public ResponseDataVO<LaunchVO> launchApp(
			@QueryParam("version") String srcVersion,
			@Context HttpServletRequest request) throws Exception {
		super.fillUserInfo(request);
		String accessToken = request.getHeader("accessToken");
		if (accessToken == null || accessToken.isEmpty()) {
			// 第一次运行本系统
			accessToken = UUID.randomUUID().toString();
		}
		Map<String, Object> map = homeService.launch(accessToken, srcVersion);
		LaunchBean launch = (LaunchBean) map.get(IHomeService.KEY_LAUNCHBEAN);
		VersionBean versionBean = (VersionBean) map.get(IHomeService.KEY_VERSIONBEAN);
		ActivityBean activity = (ActivityBean) map.get(IHomeService.KEY_ACTIVITYBEAN);
		return new ResponseDataVO<LaunchVO>(LaunchVO.transform(launch, versionBean,
				activity));
	}
}
