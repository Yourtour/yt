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
	public static final String HOTEL_IMAGE_PATH = "resources/hotels/";
	public static final String RESTAURANT_IMAGE_PATH = "resources/restaurants/";
	public static final String SCENE_IMAGE_PATH = "resources/scenes/";

	@Autowired
	private IResourceService resourceService;

	@GET
	@Path("/hotels")
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
			@FormDataParam("id") Long hotelId,
			@FormDataParam("code") String code,
			@FormDataParam("name") String name,
			@FormDataParam("address") String address,
			@FormDataParam("bookingMemo") String bookingMemo,
			@FormDataParam("currency") String currency,
			@FormDataParam("feature") String feature,
			@FormDataParam("intro") String intro,
			@FormDataParam("member") boolean isMember,
			@FormDataParam("openTime") String openTime,
			@FormDataParam("payment") String payment,
			@FormDataParam("phone") String phone,
			@FormDataParam("position") String position,
			@FormDataParam("postCode") String postCode,
			@FormDataParam("price") String price,
			@FormDataParam("star") int star,
			@FormDataParam("tags") String tags,
			@FormDataParam("tips") String tips,
			@FormDataParam("trafficIntro") String trafficIntro,
			@FormDataParam("website") String website,
			@FormDataParam("accommodationStandard") String accommodationStandard,
			@FormDataParam("networkInfo") String networkInfo,
			@FormDataParam("roomEquipment") String roomEquipment,
			FormDataMultiPart form) throws Exception {
		HotelResourceBean hotel = new HotelResourceBean();
		hotel.setId(hotelId);
		hotel.setCode(code);
		hotel.setName(name);
		hotel.setAddress(address);
		hotel.setBookingMemo(bookingMemo);
		hotel.setCurrency(currency);
		hotel.setFeature(feature);
		hotel.setIntro(intro);
		hotel.setMember(isMember);
		hotel.setOpenTime(openTime);
		hotel.setPayment(payment);
		hotel.setPhone(phone);
		hotel.setPosition(position);
		hotel.setPrice(price);
		hotel.setPostCode(postCode);
		hotel.setStar(star);
		hotel.setTags(tags);
		hotel.setTips(tips);
		hotel.setTrafficIntro(trafficIntro);
		hotel.setWebsite(website);
		hotel.setImageUrl(super.uploadMediaFile(form, "hotelImage",
				HOTEL_IMAGE_PATH));

		hotel.setAccommodationStandard(accommodationStandard);
		hotel.setNetworkInfo(networkInfo);
		hotel.setRoomEquipment(roomEquipment);

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(hotel, userId);
		return new ResponseDataVO<HotelResourceVO>(
				HotelResourceVO.transform(hotel));
	}

	@GET
	@Path("/restaurants")
	public ResponsePagingDataVO<List<RestaurantResourceVO>> getRestaurants(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<? extends ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.HOTEL, new PagingConditionBean(
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
			@FormDataParam("id") Long hotelId,
			@FormDataParam("code") String code,
			@FormDataParam("name") String name,
			@FormDataParam("address") String address,
			@FormDataParam("bookingMemo") String bookingMemo,
			@FormDataParam("currency") String currency,
			@FormDataParam("feature") String feature,
			@FormDataParam("intro") String intro,
			@FormDataParam("member") boolean isMember,
			@FormDataParam("openTime") String openTime,
			@FormDataParam("payment") String payment,
			@FormDataParam("phone") String phone,
			@FormDataParam("position") String position,
			@FormDataParam("postCode") String postCode,
			@FormDataParam("price") String price,
			@FormDataParam("star") int star,
			@FormDataParam("tags") String tags,
			@FormDataParam("tips") String tips,
			@FormDataParam("trafficIntro") String trafficIntro,
			@FormDataParam("website") String website,
			@FormDataParam("deliciouFood") String deliciouFood,
			@FormDataParam("foodStandard") String foodStandard,
			@FormDataParam("foodTags") String foodTags,
			@FormDataParam("networkInfo") String networkInfo,
			FormDataMultiPart form) throws Exception {
		RestaurantResourceBean restaurant = new RestaurantResourceBean();
		restaurant.setId(hotelId);
		restaurant.setCode(code);
		restaurant.setName(name);
		restaurant.setAddress(address);
		restaurant.setBookingMemo(bookingMemo);
		restaurant.setCurrency(currency);
		restaurant.setFeature(feature);
		restaurant.setIntro(intro);
		restaurant.setMember(isMember);
		restaurant.setOpenTime(openTime);
		restaurant.setPayment(payment);
		restaurant.setPhone(phone);
		restaurant.setPosition(position);
		restaurant.setPrice(price);
		restaurant.setPostCode(postCode);
		restaurant.setStar(star);
		restaurant.setTags(tags);
		restaurant.setTips(tips);
		restaurant.setTrafficIntro(trafficIntro);
		restaurant.setWebsite(website);
		restaurant.setImageUrl(super.uploadMediaFile(form, "hotelImage",
				HOTEL_IMAGE_PATH));

		restaurant.setDeliciouFood(deliciouFood);
		restaurant.setFoodStandard(foodStandard);
		restaurant.setFoodTags(foodTags);
		restaurant.setNetworkInfo(networkInfo);

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(restaurant, userId);
		return new ResponseDataVO<RestaurantResourceVO>(
				RestaurantResourceVO.transform(restaurant));
	}

	@GET
	@Path("/scenes")
	public ResponsePagingDataVO<List<SceneResourceVO>> getScenes(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<? extends ResourceBean>> pagingData = resourceService
				.getResources(ResourceType.HOTEL, new PagingConditionBean(
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
			@FormDataParam("id") Long hotelId,
			@FormDataParam("code") String code,
			@FormDataParam("name") String name,
			@FormDataParam("address") String address,
			@FormDataParam("bookingMemo") String bookingMemo,
			@FormDataParam("currency") String currency,
			@FormDataParam("feature") String feature,
			@FormDataParam("intro") String intro,
			@FormDataParam("member") boolean isMember,
			@FormDataParam("openTime") String openTime,
			@FormDataParam("payment") String payment,
			@FormDataParam("phone") String phone,
			@FormDataParam("position") String position,
			@FormDataParam("postCode") String postCode,
			@FormDataParam("price") String price,
			@FormDataParam("star") int star,
			@FormDataParam("tags") String tags,
			@FormDataParam("tips") String tips,
			@FormDataParam("trafficIntro") String trafficIntro,
			@FormDataParam("website") String website,
			@FormDataParam("ticket") String ticket,
			@FormDataParam("sceneMap") String sceneMap,
			@FormDataParam("sceneTraffic") String sceneTraffic,
			@FormDataParam("specialScene") String specialScene,
			FormDataMultiPart form) throws Exception {
		SceneResourceBean scene = new SceneResourceBean();
		scene.setId(hotelId);
		scene.setCode(code);
		scene.setName(name);
		scene.setAddress(address);
		scene.setBookingMemo(bookingMemo);
		scene.setCurrency(currency);
		scene.setFeature(feature);
		scene.setIntro(intro);
		scene.setMember(isMember);
		scene.setOpenTime(openTime);
		scene.setPayment(payment);
		scene.setPhone(phone);
		scene.setPosition(position);
		scene.setPrice(price);
		scene.setPostCode(postCode);
		scene.setStar(star);
		scene.setTags(tags);
		scene.setTips(tips);
		scene.setTrafficIntro(trafficIntro);
		scene.setWebsite(website);
		scene.setImageUrl(super.uploadMediaFile(form, "sceneImage",
				HOTEL_IMAGE_PATH));

		scene.setTicket(ticket);
		scene.setSceneMap(sceneMap);
		scene.setSceneTraffic(sceneTraffic);
		scene.setSpecialScene(specialScene);

		Long userId = super.getCurrentUserId(request);
		resourceService.saveResource(scene, userId);
		return new ResponseDataVO<SceneResourceVO>(
				SceneResourceVO.transform(scene));
	}
}
