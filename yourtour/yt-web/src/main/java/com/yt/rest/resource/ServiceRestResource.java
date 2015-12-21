package com.yt.rest.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.RouteRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseVO;
import com.yt.vo.route.RouteServiceVO;

@Component
@Path("services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceRestResource {
	private static final Log LOG = LogFactory.getLog(ServiceRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private RouteRepository routeRepository;

	/**
	 * 删除伙伴
	 * @param request
	 * @param member
	 * @return
	 */
	@GET
	@Path("/delete")
	public ResponseVO save(@Context HttpServletRequest request, RouteServiceVO service){
		try{
			/*RouteMainBean route = (RouteMainBean) routeRepository.get(RouteMainBean.class, Long.valueOf(routeId), false);
			if(route == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}
			
			UserProfileBean user = (UserProfileBean)routeRepository.get(UserProfileBean.class, Long.valueOf(userId), false);
			if(user == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}
			
			routeRepository.deleteRelation(route, user, Constants.RELATION_TYPE_PARTICIPATE);*/
			
			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when registering user account.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
