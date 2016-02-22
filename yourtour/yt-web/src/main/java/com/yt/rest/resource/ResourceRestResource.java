package com.yt.rest.resource;

import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.repository.ResourceRepository;
import com.yt.business.repository.RestaurantRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.resource.ResourceVO;
import com.yt.vo.resource.RestaurantResourceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
@Path("resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceRestResource {
	private static final Log LOG = LogFactory
			.getLog(ResourceRestResource.class);

	@Autowired
	private ResourceRepository repository;

	@SuppressWarnings("unchecked")
	@Path("/place/{placeId}")
	@GET
	public ResponseDataVO<List<ResourceVO>> getResources(@PathParam("placeId") Long placeId) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request to fetch all the RestaurantResourceBean.");
		}
		List<ResourceVO> list = new ArrayList<>();
		try {
			List<ResourceBean> result = repository.getResources(placeId, 0l, 20, 10);
			for (ResourceBean bean : result) {
				if (bean == null) {
					continue;
				}
				list.add(ResourceVO.transform(bean));
			}

			return new ResponseDataVO<List<ResourceVO>>(list);
		} catch (Exception ex) {
			LOG.error("Fetch all the RestaurantResourceBean fail.", ex);
			return new ResponseDataVO<List<ResourceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

}
