package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.yt.business.bean.*;
import com.yt.business.repository.ExpertRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.member.ExpertApplicationVO;
import com.yt.vo.member.ExpertServiceVO;
import com.yt.vo.route.RouteItemVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/expert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpertRestResource {
	private static final Log Logger = LogFactory.getLog(ExpertRestResource.class);

	@Autowired
	private ExpertRepository expertRepository;

	/**
	 *
	 * @param applicationVO
	 * @param request
	 * @return
	 */
	@POST
	@Path("/application/save")
	public ResponseVO saveApplication(ExpertApplicationVO applicationVO,@Context HttpServletRequest request) {
		try{
			String userId = WebUtils.getCurrentLoginUser(request);
			UserProfileBean user = (UserProfileBean) this.expertRepository.get(UserProfileBean.class, Long.valueOf(userId), false);

			ExpertApplicationBean application = this.expertRepository.getApplication(Long.valueOf(userId));
			if(application == null){
				application = ExpertApplicationVO.getBean(applicationVO);
			}else{
				application.setRealName(applicationVO.getRealName());
				application.setCertNo(applicationVO.getCertNo());
				application.setCertType(applicationVO.getCertType());
				application.setTags(applicationVO.getTags());
			}

			application.setUser(user);
			this.expertRepository.save(application, WebUtils.getCurrentLoginUser(request));
			return new ResponseVO();
		}catch(Exception exc){
			Logger.error("Expert Application Exception.", exc);
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@POST
	@Path("/approve/{applicationId}/save")
	public ResponseVO saveApprovement(@PathParam("applicationId") String applicationId,@Context HttpServletRequest request) {
		try{
			String userId = WebUtils.getCurrentLoginUser(request);

			ExpertApplicationBean application = (ExpertApplicationBean) this.expertRepository.get(ExpertApplicationBean.class, Long.valueOf(applicationId), false);
			String applicantId = application.getCreatedUserId();

			UserProfileBean applicant = (UserProfileBean) this.expertRepository.get(UserProfileBean.class, Long.valueOf(applicantId), false);
			applicant.setExpert(UserProfileBean.EXPERT_APPROVED);
			this.expertRepository.save(applicant, false, userId);

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
			String userId = WebUtils.getCurrentLoginUser(request);
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
			List<ExpertServiceBean> services = this.expertRepository.getServices(Long.valueOf(expertId));
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
	@Path("/routes/{expertId}")
	public ResponseDataVO<List<RouteItemVO>> getRoutes(@Context HttpServletRequest request, @PathParam("expertId") String expertId){
		try{
			List<RouteMainBean> routes = this.expertRepository.getRoutes(Long.valueOf(expertId));
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
