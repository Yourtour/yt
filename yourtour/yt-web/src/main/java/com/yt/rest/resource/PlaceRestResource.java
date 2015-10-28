package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	 * 根据目的地分类进行查询
	 * 
	 * @param category
	 * @return
	 */
	@GET
	@Path("/{parentId}/query")
	public ResponseDataVO<List<PlaceVO>> getPlaces(
			@PathParam("parentId") String parentId) {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		try {
			List<PlaceBean> places = placeRepository.getPlaces(Long
					.valueOf(parentId));
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
