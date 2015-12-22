package com.yt.rest.resource;

import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.member.ExpertApplicationVO;
import com.yt.vo.member.ExpertServiceVO;
import com.yt.vo.route.RouteServiceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.yt.response.ResponsePagingDataVO;
import com.yt.vo.member.ExpertVO;

@Component
@Path("/expert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpertRestResource {
	private static final Log Logger = LogFactory.getLog(ExpertRestResource.class);

	/**
	 * 保存达人申请
	 * @param userId
	 * @param applicationVO
	 * @param fileInputStream
	 * @return
	 */
	@POST
	@Path("/application/{userId}")
	public ResponseVO saveApplication(@PathParam("userId") String userId, ExpertApplicationVO applicationVO,@FormDataParam("file") InputStream fileInputStream) {
		try{
			return new ResponseVO();
		}catch(Exception exc){
			Logger.error("Expert Application Exception.", exc);
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@POST
	@Path("/application/{applicationId}/approve")
	public ResponseVO saveApprovement(@PathParam("applicationId") String applicationId) {
		try{

			return new ResponseVO();
		}catch(Exception exc){
			Logger.error("Expert Application Exception.", exc);
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 保存达人服务
	 * @param request
	 * @param serviceVO
	 * @param fileInputStream
	 * @return
	 */
	@POST
	@Path("/service/save")
	public ResponseVO saveService(@Context HttpServletRequest request, ExpertServiceVO serviceVO, @FormDataParam("file") InputStream fileInputStream){
		try{
			return new ResponseVO();
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 删除达人服务
	 * @param request
	 * @param serviceId
	 * @return
	 */
	@GET
	@Path("/service/{serviceId}/delete")
	public ResponseVO deleteService(@Context HttpServletRequest request, @PathParam("serviceId") String serviceId){
		try{


			return new ResponseVO();
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 获取达人服务
	 * @param request
	 * @param expertId
	 * @return
	 */
	@GET
	@Path("/services/{expertId}")
	public ResponseDataVO<List<ExpertServiceVO>> getServices(@Context HttpServletRequest request, @PathParam("expertId") String expertId){
		try{

			return new ResponseDataVO<List<ExpertServiceVO>>();
		} catch (Exception ex) {
			Logger.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<List<ExpertServiceVO>>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
