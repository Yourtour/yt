package com.yt.rest.resource;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.service.IService;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.SessionUtils;
import com.yt.vo.member.ExpertServiceVO;
import com.yt.vo.route.RouteServiceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/app/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(ServiceRestResource.class);

	@Autowired
	private IService service;

	/**
	 * 保存达人服务接口
	 * @param servicevo
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/expert/save")
	public ResponseVO saveExpertService(ExpertServiceVO servicevo) throws Exception{
		ExpertServiceBean service = ExpertServiceVO.transform(servicevo);

		this.service.saveExpertService(service, SessionUtils.getCurrentLoginUser());

		return new ResponseVO();
	}

	/**
	 * 删除达人服务
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/expert/{serviceId}/delete")
	public ResponseVO deleteExpertService(@PathParam("serviceId") Long serviceId) throws Exception{
		this.service.deleteExpertService(serviceId, SessionUtils.getCurrentLoginUser());

		return new ResponseVO();
	}

	/**
	 * 获取达人服务
	 * @param userId
	 * @return
	 */
	@GET
	@Path("/expert/{userId}/query")
	public ResponseDataVO<List<ExpertServiceVO>> getExpertServices(@PathParam("userId")  Long userId) throws Exception{
		List<ExpertServiceVO> services = new ArrayList<>();

		List<ExpertServiceBean> serviceBeans = service.getExpertServices(userId);
		if(serviceBeans != null){
			for(ExpertServiceBean serviceBean : serviceBeans){
				services.add(ExpertServiceVO.transform(serviceBean));
			}
		}

		return new ResponseDataVO<List<ExpertServiceVO>>(services);
	}

	/**
	 * 获取行程预订的服务
	 * @param routeId
	 * @return
	 */
	@GET
	@Path("/route/{routeId}/query")
	public ResponseDataVO<List<RouteServiceVO>> getRouteServices(@PathParam("routeId")  Long routeId) throws Exception{
		List<RouteServiceVO> services = new ArrayList<>();

		List<RouteServiceBean> serviceBeans = service.getRouteServices(routeId);
		if(serviceBeans != null){
			for(RouteServiceBean serviceBean : serviceBeans){
				services.add(RouteServiceVO.transform(serviceBean));
			}
		}

		return new ResponseDataVO<List<RouteServiceVO>>(services);
	}

	/**
	 * 行程服务预订
	 * @param routeId
	 * @param serviceId
	 * @param serviceVO
	 * @return
	 */
	@POST
	@Path("/route/{routeId}/service/{serviceId}/save")
	public ResponseDataVO<RouteServiceVO> saveRouteService(@PathParam("routeId") Long routeId,
														   @PathParam("serviceId") Long serviceId,
														   RouteServiceVO serviceVO) throws Exception{
		RouteMainBean route = new RouteMainBean(routeId);

		ExpertServiceBean service = new ExpertServiceBean(serviceId);

		RouteServiceBean serviceBean = RouteServiceVO.transform(serviceVO);
		serviceBean.setRoute(route);
		serviceBean.setService(service);

		this.service.saveRouteService(serviceBean, this.getCurrentUserId());

		RouteServiceVO vo = RouteServiceVO.transform(serviceBean);

		return new ResponseDataVO<>(vo);
	}

	/**
	 * 取消服务预订
	 * @param serviceId
	 * @return
	 */
	@GET
	@Path("/route/{routeId}/service/{serviceId}/delete")
	public ResponseVO deleteRouteService(@PathParam("routeId") Long routeId,
										 @PathParam("serviceId") Long serviceId) throws Exception {
		this.service.deleteRouteService(routeId, serviceId, SessionUtils.getCurrentLoginUser());

		return new ResponseVO();
	}
}