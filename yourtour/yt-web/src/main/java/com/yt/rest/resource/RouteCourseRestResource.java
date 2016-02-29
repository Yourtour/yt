package com.yt.rest.resource;

import com.yt.business.repository.PlaceRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/app/course/{routeId}/{activityId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteCourseRestResource {
	private static final Log LOG = LogFactory
			.getLog(RouteCourseRestResource.class);
	@Autowired
	private PlaceRepository placeRepository;

	@Path("/checkin/save")
	@POST
	public ResponseDataVO<Long> saveCheckinInfo(String memo) {
		try {
			return new ResponseDataVO<Long>();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Save checkin info fail.", ex);
			}
			return new ResponseDataVO<Long>(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}
}
