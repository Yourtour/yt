package com.yt.rest.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.ServiceRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.response.ResponseDataVO;
import com.yt.vo.member.ExpertServiceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.repository.RouteRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseVO;
import com.yt.vo.route.RouteServiceVO;

import java.util.ArrayList;
import java.util.List;

@Component
@Path("/app/services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(ServiceRestResource.class);

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	/**
	 * 获取行程服务
	 * @param routeId
	 * @return
	 */
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
	@Path("/{routeId}/{serviceId}/save")
	public ResponseDataVO<RouteServiceVO> saveRouteService(@PathParam("routeId") String routeId, @PathParam("serviceId") String serviceId, RouteServiceVO serviceVO){
		try{
			RouteMainBean route = (RouteMainBean) routeRepository.get(RouteMainBean.class, Long.valueOf(routeId), false);
			if(route == null){
				return new ResponseDataVO<>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			ExpertServiceBean service = (ExpertServiceBean) routeRepository.get(ExpertServiceBean.class, Long.valueOf(serviceId), true);
			if(service == null){
				return new ResponseDataVO<>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			RouteServiceBean serviceBean = RouteServiceVO.transform(serviceVO);
			serviceBean.setRoute(route);
			serviceBean.setService(service);

			this.serviceRepository.save(serviceBean, this.getCurrentUserId());

			RouteServiceVO vo = RouteServiceVO.transform(serviceBean);

			return new ResponseDataVO<>(vo);
		} catch (Exception ex) {
			LOG.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param serviceId
	 * @return
	 */
	@GET
	@Path("/{serviceId}/delete")
	public ResponseVO deleteRouteService(@PathParam("serviceId") String serviceId) {
		try {
			Long sid = Neo4jUtils.getGraphIDFromString(serviceId);

			RouteServiceBean service = (RouteServiceBean) this.serviceRepository.get(RouteServiceBean.class, sid);
			if(service != null){
				this.serviceRepository.delete(service);
			}

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised.", ex);
			return new ResponseVO(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}



	/**
	 * 获取达人服务
	 * @param expertId
	 * @return
	 */
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

	/**
	 * 保存服务收藏
	 * @param serviceId
	 * @return
	 */
	@GET
	@Path("/favorite/{serviceId}/save")
	public ResponseVO saveExpectServiceFavorite(@PathParam("serviceId") String serviceId) {
		try {
			Long sid = Neo4jUtils.getGraphIDFromString(serviceId);
			ExpertServiceBean service = (ExpertServiceBean) this.serviceRepository.get(ExpertServiceBean.class, sid, false);
			if(service == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			Long uid = Neo4jUtils.getGraphIDFromString(this.getCurrentUserId());
			UserProfileBean user = (UserProfileBean) this.serviceRepository.get(UserProfileBean.class, uid, false);
			if(user == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			serviceRepository.createRelation(user, service, Constants.RELATION_TYPE_FAVORITE, Direction.OUTGOING);

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised.", ex);
			return new ResponseVO(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 取消服务收藏
	 * @param serviceId
	 * @return
	 */
	@GET
	@Path("/favorite/{serviceId}/delete")
	public ResponseVO deleteExpectServiceFavorite(@PathParam("serviceId") String serviceId) {
		try {
			Long sid = Neo4jUtils.getGraphIDFromString(serviceId);
			ExpertServiceBean service = (ExpertServiceBean) this.serviceRepository.get(ExpertServiceBean.class, sid, false);
			if(service == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			Long uid = Neo4jUtils.getGraphIDFromString(this.getCurrentUserId());
			UserProfileBean user = (UserProfileBean) this.serviceRepository.get(UserProfileBean.class, uid, false);
			if(user == null){
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			serviceRepository.deleteRelation(user, service, Constants.RELATION_TYPE_FAVORITE);

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised.", ex);
			return new ResponseVO(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}


	/**
	 * 获取目的地达人服务
	 * @param placeIds
	 * @return
	 */
	@GET
	@Path("/place/{placeIds}")
	public ResponseDataVO<List<ExpertServiceVO>> getPlaceServices(@PathParam("placeIds")  String placeIds){
		try {
			String[] spids = placeIds.split(",");
			Long[] lpids = new Long[spids.length];
			for(int index = 0; index < spids.length; index++){
				lpids[index] = Neo4jUtils.getGraphIDFromString(spids[index]);
			}

			List<ExpertServiceVO> services = new ArrayList<>();
			List<ExpertServiceBean> serviceBeans = serviceRepository.getPlaceServices(lpids, 0l, 20);
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