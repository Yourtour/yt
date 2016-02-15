package com.yt.rest.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.yt.business.bean.*;
import com.yt.business.repository.ServiceRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.response.ResponseDataVO;
import com.yt.utils.WebUtils;
import com.yt.vo.member.ExpertServiceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.repository.RouteRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseVO;
import com.yt.vo.route.RouteServiceVO;

import java.util.ArrayList;
import java.util.List;

@Component
@Path("services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceRestResource extends BaseRestResource{
	private static final Log LOG = LogFactory.getLog(ServiceRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@GET
	@Path("/route/{routeId}")
	public ResponseDataVO<List<RouteServiceVO>> getRouteServices(@PathParam("routeId")  String routeId){
		try {
			Long rid = Neo4jUtils.getGraphIDFromString(routeId);

			List<RouteServiceVO> services = new ArrayList<>();
			List<RouteServiceBean> serviceBeans = serviceRepository.getRouteServices(rid);
			if(serviceBeans != null){
				for(RouteServiceBean serviceBean : serviceBeans){
					services.add(RouteServiceVO.transform(serviceBean));
				}
			}

			return new ResponseDataVO<List<RouteServiceVO>>(services);
		} catch (Exception ex) {
			LOG.error("Exception raised.", ex);
			return new ResponseDataVO<List<RouteServiceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param routeId
	 * @param serviceId
	 * @param serviceVO
	 * @return
	 */
	@POST
	@Path("/route/{routeId}/service/{serviceId}/save")
	public ResponseVO saveRouteService(@PathParam("routeId") String routeId, @PathParam("serviceId") String serviceId, RouteServiceVO serviceVO){
		try{
			RouteMainBean route = (RouteMainBean) routeRepository.get(RouteMainBean.class, Long.valueOf(routeId), false);
			if(route == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			ExpertServiceBean service = (ExpertServiceBean) routeRepository.get(ExpertServiceBean.class, Long.valueOf(serviceId), false);
			if(service == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			RouteServiceBean serviceBean = RouteServiceVO.transform(serviceVO);
			serviceBean.setRoute(route);
			serviceBean.setService(service);

			this.serviceRepository.save(serviceBean, this.getCurrentUserId());

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when saving service.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
    @Path("/expert/{expertId}")
    public ResponseDataVO<List<ExpertServiceVO>> getExpertServices(@PathParam("expertId")  String expertId){
        try {
            Long userId = Neo4jUtils.getGraphIDFromString(expertId);

            List<ExpertServiceVO> services = new ArrayList<>();
            List<ExpertServiceBean> serviceBeans = serviceRepository.getExpertServices(userId);
            if(serviceBeans != null){
                for(ExpertServiceBean serviceBean : serviceBeans){
                    services.add(ExpertServiceVO.transform(serviceBean));
                }
            }

            return new ResponseDataVO<List<ExpertServiceVO>>(services);
        } catch (Exception ex) {
            LOG.error("Exception raised.", ex);
            return new ResponseDataVO<List<ExpertServiceVO>>(
                    StaticErrorEnum.FETCH_DB_DATA_FAIL);
        }
    }
}