package com.yt.rest.resource;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.service.IPlaceService;
import com.yt.response.ResponseDataVO;
import com.yt.vo.member.ExpertVO;
import com.yt.vo.place.PlaceVO;
import com.yt.vo.resource.ResourceVO;
import com.yt.vo.route.RouteVO;

@Component
@Path("app/places")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceRestResource {
	private static final Log LOG = LogFactory.getLog(PlaceRestResource.class);
	private static final int DEFAULT_LIMIT = 100;

	@Autowired
	private IPlaceService placeService;

	/**
	 * 获取所有的目的地信息
	 * 
	 * @return
	 */
	@GET
	public ResponseDataVO<List<PlaceVO>> getAllPlaces() throws Exception {
		List<PlaceVO> roots = new Vector<PlaceVO>();
		List<PlaceBean> places = placeService.getAllPlaces();
		for (PlaceBean place : places) {
			if (place == null) {
				continue;
			}
			roots.add(PlaceVO.transform(place));
		}
		return new ResponseDataVO<List<PlaceVO>>(roots);
	}

	/**
	 * 获取所有推荐的目的地清单
	 * 
	 * @return
	 */
	@GET
	@Path("/recommend")
	public ResponseDataVO<List<PlaceVO>> getRecommendPlaces() throws Exception {
		List<PlaceBean> beans = placeService.getRecommendPlaces();
		List<PlaceVO> placeVOs = new Vector<PlaceVO>();
		for (PlaceBean bean : beans) {
			if (bean == null) {
				continue;
			}
			placeVOs.add(PlaceVO.transform(bean));
		}
		return new ResponseDataVO<List<PlaceVO>>(placeVOs);
	}

	/**
	 * 获取目的地信息
	 * 
	 * @param placeId
	 * @return
	 */
	@GET
	@Path("/{placeId}")
	public ResponseDataVO<PlaceVO> getMainInfo(
			@PathParam("placeId") Long placeId) throws Exception {
		PlaceBean place = placeService.getPlace(placeId);
		PlaceVO vo = PlaceVO.transform(place);
		return new ResponseDataVO<PlaceVO>(vo);
	}

	@GET
	@Path("/{placeId}/experts")
	public ResponseDataVO<List<ExpertVO>> getExpertsAtPlace(
			@PathParam("placeId") Long placeId,
			@QueryParam("nextCursor") Long nextCursor,
			@QueryParam("limit") int limit) throws Exception {
		if (nextCursor == null) {
			nextCursor = new Long(0);
		}
		if (limit <= 0) {
			limit = DEFAULT_LIMIT;
		}
		List<ExpertBean> experts = placeService.getExperts(placeId, nextCursor,
				limit);
		List<ExpertVO> expertVOs = new Vector<ExpertVO>();
		for (ExpertBean expert : experts) {
			if (expert == null) {
				continue;
			}
			expertVOs.add(ExpertVO.transform(expert));
		}
		return new ResponseDataVO<List<ExpertVO>>(expertVOs);
	}

	@GET
	@Path("/{placeId}/resources")
	public ResponseDataVO<List<ResourceVO>> getResourcesAtPlace(
			@PathParam("placeId") Long placeId,
			@QueryParam("nextCursor") Long nextCursor,
			@QueryParam("limit") int limit) throws Exception {
		if (nextCursor == null) {
			nextCursor = new Long(0);
		}
		if (limit <= 0) {
			limit = DEFAULT_LIMIT;
		}
		List<ResourceBean> resources = placeService.getResources(placeId,
				nextCursor, limit);
		List<ResourceVO> resourceVOs = new Vector<ResourceVO>();
		for (ResourceBean resource : resources) {
			if (resource == null) {
				continue;
			}
			resourceVOs.add(ResourceVO.transform(resource));
		}
		return new ResponseDataVO<List<ResourceVO>>(resourceVOs);
	}

	@GET
	@Path("/{placeId}/routes")
	public ResponseDataVO<List<RouteVO>> getRoutesAtPlace(
			@PathParam("placeId") Long placeId,
			@QueryParam("nextCursor") Long nextCursor,
			@QueryParam("limit") int limit) throws Exception {
		if (nextCursor == null) {
			nextCursor = new Long(0);
		}
		if (limit <= 0) {
			limit = DEFAULT_LIMIT;
		}
		List<RouteMainBean> routes = placeService.getRoutes(placeId,
				nextCursor, limit);
		List<RouteVO> routeVOs = new Vector<RouteVO>();
		for (RouteMainBean route : routes) {
			if (route == null) {
				continue;
			}
			routeVOs.add(RouteVO.transform(route));
		}
		return new ResponseDataVO<List<RouteVO>>(routeVOs);
	}
}
