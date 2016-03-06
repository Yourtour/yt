package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.yt.business.bean.*;
import com.yt.business.repository.ExpertRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.SessionUtils;
import com.yt.vo.member.ExpertApplicationVO;
import com.yt.vo.member.ExpertApprovementVO;
import com.yt.vo.member.ExpertServiceVO;
import com.yt.vo.member.ExpertVO;
import com.yt.vo.route.RouteItemVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/app/expert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpertRestResource {
	private static final Log Logger = LogFactory.getLog(ExpertRestResource.class);

	@Autowired
	private ExpertRepository expertRepository;

	/**
	 * 查找目的地提供服务的达人
	 * @param placeIds
	 * @param type
	 * @return
	 */
	@GET
	@Path("/places/{placeIds}/services/{serviceType}")
	public ResponseDataVO<List<ExpertVO>> query(@PathParam("placeIds") String placeIds, @PathParam("serviceType") String type){
		try{
			List<ExpertVO> experts = new ArrayList<>();
			List<ExpertBean> beans = this.expertRepository.getExperts(placeIds, type.equalsIgnoreCase("ALL")?null:type);
			if(CollectionUtils.isNotEmpty(beans)){
				for(ExpertBean expert : beans){
					experts.add(ExpertVO.transform(expert));
				}
			}

			return new ResponseDataVO<List<ExpertVO>>(experts);
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<List<ExpertVO>>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}



	/**
	 *
	 * @param expertId
	 * @return
	 */
	@GET
	@Path("/{expertId}")
	public ResponseDataVO<ExpertVO> get(@PathParam("expertId") String expertId){
		try{
			ExpertBean bean = (ExpertBean) this.expertRepository.get(ExpertBean.class, Neo4jUtils.getGraphIDFromString(expertId));

			ExpertVO expert = ExpertVO.transform(bean);

			return new ResponseDataVO<ExpertVO>(expert);
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<ExpertVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 达人申请
	 * @param applicationVO
	 * @param request
	 * @return
	 */
	@POST
	@Path("/application")
	public ResponseVO saveApplication(ExpertApplicationVO applicationVO,@Context HttpServletRequest request) {
		try{
			String userId = SessionUtils.getCurrentLoginUser(request);
			UserProfileBean user = (UserProfileBean) this.expertRepository.get(UserProfileBean.class, Long.valueOf(userId), false);
			ExpertBean expert = null; //user.getExpert();

			ExpertApplicationBean application = this.expertRepository.getApplication(Long.valueOf(userId));
			if(application == null){
				application = ExpertApplicationVO.getBean(applicationVO);
			}else{
				application.setRealName(applicationVO.getRealName());
				application.setCertNo(applicationVO.getCertNo());
				application.setCertType(applicationVO.getCertType());
				application.setTags(applicationVO.getTags());
			}

			application.setExpert(expert);

			this.expertRepository.saveApplication(application, SessionUtils.getCurrentLoginUser(request));
			return new ResponseVO();
		}catch(Exception exc){
			Logger.error("Expert Application Exception.", exc);
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 *
	 * @param applicationId
	 * @param approvementVO
	 * @return
	 */
	@POST
	@Path("/{expertId}/{applicationId}/approve")
	public ResponseVO saveApprovement(@PathParam("expertId") String expertId, @PathParam("applicationId") String applicationId, ExpertApprovementVO approvementVO) {
		try{
			String userId = SessionUtils.getCurrentLoginUser();

			ExpertApplicationBean application = (ExpertApplicationBean) this.expertRepository.get(ExpertApplicationBean.class, Long.valueOf(applicationId), false);

			ExpertApprovementBean approvement = ExpertApprovementVO.transform(approvementVO);
			approvement.setApplication(application);

			this.expertRepository.saveApprovement(approvement, userId);

			return new ResponseVO();
		}catch(Exception exc){
			Logger.error("Expert Application Exception.", exc);
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 *
	 * @param request
	 * @param serviceVO
	 * @return
	 */
	@POST
	@Path("/service/save")
	public ResponseDataVO<Long> saveService(@Context HttpServletRequest request, ExpertServiceVO serviceVO){
		try{
			String userId = SessionUtils.getCurrentLoginUser(request);
			UserProfileBean user = (UserProfileBean) this.expertRepository.get(UserProfileBean.class, Long.valueOf(userId), false);

			ExpertServiceBean service = ExpertServiceVO.transform(serviceVO);
			service.setUser(user);
			this.expertRepository.save(service, userId);

			return new ResponseDataVO<Long>(service.getGraphId());
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param request
	 * @param serviceId
	 * @return
	 */
	@GET
	@Path("/service/{serviceId}/delete")
	public ResponseVO deleteService(@Context HttpServletRequest request, @PathParam("serviceId") String serviceId){
		try{
			ExpertServiceBean service = (ExpertServiceBean) this.expertRepository.get(ExpertServiceBean.class, Long.valueOf(serviceId), false);

			this.expertRepository.delete(service);

			return new ResponseVO();
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param request
	 * @param expertId
	 * @return
	 */
	@GET
	@Path("/services/{expertId}")
	public ResponseDataVO<List<ExpertServiceVO>> getServices(@Context HttpServletRequest request, @PathParam("expertId") String expertId){
		try{
			List<ExpertServiceBean> services = this.expertRepository.getServices(Long.valueOf(expertId), 0l, 20);
			List<ExpertServiceVO> valueobjects = new ArrayList<>();

			if(services != null){
				for(ExpertServiceBean service : services){
					valueobjects.add(ExpertServiceVO.transform(service));
				}
			}
			return new ResponseDataVO<List<ExpertServiceVO>>(valueobjects);
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<List<ExpertServiceVO>>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param request
	 * @param expertId
	 * @return
	 */
	@GET
	@Path("/routes/expert/{expertId}")
	public ResponseDataVO<List<RouteItemVO>> getServicedRoutes(@Context HttpServletRequest request, @PathParam("expertId") String expertId){
		try{
			List<RouteMainBean> routes = this.expertRepository.getServicedRoutes(Long.valueOf(expertId), 0l, 20);
			List<RouteItemVO> valueobjects = new ArrayList<>();

			if(routes != null){
				for(RouteMainBean route : routes){
					valueobjects.add(new RouteItemVO(route));
				}
			}
			return new ResponseDataVO<List<RouteItemVO>>(valueobjects);
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<List<RouteItemVO>>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param request
	 * @param uid
	 * @return
	 */
	@GET
	@Path("/routes/recommend/{uid}")
	public ResponseDataVO<List<RouteItemVO>> getRecommendRoutes(@Context HttpServletRequest request, @PathParam("uid") String uid){
		try{
			List<RouteMainBean> routes = this.expertRepository.getRecommendRoutes(Long.valueOf(uid), 0l, 20);
			List<RouteItemVO> valueobjects = new ArrayList<>();

			if(routes != null){
				for(RouteMainBean route : routes){
					valueobjects.add(new RouteItemVO(route));
				}
			}
			return new ResponseDataVO<List<RouteItemVO>>(valueobjects);
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<List<RouteItemVO>>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
