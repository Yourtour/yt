package com.yt.oms.resource;

import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.BannerBean;
import com.yt.business.service.IBannerService;
import com.yt.oms.vo.home.BannerVO;
import com.yt.response.ResponseDataVO;
import com.yt.rest.resource.RestResource;
import com.yt.utils.FileUtils;

@Component
@Path("banners/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BannerRestResource extends RestResource {
	public static final String BANNER_IMAGE_PATH = "/images/banners/";
	@Autowired
	private IBannerService bannerService;

	@GET
	public ResponseDataVO<List<BannerVO>> getBanners(
			@QueryParam("nextCursor") Long nextCursor,
			@QueryParam("limit") int limit) throws Exception {
		if (nextCursor == null) {
			nextCursor = 0l;
		}
		if (limit <= 0) {
			limit = RestResource.DEFAULT_LIMIT_APAGE;
		}
		List<BannerBean> banners = bannerService.getBanners(nextCursor, limit);
		List<BannerVO> vos = new Vector<>();
		for (BannerBean bean : banners) {
			if (bean == null) {
				continue;
			}
			vos.add(BannerVO.transform(bean));
		}
		return new ResponseDataVO<List<BannerVO>>(vos);
	}

	@GET
	@Path("/{bannerId")
	public ResponseDataVO<BannerVO> getBanner(
			@PathParam("bannerId") Long bannerId) throws Exception {
		BannerBean banner = bannerService.getBanner(bannerId);
		return new ResponseDataVO<BannerVO>(BannerVO.transform(banner));
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseDataVO<BannerVO> saveBanner(
			@Context HttpServletRequest request,
			@FormDataParam("id") Long bannerId,
			@FormDataParam("title") String title,
			@FormDataParam("subTitle") String subTitle,
			@FormDataParam("content") String content,
			@FormDataParam("startTime") long startTime,
			@FormDataParam("endTime") long endTime,
			@FormDataParam("status") String status, FormDataMultiPart form)
			throws Exception {
		BannerBean banner = new BannerBean();
		banner.setId(bannerId);
		banner.setTitle(title);
		banner.setSubTitle(subTitle);
		banner.setContent(content);
		banner.setStartTime(startTime);
		banner.setEndTime(endTime);
		banner.setStatus(BannerBean.Status.valueOf(status));
		// 保存图片
		List<FormDataBodyPart> l = form.getFields("userLogo");
		if (l != null) {
			for (FormDataBodyPart p : l) {
				InputStream is = p.getValueAs(InputStream.class);
				FormDataContentDisposition detail = p
						.getFormDataContentDisposition();
				banner.setImageUrl(FileUtils.saveFile(BANNER_IMAGE_PATH,
						FileUtils.getType(detail.getFileName()), is));
			}
		}
		Long userId = super.getCurrentUserId(request);
		bannerService.saveBanner(banner, userId);
		return new ResponseDataVO<BannerVO>(BannerVO.transform(banner));
	}

	@GET
	@Path("/{bannerId}/delete")
	public ResponseDataVO<BannerVO> deleteBanner(
			@PathParam("bannerId") Long bannerId,
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		BannerBean banner = bannerService.deleteBanner(bannerId, userId);
		return new ResponseDataVO<BannerVO>(BannerVO.transform(banner));
	}

}
