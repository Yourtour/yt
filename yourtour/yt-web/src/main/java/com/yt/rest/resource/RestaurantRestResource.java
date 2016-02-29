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

import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.repository.RestaurantRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.resource.RestaurantResourceVO;

@Component
@Path("app/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantRestResource {
	private static final Log LOG = LogFactory
			.getLog(RestaurantRestResource.class);

	@Autowired
	private RestaurantRepository restaurantRepository;

	@SuppressWarnings("unchecked")
	@Path("load")
	@GET
	public ResponseDataVO<List<RestaurantResourceVO>> getAllScenes() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request to fetch all the RestaurantResourceBean.");
		}
		List<RestaurantResourceVO> list = new ArrayList<RestaurantResourceVO>();
		try {
			List<RestaurantResourceBean> result = (List<RestaurantResourceBean>) restaurantRepository
					.get(RestaurantResourceBean.class);
			for (RestaurantResourceBean bean : result) {
				if (bean == null) {
					continue;
				}
				RestaurantResourceVO vo = RestaurantResourceVO.transform(bean);
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch RestaurantResourceBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<RestaurantResourceVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the RestaurantResourceBean fail.", ex);
			}
			return new ResponseDataVO<List<RestaurantResourceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@SuppressWarnings("unchecked")
	@Path("query")
	@GET
	public ResponsePagingDataVO<List<RestaurantResourceVO>> loadPage(@QueryParam("name") String name, @QueryParam("placeId") String placeId,
			@QueryParam("page") int page, @QueryParam("start") int start,
			@QueryParam("limit") int limit) {
		try {
			long totalSize = restaurantRepository
					.count(RestaurantResourceBean.class);
			if (start >= totalSize) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The query parameter is invalid, the total restaurant: %d, but request: page(%d), start(%d), limit(%d).",
									totalSize, page, start, limit));
				}
				return new ResponsePagingDataVO<List<RestaurantResourceVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}

			Vector<RestaurantResourceVO> result = new Vector<RestaurantResourceVO>();
			List<RestaurantResourceBean> restaurants = (List<RestaurantResourceBean>) restaurantRepository
					.getByPage(RestaurantResourceBean.class, start, limit);
			for (RestaurantResourceBean restaurant : restaurants) {
				RestaurantResourceVO vo = RestaurantResourceVO
						.transform(restaurant);
				if (vo == null) {
					continue;
				}
				result.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Query a page restaurant success, total restaurant: {%d}, request: page(%d), start(%d), limit(%d).",
								result.size(), page, start, limit));
			}
			return new ResponsePagingDataVO<List<RestaurantResourceVO>>(
					totalSize, result);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Query a page restaurant fail, request: page(%d), start(%d), limit(%d).",
								page, start, limit), ex);
			}
			return new ResponsePagingDataVO<List<RestaurantResourceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}")
	public ResponseDataVO<RestaurantResourceVO> getRestaurant(
			@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RestaurantResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RestaurantResourceBean) restaurantRepository.get(
						RestaurantResourceBean.class, graphId);
			} else {
				// id 是rowkey
				bean = (RestaurantResourceBean) restaurantRepository.get(
						RestaurantResourceBean.class, "rowKey", id);
			}
			if (bean == null) {
				return new ResponseDataVO<RestaurantResourceVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			RestaurantResourceVO vo = RestaurantResourceVO.transform(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch RestaurantResourceBean[id='%s'] success.", id));
			}
			return new ResponseDataVO<RestaurantResourceVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch RestaurantResourceBean[id='%s'] fail.", id), ex);
			}
			return new ResponseDataVO<RestaurantResourceVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<RestaurantResourceVO> vos) {
		for (RestaurantResourceVO vo : vos) {
			ResponseVO response = save(null, vo, "admin");
			if (response.getErrorCode() != 0) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("Import RestaurantResourceBean fail, error Bean[id=''%s'].",
									vo.getRowKey()));
				}
				return response;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Import RestaurantResourceBean success, total: %d.",
					vos.size()));
		}
		return new ResponseVO();
	}

	@POST
	@Path("save.json")
	public ResponseVO saveByAdd(RestaurantResourceVO vo,
			@Context HttpServletRequest request) {
		return save(null, vo, WebUtils.getCurrentLoginUser(request));
	}

	@POST
	@Path("save/{id}.json")
	public ResponseVO saveByUpdate(@PathParam("id") String id,
			RestaurantResourceVO vo, @Context HttpServletRequest request) {
		return save(id, vo, WebUtils.getCurrentLoginUser(request));
	}

	private ResponseVO save(String id, RestaurantResourceVO vo, String operator) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RestaurantResourceVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RestaurantResourceBean bean = RestaurantResourceVO.transform(vo);
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
			restaurantRepository.save(bean, operator);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save RestaurantResourceBean['%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the RestaurantResourceBean[id='%s'] fail.",
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
			RestaurantResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RestaurantResourceBean) restaurantRepository.get(
						RestaurantResourceBean.class, graphId);
			} else {
				// id是rowkey
				bean = (RestaurantResourceBean) restaurantRepository.get(
						RestaurantResourceBean.class, "rowKey", id);
			}
			id = bean.getRowKey();
			restaurantRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete RestaurantResourceBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch RestaurantResourceBean[id='%s'] fail.", id), ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
