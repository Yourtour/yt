package com.yt.oms.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.service.IResourceService;
import com.yt.oms.vo.resource.HotelResourceVO;
import com.yt.oms.vo.resource.RestaurantResourceVO;
import com.yt.oms.vo.resource.SceneResourceVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.rest.resource.RestResource;

@Component
@Path("/resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceRestResource extends RestResource {
	@Autowired
	private IResourceService resourceService;

	@GET
	@Path("/hotels")
	public ResponsePagingDataVO<List<HotelResourceVO>> getHotels()
			throws Exception {
		// TODO
		return null;
	}

	@GET
	@Path("/hotels/{hotelId}")
	public ResponseDataVO<HotelResourceVO> getHotel(
			@PathParam("hotelId") Long hotelId) throws Exception {
		ResourceBean resource = resourceService.getResource(hotelId,
				ResourceType.HOTEL);
		if (resource instanceof HotelResourceBean) {
			return new ResponseDataVO<HotelResourceVO>(
					HotelResourceVO.transform((HotelResourceBean) resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							HotelResourceBean.class.getName(), resource
									.getClass().getName()));
		}
	}

	@GET
	@Path("/hotels/{hotelId}/delete")
	public ResponseDataVO<HotelResourceVO> deleteHotel(
			@PathParam("hotelId") Long hotelId,
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		ResourceBean resource = resourceService.deleteResource(hotelId,
				ResourceType.HOTEL, userId);
		if (resource instanceof HotelResourceBean) {
			return new ResponseDataVO<HotelResourceVO>(
					HotelResourceVO.transform((HotelResourceBean) resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							HotelResourceBean.class.getName(), resource
									.getClass().getName()));
		}
	}

	@GET
	@Path("/restaurants")
	public ResponsePagingDataVO<List<RestaurantResourceVO>> getRestaurants()
			throws Exception {
		// TODO
		return null;
	}

	@GET
	@Path("/restaurants/{restaurantId}")
	public ResponseDataVO<RestaurantResourceVO> getRestaurant(
			@PathParam("restaurantId") Long restaurantId) throws Exception {
		ResourceBean resource = resourceService.getResource(restaurantId,
				ResourceType.FOOD);
		if (resource instanceof RestaurantResourceBean) {
			return new ResponseDataVO<RestaurantResourceVO>(
					RestaurantResourceVO
							.transform((RestaurantResourceBean) resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							RestaurantResourceBean.class.getName(), resource
									.getClass().getName()));
		}
	}

	@GET
	@Path("/scenes")
	public ResponsePagingDataVO<List<SceneResourceVO>> getScenes()
			throws Exception {
		// TODO
		return null;
	}

	@GET
	@Path("/scenes/{sceneId}")
	public ResponseDataVO<SceneResourceVO> getScene(
			@PathParam("sceneId") Long sceneId) throws Exception {
		ResourceBean resource = resourceService.getResource(sceneId,
				ResourceType.SCENE);
		if (resource instanceof SceneResourceBean) {
			return new ResponseDataVO<SceneResourceVO>(
					SceneResourceVO.transform((SceneResourceBean) resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							SceneResourceBean.class.getName(), resource
									.getClass().getName()));
		}
	}
}
