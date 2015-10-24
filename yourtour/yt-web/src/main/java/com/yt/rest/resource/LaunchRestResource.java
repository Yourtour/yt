package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
import com.yt.vo.LaunchVO;
import com.yt.vo.PlaceVO;

@Component
@Path("app")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LaunchRestResource {
	private static final Log LOG = LogFactory.getLog(LaunchRestResource.class);

	@Autowired
	private PlaceRepository placeRepository;

	@Path("launch")
	@GET
	public ResponseDataVO<LaunchVO> launchApp() {
		LaunchVO valueObject = new LaunchVO();
		try {
			List<PlaceBean> placeBeans = (List<PlaceBean>) placeRepository.getAllRootPlaces();
			if(placeBeans != null){
				List<PlaceVO> places = new ArrayList<PlaceVO>();
				for (PlaceBean bean : placeBeans) {
					places.add(new PlaceVO(bean));
				}
				
				valueObject.setPlaces(places);
			}
			
			return new ResponseDataVO<LaunchVO>(valueObject);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("launchApp fail.", ex);
			}
			return new ResponseDataVO<LaunchVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
