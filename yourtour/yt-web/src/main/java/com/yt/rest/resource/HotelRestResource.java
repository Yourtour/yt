package com.yt.rest.resource;

import java.util.ArrayList;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.repository.HotelRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.resource.HotelResourceVO;

@Component
@Path("hotels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HotelRestResource {
	private static final Log LOG = LogFactory.getLog(HotelRestResource.class);

	@Autowired
	private HotelRepository hotelRepository;

	@SuppressWarnings("unchecked")
	@Path("load")
	@GET
	public ResponseDataVO<List<HotelResourceVO>> getAllScenes() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request to fetch all the HotelResourceBean.");
		}
		List<HotelResourceVO> list = new ArrayList<HotelResourceVO>();
		try {
			List<HotelResourceBean> result = (List<HotelResourceBean>) hotelRepository
					.get(HotelResourceBean.class);
			for (HotelResourceBean bean : result) {
				if (bean == null) {
					continue;
				}
				HotelResourceVO vo = HotelResourceVO.transform(bean);
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch HotelResourceBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<HotelResourceVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the HotelResourceBean fail.", ex);
			}
			return new ResponseDataVO<List<HotelResourceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@Path("loadPage.json")
	@GET
	public ResponsePagingDataVO<List<HotelResourceVO>> loadPage(
			@QueryParam("page") int page, @QueryParam("start") int start,
			@QueryParam("limit") int limit) {
		try {
			long totalSize = hotelRepository.count(HotelResourceBean.class);
			if (start >= totalSize) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The query parameter is invalid, the total hotel: %d, but request: page(%d), start(%d), limit(%d).",
									totalSize, page, start, limit));
				}
				return new ResponsePagingDataVO<List<HotelResourceVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}

			Vector<HotelResourceVO> result = new Vector<HotelResourceVO>();
			List<HotelResourceBean> hotels = hotelRepository.getHotelsByPage(
					start, limit);
			for (HotelResourceBean restaurant : hotels) {
				HotelResourceVO vo = HotelResourceVO.transform(restaurant);
				if (vo == null) {
					continue;
				}
				result.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Query a page hotel success, total hotel: {%d}, request: page(%d), start(%d), limit(%d).",
								result.size(), page, start, limit));
			}
			return new ResponsePagingDataVO<List<HotelResourceVO>>(totalSize,
					result);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Query a page hotel fail, request: page(%d), start(%d), limit(%d).",
								page, start, limit), ex);
			}
			return new ResponsePagingDataVO<List<HotelResourceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}")
	public ResponseDataVO<HotelResourceVO> getRestaurant(
			@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			HotelResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = hotelRepository.getHotelByGraphId(graphId);
			} else {
				// id 是rowkey
				bean = (HotelResourceBean) hotelRepository.get(
						HotelResourceBean.class, "rowKey", id);
			}
			if (bean == null) {
				return new ResponseDataVO<HotelResourceVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			HotelResourceVO vo = HotelResourceVO.transform(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch HotelResourceBean[id='%s'] success.", id));
			}
			return new ResponseDataVO<HotelResourceVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch HotelResourceBean[id='%s'] fail.", id), ex);
			}
			return new ResponseDataVO<HotelResourceVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<HotelResourceVO> vos) {
		for (HotelResourceVO vo : vos) {
			ResponseVO response = save(null, vo, "admin");
			if (response.getErrorCode() != 0) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("Import HotelResourceBean fail, error Bean[id=''%s'].",
									vo.getRowKey()));
				}
				return response;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Import HotelResourceBean success, total: %d.", vos.size()));
		}
		return new ResponseVO();
	}

	@POST
	@Path("save.json")
	public ResponseVO saveByAdd(HotelResourceVO vo,
			@Context HttpServletRequest request) {
		return save(null, vo, WebUtils.getCurrentLoginUser(request));
	}

	@POST
	@Path("save/{id}.json")
	public ResponseVO saveByUpdate(@PathParam("id") String id,
			HotelResourceVO vo, @Context HttpServletRequest request) {
		return save(id, vo, WebUtils.getCurrentLoginUser(request));
	}

	private ResponseVO save(String id, HotelResourceVO vo, String operator) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The HotelResourceVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			HotelResourceBean bean = HotelResourceVO.transform(vo);
			if (id == null) {
				// 新增
				bean.setGraphId(null);
			} else {
				// 修改
				long graphId = Neo4jUtils.getGraphIDFromString(id);
				if (graphId != -1l) {
					// ID是Graph ID
					bean.setGraphId(graphId);
				} else {
					// ID是Row Key
					bean.setRowKey(id);
				}
			}
			hotelRepository.save(bean, operator);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save HotelResourceBean['%s'] success.", vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Save the HotelResourceBean[id='%s'] fail.",
								vo.getId()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@GET
	@Path("remove/{id}.json")
	public ResponseVO delete(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			HotelResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = hotelRepository.getHotelByGraphId(graphId);
			} else {
				// id是rowkey
				bean = (HotelResourceBean) hotelRepository.get(
						HotelResourceBean.class, "rowKey", id);
			}
			id = bean.getRowKey();
			hotelRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete HotelResourceBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch HotelResourceBean[id='%s'] fail.", id), ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
