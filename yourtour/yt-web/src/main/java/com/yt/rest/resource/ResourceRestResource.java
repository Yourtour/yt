package com.yt.rest.resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.service.IResourceService;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.FileUtils;
import com.yt.utils.SessionUtils;
import com.yt.vo.resource.ResourceVO;

/**
 * 和游徒项目相关的资源信息Restful接口
 */
@Component
@Path("app/resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceRestResource extends RestResource {
	private static final Log LOG = LogFactory
			.getLog(ResourceRestResource.class);

	@Autowired
	private IResourceService resourceService;

	/**
	 * 获取指定资源信息
	 * 
	 * @param resourceId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@Path("/{type}/{resourceId}")
	@GET
	public ResponseDataVO<ResourceVO> getResource(
			@PathParam("resourceId") Long resourceId,
			@PathParam("type") String type) throws Exception {
		ResourceBean resource = resourceService.getResource(resourceId,
				ResourceType.valueOf(type));
		ResourceVO vo = ResourceVO.transform(resource);

		return new ResponseDataVO<ResourceVO>(vo);
	}

	/**
	 * 保存资源信息
	 * 
	 * @param resourceId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/{type}/{resourceId}/save")
	@POST
	public ResponseVO saveResource(@PathParam("resourceId") Long resourceId,
			@PathParam("type") String type, ResourceVO vo,
			FormDataMultiPart form) throws Exception {
		ResourceBean resource = ResourceVO.transform(vo);

		StringBuffer images = new StringBuffer();
		List<FormDataBodyPart> l = form.getFields("images");
		if (l != null) {
			for (FormDataBodyPart p : l) {
				InputStream is = p.getValueAs(InputStream.class);
				FormDataContentDisposition detail = p
						.getFormDataContentDisposition();
				if (images.length() > 0)
					images.append(FILE_SEPERATOR);

				images.append(FileUtils.saveFile("images/resources",
						FileUtils.getType(detail.getFileName()), is));
			}
		}

		if (images.length() > 0) {
			resource.setImageUrl(images.toString());
		}

		resourceService.saveResource(resource,
				SessionUtils.getCurrentLoginUser());

		return new ResponseVO();
	}

	/**
	 * 删除资源
	 * 
	 * @param resourceId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@Path("/{type}/{resourceId}/delete")
	@GET
	public ResponseVO deleteResource(@PathParam("resourceId") Long resourceId,
			@PathParam("type") String type) throws Exception {
		resourceService.deleteResource(resourceId, ResourceType.valueOf(type),
				SessionUtils.getCurrentLoginUser());

		return new ResponseVO();
	}

	/**
	 * 获取目的地资源
	 * 
	 * @param placeId
	 * @return
	 * @throws Exception
	 */
	@Path("/place/{placeId}/{type}")
	@GET
	public ResponsePagingDataVO<List<ResourceVO>> getResources(
			@PathParam("placeId") Long placeId, @PathParam("type") String type,
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		List<ResourceVO> list = new ArrayList<>();
		PagingDataBean<List<? extends ResourceBean>> result = resourceService
				.getPlaceResources(placeId, ResourceType.valueOf(type),
						new PagingConditionBean(nextCursor, limit, total));
		for (ResourceBean bean : result.getData()) {
			if (bean == null) {
				continue;
			}
			list.add(ResourceVO.transform(bean));
		}

		return new ResponsePagingDataVO<List<ResourceVO>>(result.getTotal(),
				list.size(), list);
	}
}
