package com.yt.rest.resource;

import java.util.List;
import java.util.Map;

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
import com.yt.business.bean.ContentBean;
import com.yt.business.bean.DiscoverBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.VersionBean;
import com.yt.business.service.IHomeService;
import com.yt.response.ResponseDataVO;
import com.yt.utils.SessionUtils;
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

	/**
	 * 获取首页数据
	 * 
	 * @param lastModifiedTime
	 *            首页上次修改数据，用于判断客户端缓存的首页数据在服务器端是否改变，如改变返回最新的首页数据，否则
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Path("/home")
	@GET
	public ResponseDataVO<RecommendInHomeVO> getHomeDate(
			@QueryParam("lastModifiedTime") Long lastModifiedTime)
			throws Exception {
		Map<String, Object> recommends = homeService.getHomeData(
				SessionUtils.getCurrentLoginUser(), lastModifiedTime);

		List<BannerBean> banners = (List<BannerBean>) recommends
				.get(IHomeService.KEY_BANNERS);
		List<ContentBean> routes = (List<ContentBean>) recommends
				.get(IHomeService.KEY_YT_RECOMMENDS);
		List<DiscoverBean> hotPlayings = (List<DiscoverBean>) recommends
				.get(IHomeService.KEY_DISCOVERS);

		return new ResponseDataVO<RecommendInHomeVO>(
				RecommendInHomeVO.transform(banners, routes, hotPlayings));
	}

	/**
	 * 客户端启动调用接口
	 * 
	 * @param devType
	 *            设备类型，目前为Android和IOS
	 * @param appType
	 *            APP类型, 达人版和游客版
	 * @param version
	 *            当前客户端版本，为三段式
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Path("/launch")
	@GET
	public ResponseDataVO<LaunchVO> launchApp(
			@QueryParam("devType") String devType,
			@QueryParam("appType") String appType,
			@QueryParam("version") String version,
			@Context HttpServletRequest request) throws Exception {
		String accessToken = request.getHeader("Access-Token");
		String userToken = request.getHeader("User-Token");
		long userId = -1;
		try {
			userId = Long.valueOf(userToken);
		} catch (Exception ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The UserToken[%s]'s type is not Long.",
						userToken));
			}
		}
		Map<String, Object> map = homeService.launch(userId, accessToken,
				devType, appType, version);

		LaunchBean launch = (LaunchBean) map.get(IHomeService.KEY_LAUNCHBEAN);
		VersionBean versionBean = (VersionBean) map
				.get(IHomeService.KEY_VERSIONBEAN);
		ActivityBean activity = (ActivityBean) map
				.get(IHomeService.KEY_ACTIVITYBEAN);
		return new ResponseDataVO<LaunchVO>(LaunchVO.transform(launch,
				versionBean, activity));
	}
}
