package com.yt.oms.resource;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.service.IResourceService;
import com.yt.core.utils.BeanUtils;
import com.yt.oms.vo.resource.HotelResourceVO;
import com.yt.oms.vo.resource.RestaurantResourceVO;
import com.yt.oms.vo.resource.SceneResourceVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.rest.resource.RestResource;

@Component
@Path("/oms/resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceRestResource extends RestResource {
	public static final String HOTEL_IMAGE_PATH = "resources/hotels/";
	public static final String RESTAURANT_IMAGE_PATH = "resources/restaurants/";
	public static final String SCENE_IMAGE_PATH = "resources/scenes/";

	@Autowired
	private IResourceService resourceService;

	@GET
	@Path("/hotels/query")
	public ResponsePagingDataVO<List<HotelResourceVO>> getHotels(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<? extends ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.HOTEL, new PagingConditionBean(
						nextCursor, limit, total));
		List<HotelResourceVO> vos = new Vector<>();
		for (ResourceBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(HotelResourceVO.transform((HotelResourceBean) bean));
		}
		return new ResponsePagingDataVO<List<HotelResourceVO>>(
				pagingData.getTotal(), vos.size(), vos);
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

	@POST
	@Path("/hotels/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<HotelResourceVO> saveHotel(
			@Context HttpServletRequest request,
			@FormDataParam("hotel") String json, FormDataMultiPart form)
			throws Exception {
		HotelResourceVO hotelVO = (HotelResourceVO) BeanUtils.deserialize(json,
				HotelResourceVO.class);
		HotelResourceBean hotel = HotelResourceVO.transform(hotelVO);
		hotel.setImageUrl(super.uploadMediaFile(form, "hotelImage",
				HOTEL_IMAGE_PATH));

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(hotel, userId);
		return new ResponseDataVO<HotelResourceVO>(
				HotelResourceVO.transform(hotel));
	}

	@GET
	@Path("/restaurants/query")
	public ResponsePagingDataVO<List<RestaurantResourceVO>> getRestaurants(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<? extends ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.FOOD, new PagingConditionBean(
						nextCursor, limit, total));
		List<RestaurantResourceVO> vos = new Vector<>();
		for (ResourceBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(RestaurantResourceVO
					.transform((RestaurantResourceBean) bean));
		}
		return new ResponsePagingDataVO<List<RestaurantResourceVO>>(
				pagingData.getTotal(), vos.size(), vos);
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
	@Path("/restaurants/{restaurantId}/delete")
	public ResponseDataVO<RestaurantResourceVO> deleteRestaurant(
			@PathParam("restaurantId") Long restaurantId,
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		ResourceBean resource = resourceService.deleteResource(restaurantId,
				ResourceType.FOOD, userId);
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

	@POST
	@Path("/restaurants/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<RestaurantResourceVO> saveRestaurant(
			@Context HttpServletRequest request,
			@FormDataParam("restaurant") String json, FormDataMultiPart form)
			throws Exception {
		RestaurantResourceVO restaurantVO = (RestaurantResourceVO) BeanUtils
				.deserialize(json, RestaurantResourceVO.class);
		RestaurantResourceBean restaurant = RestaurantResourceVO
				.transform(restaurantVO);
		restaurant.setImageUrl(super.uploadMediaFile(form, "restaurantImage",
				HOTEL_IMAGE_PATH));

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(restaurant, userId);
		return new ResponseDataVO<RestaurantResourceVO>(
				RestaurantResourceVO.transform(restaurant));
	}

	@GET
	@Path("/scenes/query")
	public ResponsePagingDataVO<List<SceneResourceVO>> getScenes(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<? extends ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.SCENE, new PagingConditionBean(
						nextCursor, limit, total));
		List<SceneResourceVO> vos = new Vector<>();
		for (ResourceBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(SceneResourceVO.transform((SceneResourceBean) bean));
		}
		return new ResponsePagingDataVO<List<SceneResourceVO>>(
				pagingData.getTotal(), vos.size(), vos);
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

	@GET
	@Path("/scenes/{sceneId}/delete")
	public ResponseDataVO<SceneResourceVO> deleteScene(
			@PathParam("sceneId") Long sceneId,
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		ResourceBean resource = resourceService.deleteResource(sceneId,
				ResourceType.SCENE, userId);
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

	@POST
	@Path("/scenes/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<SceneResourceVO> saveScene(
			@Context HttpServletRequest request,
			@FormDataParam("scene") String json, FormDataMultiPart form)
			throws Exception {
		SceneResourceVO sceneVO = (SceneResourceVO) BeanUtils.deserialize(json,
				SceneResourceVO.class);
		SceneResourceBean scene = SceneResourceVO.transform(sceneVO);
		scene.setImageUrl(super.uploadMediaFile(form, "sceneImage",
				HOTEL_IMAGE_PATH));

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(scene, userId);
		return new ResponseDataVO<SceneResourceVO>(
				SceneResourceVO.transform(scene));
	}
}
