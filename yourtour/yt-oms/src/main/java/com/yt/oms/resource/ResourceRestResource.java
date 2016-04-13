package com.yt.oms.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.service.IResourceService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.oms.vo.resource.ResourceVO;
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
	public ResponsePagingDataVO<List<ResourceVO>> getHotels(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.HOTEL, new PagingConditionBean(
						nextCursor, limit, total));
		List<ResourceVO> vos = new Vector<>();
		for (ResourceBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(ResourceVO.transform(bean));
		}
		return new ResponsePagingDataVO<List<ResourceVO>>(
				pagingData.getTotal(), vos.size(), vos);
	}

	@GET
	@Path("/hotels/{hotelId}")
	public ResponseDataVO<ResourceVO> getHotel(
			@PathParam("hotelId") Long hotelId) throws Exception {
		ResourceBean resource = resourceService.getResource(hotelId,
				ResourceType.HOTEL);
		if (resource instanceof ResourceBean) {
			return new ResponseDataVO<ResourceVO>(
					ResourceVO.transform(resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							resource.getType().name(), resource.getClass()
									.getName()));
		}
	}

	@GET
	@Path("/hotels/{hotelId}/delete")
	public ResponseDataVO<ResourceVO> deleteHotel(
			@PathParam("hotelId") Long hotelId,
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		ResourceBean resource = resourceService.deleteResource(hotelId,
				ResourceType.HOTEL, userId);
		if (resource instanceof ResourceBean) {
			return new ResponseDataVO<ResourceVO>(
					ResourceVO.transform(resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							resource.getType().name(), resource.getClass()
									.getName()));
		}
	}

	@POST
	@Path("/hotels/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<ResourceVO> saveHotel(
			@Context HttpServletRequest request,
			@FormDataParam("hotel") String json,
			@FormDataParam("files") List<FormDataBodyPart> files)
			throws Exception {
		ResourceVO hotelVO = (ResourceVO) BeanUtils.deserialize(json,
				ResourceVO.class);
		ResourceBean hotel = ResourceVO.transform(hotelVO);
		hotel.setImageUrl(super.uploadMediaFile(files, HOTEL_IMAGE_PATH));

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(hotel, userId);
		return new ResponseDataVO<ResourceVO>(ResourceVO.transform(hotel));
	}

	@GET
	@Path("/restaurants/query")
	public ResponsePagingDataVO<List<ResourceVO>> getRestaurants(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.FOOD, new PagingConditionBean(
						nextCursor, limit, total));
		List<ResourceVO> vos = new Vector<>();
		for (ResourceBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(ResourceVO.transform(bean));
		}
		return new ResponsePagingDataVO<List<ResourceVO>>(
				pagingData.getTotal(), vos.size(), vos);
	}

	@GET
	@Path("/restaurants/{restaurantId}")
	public ResponseDataVO<ResourceVO> getRestaurant(
			@PathParam("restaurantId") Long restaurantId) throws Exception {
		ResourceBean resource = resourceService.getResource(restaurantId,
				ResourceType.FOOD);
		if (resource instanceof ResourceBean) {
			return new ResponseDataVO<ResourceVO>(
					ResourceVO.transform(resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							resource.getType().name(), resource.getClass()
									.getName()));
		}
	}

	@GET
	@Path("/restaurants/{restaurantId}/delete")
	public ResponseDataVO<ResourceVO> deleteRestaurant(
			@PathParam("restaurantId") Long restaurantId,
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		ResourceBean resource = resourceService.deleteResource(restaurantId,
				ResourceType.FOOD, userId);
		if (resource instanceof ResourceBean) {
			return new ResponseDataVO<ResourceVO>(
					ResourceVO.transform(resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							resource.getType().name(), resource.getClass()
									.getName()));
		}
	}

	@POST
	@Path("/restaurants/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<ResourceVO> saveRestaurant(
			@Context HttpServletRequest request,
			@FormDataParam("restaurant") String json,
			@FormDataParam("files") List<FormDataBodyPart> files)
			throws Exception {
		ResourceVO restaurantVO = (ResourceVO) BeanUtils.deserialize(json,
				ResourceVO.class);
		ResourceBean restaurant = ResourceVO.transform(restaurantVO);
		restaurant.setImageUrl(super.uploadMediaFile(files, HOTEL_IMAGE_PATH));

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(restaurant, userId);
		return new ResponseDataVO<ResourceVO>(ResourceVO.transform(restaurant));
	}

	@GET
	@Path("/scenes/query")
	public ResponsePagingDataVO<List<ResourceVO>> getScenes(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.SCENE, new PagingConditionBean(
						nextCursor, limit, total));
		List<ResourceVO> vos = new Vector<>();
		for (ResourceBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(ResourceVO.transform(bean));
		}
		return new ResponsePagingDataVO<List<ResourceVO>>(
				pagingData.getTotal(), vos.size(), vos);
	}

	@GET
	@Path("/scenes/{sceneId}")
	public ResponseDataVO<ResourceVO> getScene(
			@PathParam("sceneId") Long sceneId) throws Exception {
		ResourceBean resource = resourceService.getResource(sceneId,
				ResourceType.SCENE);
		if (resource instanceof ResourceBean) {
			return new ResponseDataVO<ResourceVO>(
					ResourceVO.transform(resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							resource.getType().name(), resource.getClass()
									.getName()));
		}
	}

	@GET
	@Path("/scenes/{sceneId}/delete")
	public ResponseDataVO<ResourceVO> deleteScene(
			@PathParam("sceneId") Long sceneId,
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		ResourceBean resource = resourceService.deleteResource(sceneId,
				ResourceType.SCENE, userId);
		if (resource instanceof ResourceBean) {
			return new ResponseDataVO<ResourceVO>(
					ResourceVO.transform(resource));
		} else {
			throw new Exception(
					String.format(
							"Return resource type unmatch, required type: %s, return type: %s",
							resource.getType().name(), resource.getClass()
									.getName()));
		}
	}

	@POST
	@Path("/scenes/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<ResourceVO> saveScene(
			@Context HttpServletRequest request,
			@FormDataParam("scene") String json,
			@FormDataParam("files") List<FormDataBodyPart> files)
			throws Exception {
		ResourceVO sceneVO = (ResourceVO) BeanUtils.deserialize(json,
				ResourceVO.class);
		ResourceBean scene = ResourceVO.transform(sceneVO);
		scene.setImageUrl(super.uploadMediaFile(files, HOTEL_IMAGE_PATH));

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(scene, userId);
		return new ResponseDataVO<ResourceVO>(ResourceVO.transform(scene));
	}

	@POST
	@Path("/query")
	public ResponsePagingDataVO<List<ResourceVO>> getResources(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total, Map<String, Object> params)
			throws Exception {
		List<ResourceVO> voes = new ArrayList<>();

		PagingDataBean<List<ResourceBean>> paginationData = this.resourceService
				.getResources(
						new PagingConditionBean(nextCursor, limit, total),
						params);
		if (paginationData != null && paginationData.getTotal() > 0) {
			List<? extends ResourceBean> resources = paginationData.getData();
			if (CollectionUtils.isNotEmpty(resources)) {
				for (ResourceBean resource : resources) {
					ResourceVO vo = new ResourceVO();
					vo.fromBean(resource);
					voes.add(vo);
				}
			}
		}

		return new ResponsePagingDataVO<List<ResourceVO>>(0, voes.size(), voes);
	}

}
