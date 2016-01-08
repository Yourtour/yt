package com.yt.rest.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.yt.business.bean.*;
import com.yt.business.repository.RouteServiceRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.response.ResponseDataVO;
import com.yt.utils.WebUtils;
import com.yt.vo.AbbrVO;
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
public class ServiceRestResource {
	private static final Log LOG = LogFactory.getLog(ServiceRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private RouteServiceRepository serviceRepository;

	/**
	 *
	 * @param request
	 * @param service
	 * @return
	 */
	@POST
	@Path("/route/save")
	public ResponseVO saveRouteService(@Context HttpServletRequest request, RouteServiceVO serviceVO){
		try{
			RouteMainBean route = (RouteMainBean) routeRepository.get(RouteMainBean.class, Long.valueOf(serviceVO.getRouteId()), false);
			if(route == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			ExpertServiceBean service = (ExpertServiceBean) routeRepository.get(ExpertServiceBean.class, Long.valueOf(serviceVO.getExpertServiceId()), false);
			if(service == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			RouteServiceBean serviceBean = new RouteServiceBean();
			serviceBean.setRoute(route);
			serviceBean.setService(service);
			serviceBean.setTitle(serviceVO.getTitle());
			serviceBean.setMemo(serviceVO.getMemo());

			this.serviceRepository.save(serviceBean,  WebUtils.getCurrentLoginUser(request));

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when saving service.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param request
	 * @param service
	 * @return
	 */
	@POST
	@Path("/schedule/save")
	public ResponseVO saveScheduleService(@Context HttpServletRequest request, RouteServiceVO serviceVO){
		try{
			RouteScheduleBean schedule = (RouteScheduleBean) routeRepository.get(RouteScheduleBean.class, Long.valueOf(serviceVO.getScheduleId()), false);
			if(schedule == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			ExpertServiceBean service = (ExpertServiceBean) routeRepository.get(ExpertServiceBean.class, Long.valueOf(serviceVO.getExpertServiceId()), false);
			if(service == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			RouteServiceBean serviceBean = new RouteServiceBean();
			serviceBean.setSchedule(schedule);
			serviceBean.setService(service);
			serviceBean.setTitle(serviceVO.getTitle());
			serviceBean.setMemo(serviceVO.getMemo());

			this.serviceRepository.save(serviceBean,  WebUtils.getCurrentLoginUser(request));

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when saving service.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@POST
	@Path("/activity/save")
	public ResponseVO saveActivityService(@Context HttpServletRequest request, RouteServiceVO serviceVO){
		try{
			RouteActivityBean activity = (RouteActivityBean) routeRepository.get(RouteActivityBean.class, Long.valueOf(serviceVO.getScheduleId()), false);
			if(activity == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			ExpertServiceBean service = (ExpertServiceBean) routeRepository.get(ExpertServiceBean.class, Long.valueOf(serviceVO.getExpertServiceId()), false);
			if(service == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			RouteServiceBean serviceBean = new RouteServiceBean();
			serviceBean.setActivity(activity);
			serviceBean.setService(service);
			serviceBean.setTitle(serviceVO.getTitle());
			serviceBean.setMemo(serviceVO.getMemo());

			this.serviceRepository.save(serviceBean,  WebUtils.getCurrentLoginUser(request));

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised when saving service.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
    @Path("/expert/{expertId}")
    public ResponseDataVO<List<ExpertServiceVO>> getServices(@PathParam("expertId")  String expertId){
        try {
            Long userId = Neo4jUtils.getGraphIDFromString(expertId);

            List<ExpertServiceVO> services = new ArrayList<>();
            List<ExpertServiceBean> serviceBeans = serviceRepository.getServices(userId);
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
