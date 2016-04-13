package com.yt.oms.resource;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.service.IUserService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.UserVO;
import com.yt.oms.vo.route.RouteVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.rest.resource.RestResource;
import com.yt.utils.SessionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/24.
 */
@Component
@Path("oms/common")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommonRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(CommonRestResource.class);

	@POST
	@Path("/image/{type}/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<String> uploadImage(@PathParam("type") String type,  FormDataMultiPart multipart) throws Exception{
		String imageUrls = super.uploadMediaFile(multipart, "imageUrl", "/images/" + type);
		return new ResponseDataVO<>(imageUrls);
	}
}
