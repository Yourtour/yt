package com.yt.rest.resource;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.HotPlayingBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.NewsBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.service.IBaseService;
import com.yt.business.service.IHomeService;
import com.yt.core.common.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.vo.HomeVO;
import com.yt.vo.LaunchVO;
import com.yt.vo.home.RecommendInHomeVO;

@Component
@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeRestResource {
	private static final Log LOG = LogFactory.getLog(HomeRestResource.class);

	@Autowired
	private IBaseService service;

	@Autowired
	private IHomeService homeService;

	@SuppressWarnings("unchecked")
	public ResponseDataVO<RecommendInHomeVO> getRecommend() {
		try {
			Map<String, Object> recommends = homeService.getRecommends();
			List<NewsBean> newBeans = (List<NewsBean>) recommends
					.get("newsBeans");
			List<RouteMainBean> routes = (List<RouteMainBean>) recommends
					.get("routes");
			List<HotPlayingBean> hotPlayings = (List<HotPlayingBean>) recommends
					.get("hotPlayings");
			return new ResponseDataVO<RecommendInHomeVO>(
					RecommendInHomeVO.transform(newBeans, routes, hotPlayings));
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
	 * 获取首页信息
	 * 
	 * @param placeId
	 * @param request
	 * @return
	 */
	@Path("/home")
	@GET
	public ResponseDataVO<HomeVO> getHomeInfo(
			@PathParam("placeId") String placeId,
			@Context HttpServletRequest request) {
		HomeVO homeVO = new HomeVO();

		return new ResponseDataVO<HomeVO>(homeVO);
	}

	/**
	 * APP 启动调用接口
	 * 
	 * @param accessId
	 * @param lastAccessDate
	 * @param version
	 * @return
	 * @throws Exception
	 */
	@Path("launch")
	@GET
	public ResponseDataVO<LaunchVO> launchApp(
			@PathParam("accessId") String accessId,
			@PathParam("lastAccessDate") Long lastAccessDate,
			@PathParam("version") String version) throws Exception {

		LaunchBean bean = this.service
				.launch(accessId, lastAccessDate, version);

		return new ResponseDataVO<LaunchVO>(LaunchVO.transform(bean));
	}
}
