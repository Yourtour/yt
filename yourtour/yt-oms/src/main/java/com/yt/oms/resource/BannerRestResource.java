package com.yt.oms.resource;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.BannerBean;
import com.yt.business.service.IBannerService;
import com.yt.core.utils.BeanUtils;
import com.yt.oms.vo.home.BannerVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.rest.resource.RestResource;

@Component
@Path("/oms/banners/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BannerRestResource extends RestResource {
	public static final String BANNER_IMAGE_PATH = "/images/banners/";
	@Autowired
	private IBannerService bannerService;

	@GET
	@Path("/query")
	public ResponsePagingDataVO<List<BannerVO>> getBanners(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<BannerBean>> pagingData = bannerService
				.getBanners(new PagingConditionBean(nextCursor, limit, total));
		List<BannerVO> vos = new Vector<>();
		for (BannerBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(BannerVO.transform(bean));
		}

		return new ResponsePagingDataVO<List<BannerVO>>(pagingData.getTotal(),
				vos.size(), vos);
	}

	@GET
	@Path("/{bannerId}")
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
			@FormDataParam("banner") String json,
			@FormDataParam("files") List<FormDataBodyPart> files)
			throws Exception {
		BannerVO bannerVO = (BannerVO) BeanUtils.deserialize(json,
				BannerVO.class);
		BannerBean banner = BannerVO.transform(bannerVO);
		banner.setImageUrl(super.uploadMediaFile(files, BANNER_IMAGE_PATH));
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