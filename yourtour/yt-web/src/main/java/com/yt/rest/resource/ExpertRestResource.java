package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.service.IExpertService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.response.ResponseDataVO;
import com.yt.vo.member.ExpertApplicationVO;
import com.yt.vo.member.ExpertVO;
import com.yt.vo.route.RouteItemVO;

@Component
@Path("/app/experts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpertRestResource extends RestResource {
	private static final Log Logger = LogFactory
			.getLog(ExpertRestResource.class);

	@Autowired
	private IExpertService expertService;

	/**
	 * 查找目的地提供服务的达人
	 * 
	 * @param placeIds
	 * @param type
	 * @return
	 */
	@GET
	@Path("/places/{placeIds}/services/{serviceType}")
	public ResponseDataVO<List<ExpertVO>> query(
			@PathParam("placeIds") String placeIds,
			@PathParam("serviceType") String type) throws Exception {
		List<ExpertVO> experts = new ArrayList<>();
		List<UserProfileBean> beans = this.expertService.getExperts(placeIds,
				type.equalsIgnoreCase("ALL") ? null : type);
		if (CollectionUtils.isNotEmpty(beans)) {
			for (UserProfileBean expert : beans) {
				experts.add(ExpertVO.transform(expert));
			}
		}

		return new ResponseDataVO<List<ExpertVO>>(experts);
	}

	/**
	 * 获取达人信息
	 * 
	 * @param uid
	 * @return
	 */
	@GET
	@Path("/{uid}")
	public ResponseDataVO<ExpertVO> get(@PathParam("uid") Long uid)
			throws Exception {
		UserProfileBean bean = this.expertService.getExpert(uid);

		ExpertVO expert = ExpertVO.transform(bean);

		return new ResponseDataVO<ExpertVO>(expert);
	}

	/**
	 * 达人申请
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/application")
	public ResponseDataVO<ExpertApplicationVO> saveApplication(
			@Context HttpServletRequest request,
			@FormDataParam("application") String json,
			@FormDataParam("files") List<FormDataBodyPart> files)
			throws Exception {
		ExpertApplicationVO vo = (ExpertApplicationVO) BeanUtils.deserialize(
				json, ExpertApplicationVO.class);
		ExpertApplicationBean bean = new ExpertApplicationBean();
		vo.toBean(bean);
		Long userId = this.getCurrentUserId(request);
		ExpertApplicationBean application = expertService.saveApplication(bean,
				userId);
		return new ResponseDataVO<>(ExpertApplicationVO.transform(application));
	}

	/**
	 * 
	 * @param uid
	 * @return
	 */
	@GET
	@Path("/{uid}/routes")
	public ResponseDataVO<List<RouteItemVO>> getServicedRoutes(
			@PathParam("uid") Long uid) throws Exception {
		/*
		 * List<RouteItemVO> valueobjects = new ArrayList<>();
		 * 
		 * List<RouteMainBean> routes =
		 * this.expertService.getServicedRoutes(uid, 0l, 20); if(routes !=
		 * null){ for(RouteMainBean route : routes){ valueobjects.add(new
		 * RouteItemVO(route)); } } return new
		 * ResponseDataVO<List<RouteItemVO>>(valueobjects);
		 */

		return null;
	}

	/**
	 * 
	 * @param request
	 * @param uid
	 * @return
	 */
	@GET
	@Path("/{uid}/routes/recommend")
	public ResponseDataVO<List<RouteItemVO>> getRecommendRoutes(
			@PathParam("uid") Long uid) throws Exception {
		/*
		 * List<RouteMainBean> routes =
		 * this.expertService.getRecommendRoutes(uid, 0l, 20); List<RouteItemVO>
		 * valueobjects = new ArrayList<>();
		 * 
		 * if(routes != null){ for(RouteMainBean route : routes){
		 * valueobjects.add(new RouteItemVO(route)); } } return new
		 * ResponseDataVO<List<RouteItemVO>>(valueobjects);
		 */

		return null;
	}
}
