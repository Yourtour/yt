package com.yt.rest.resource;

import com.yt.business.bean.PlaceBean;
import com.yt.business.service.IPlaceService;
import com.yt.core.utils.CollectionUtils;
import com.yt.response.ResponseDataVO;
import com.yt.vo.place.PlaceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("app/place")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceRestResource {
	private static final Log LOG = LogFactory.getLog(PlaceRestResource.class);

	@Autowired
	private IPlaceService placeService;

	/**
	 * 获取目的地树的根节点
	 * @return
	 */
	@GET
	@Path("/root/query")
	public ResponseDataVO<List<PlaceVO>> getPlaces() throws Exception {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		List<PlaceBean> places = placeService.getAllRootPlaces();
		for (PlaceBean place : places) {
			list.add(PlaceVO.transform(place));
		}

		return new ResponseDataVO<List<PlaceVO>>(list);
	}

	/**
	 * 获取指定目的地下属目的地
	 * @param parentCode
	 * @return
	 */
	@GET
	@Path("/parent/{parentCode}/query")
	public ResponseDataVO<List<PlaceVO>> getPlaces(@PathParam("parentCode") String parentCode) throws Exception {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		List<PlaceBean> places = placeService.getPlaces(parentCode);
		List<PlaceBean> subPlaces = null;
		for (PlaceBean place : places) {
			list.add(PlaceVO.transform(place));

			subPlaces = place.getSubs();
			if(CollectionUtils.isNotEmpty(subPlaces)){
				for(PlaceBean subPlace : subPlaces){
					list.add(PlaceVO.transform(subPlace));
				}
			}
		}

		return new ResponseDataVO<List<PlaceVO>>(list);
	}

	/**
	 * 获取和指定目的地相关的目的地
	 * @return
	 */
	@GET
	@Path("/relative/{placeId}/query")
	public ResponseDataVO<List<PlaceVO>> getRelatedPlaces(@PathParam("placeId") Long placeId ) throws Exception{
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		List<PlaceBean> places = placeService.getRelatedPlaces(placeId);
		for (PlaceBean place : places) {
			list.add(PlaceVO.transform(place));
		}

		return new ResponseDataVO<List<PlaceVO>>(list);
	}

	/**
	 * 获取目的地信息
	 * @param placeId
	 * @return
	 */
	@GET
	@Path("/{placeId}")
	public ResponseDataVO<PlaceVO> queryMainInfo(@PathParam("placeId") Long placeId){
		PlaceVO vo = null;

		return new ResponseDataVO<PlaceVO>(vo);
	}
}
