package com.yt.oms.resource;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.PlaceBean;
import com.yt.business.service.IPlaceService;
import com.yt.oms.vo.place.PlaceTreeVO;
import com.yt.oms.vo.place.PlaceVO;
import com.yt.response.ResponseDataVO;
import com.yt.rest.resource.RestResource;

@Component
@Path("/oms/places/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceRestResource extends RestResource {
	public static final String PLACE_IMAGE_PATH = "/images/places/";

	@Autowired
	private IPlaceService placeService;

	/**
	 * 获取所有的目的地信息，以树状结构展示
	 */
	@GET
	public ResponseDataVO<List<PlaceTreeVO>> getAllPlaces() throws Exception {
		List<PlaceTreeVO> roots = new Vector<PlaceTreeVO>();
		List<PlaceBean> places = placeService.getAllPlaces();
		for (PlaceBean place : places) {
			if (place == null) {
				continue;
			}
			roots.add(PlaceTreeVO.transform(place));
		}
		return new ResponseDataVO<List<PlaceTreeVO>>(roots);
	}

	@GET
	@Path("/{placeId}")
	public ResponseDataVO<PlaceVO> getPlace(@PathParam("placeId") Long placeId)
			throws Exception {
		PlaceBean place = placeService.getPlace(placeId);
		return new ResponseDataVO<PlaceVO>(PlaceVO.transform(place));
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<PlaceVO> savePlace(
			@Context HttpServletRequest request,
			@FormDataParam("id") Long placeId,
			@FormDataParam("parentId") Long parentId,
			@FormDataParam("code") String code,
			@FormDataParam("shorter") String shorter,
			@FormDataParam("name") String name,
			@FormDataParam("memo") String memo,
			@FormDataParam("isHome") boolean isHome,
			@FormDataParam("recommended") boolean recommended,
			FormDataMultiPart form) throws Exception {
		PlaceBean place = new PlaceBean();
		place.setId(placeId);
		place.setCode(code);
		place.setShorter(shorter);
		place.setName(name);
		place.setHome(isHome);
		place.setImageUrl(super.uploadMediaFile(form, "placeImage",
				PLACE_IMAGE_PATH));
		place.setMemo(memo);
		place.setRecommended(recommended);
		Long userId = super.getCurrentUserId(request);
		placeService.savePlace(parentId, place, userId);
		return new ResponseDataVO<PlaceVO>(PlaceVO.transform(place));
	}

	@GET
	@Path("/{placeId}/delete")
	public ResponseDataVO<PlaceVO> deletePlace(@PathParam("placeId") Long placeId, @Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		PlaceBean place = placeService.deletePlace(placeId, userId);
		return new ResponseDataVO<PlaceVO>(PlaceVO.transform(place));
	}
}
