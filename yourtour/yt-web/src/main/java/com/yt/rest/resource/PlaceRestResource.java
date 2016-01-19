package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yt.core.utils.CollectionUtils;
import com.yt.utils.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.PlaceBean;
import com.yt.business.repository.PlaceRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.vo.basedata.PlaceVO;

@Component
@Path("place")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceRestResource {
	private static final Log LOG = LogFactory.getLog(PlaceRestResource.class);

	@Autowired
	private PlaceRepository placeRepository;

	/**
	 * 获取目的地树的根节点
	 * @return
	 */
	@GET
	@Path("/root/query")
	public ResponseDataVO<List<PlaceVO>> getPlaces() {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		try {
			List<PlaceBean> places = placeRepository.getAllRootPlaces();
			for (PlaceBean place : places) {
				list.add(PlaceVO.transform(place));
			}

			return new ResponseDataVO<List<PlaceVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("GetPlaces fail.", ex);
			}
			return new ResponseDataVO<List<PlaceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 获取指定目的地下属目的地
	 * @param parentCode
	 * @return
	 */
	@GET
	@Path("/{parentCode}/query")
	public ResponseDataVO<List<PlaceVO>> getPlaces(@PathParam("parentCode") String parentCode) {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		try {
			List<PlaceBean> places = placeRepository.getPlaces(parentCode);
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
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("GetPlaces fail.", ex);
			}
			return new ResponseDataVO<List<PlaceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 获取用户推荐目的地
	 * @return
	 */
	@GET
	@Path("/recommend/query")
	public ResponseDataVO<List<PlaceVO>> getRouteRecommendPlaces() {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		try {
			String userId = WebUtils.getCurrentLoginUser();
			List<PlaceBean> places = placeRepository.getRouteRecommendPlaces(Long.valueOf(userId));
			for (PlaceBean place : places) {
				list.add(PlaceVO.transform(place));
			}

			return new ResponseDataVO<List<PlaceVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("GetPlaces fail.", ex);
			}
			return new ResponseDataVO<List<PlaceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
