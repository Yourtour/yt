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
import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.repository.LineRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.AbbrVO;
import com.yt.vo.RelationConditionVO;
import com.yt.vo.route.LineVO;

@Component
@Path("app/lines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LineRestResource {
	private static final Log LOG = LogFactory.getLog(LineRestResource.class);

	@Autowired
	private LineRepository lineRepository;

	@SuppressWarnings("unchecked")
	@Path("load")
	@GET
	public ResponseDataVO<List<LineVO>> getAllLines() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request fetch all the LineBean.");
		}
		List<LineVO> list = new ArrayList<LineVO>();
		try {
			List<LineBean> result = (List<LineBean>) lineRepository
					.get(LineBean.class);
			for (LineBean bean : result) {
				if (bean == null) {
					continue;
				}
				LineVO vo = LineVO.transform(bean);
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch all the LineBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<LineVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the LineBean fail.", ex);
			}
			return new ResponseDataVO<List<LineVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@SuppressWarnings("unchecked")
	@Path("match/{placeIds}")
	@GET
	public ResponseDataVO<List<LineVO>> getAllMatchedLines(@PathParam("placeIds") String placeIds) {
		List<LineVO> list = new ArrayList<LineVO>();
		try {
			List<LineBean> result = (List<LineBean>) lineRepository
					.get(LineBean.class);
			for (LineBean bean : result) {
				if (bean == null) {
					continue;
				}
				LineVO vo = LineVO.transform(bean);
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch all the LineBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<LineVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the LineBean fail.", ex);
			}
			return new ResponseDataVO<List<LineVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}")
	public ResponseDataVO<LineVO> getLine(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			LineBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (LineBean) lineRepository.get(LineBean.class, graphId);
			} else {
				// id 是rowkey
				bean = (LineBean) lineRepository.get(LineBean.class, "rowKey",
						id);
			}
			if (bean == null) {
				return new ResponseDataVO<LineVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			LineVO vo = LineVO.transform(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Get the LineBean['%s'] success.", id));
			}
			return new ResponseDataVO<LineVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch LineBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseDataVO<LineVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}/scenes")
	public ResponseDataVO<List<AbbrVO>> getScenesByPlace(
			@PathParam("id") String placeId) {
		long graphId = Neo4jUtils.getGraphIDFromString(placeId);
		try {
			PlaceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (PlaceBean) lineRepository.get(PlaceBean.class, graphId);
			} else {
				// id 是rowkey
				bean = (PlaceBean) lineRepository.get(PlaceBean.class,
						"rowKey", placeId);
			}
			if (bean == null) {
				return new ResponseDataVO<List<AbbrVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			graphId = bean.getGraphId();
			List<SceneResourceBean> scenes = lineRepository
					.getScenesByPlace(graphId);
			List<AbbrVO> list = new Vector<AbbrVO>();
			for (SceneResourceBean scene : scenes) {
				if (scene == null) {
					continue;
				}
				AbbrVO vo = new AbbrVO(String.valueOf(scene.getGraphId()),
						scene.getName());
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch all the scenes by PlaceBean[id='%s'] success.",
						placeId));
			}
			return new ResponseDataVO<List<AbbrVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch all the scenes by PlaceBean[id='%s'] fail.",
						placeId), ex);
			}
			return new ResponseDataVO<List<AbbrVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}/hotels")
	public ResponseDataVO<List<AbbrVO>> getHotelsByPlace(
			@PathParam("id") String placeId) {
		long graphId = Neo4jUtils.getGraphIDFromString(placeId);
		try {
			PlaceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (PlaceBean) lineRepository.get(PlaceBean.class, graphId);
			} else {
				// id 是rowkey
				bean = (PlaceBean) lineRepository.get(PlaceBean.class,
						"rowKey", placeId);
			}
			if (bean == null) {
				return new ResponseDataVO<List<AbbrVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			graphId = bean.getGraphId();
			List<HotelResourceBean> hotels = lineRepository
					.getHotelsByPlace(graphId);
			List<AbbrVO> list = new Vector<AbbrVO>();
			for (HotelResourceBean hotel : hotels) {
				if (hotel == null) {
					continue;
				}
				AbbrVO vo = new AbbrVO(String.valueOf(hotel.getGraphId()),
						hotel.getName());
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch all the hotels by PlaceBean[id='%s'] success.",
						placeId));
			}
			return new ResponseDataVO<List<AbbrVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch all the hotels by PlaceBean[id='%s'] fail.",
						placeId), ex);
			}
			return new ResponseDataVO<List<AbbrVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}/restaurants")
	public ResponseDataVO<List<AbbrVO>> getRestaurantsByPlace(
			@PathParam("id") String placeId) {
		long graphId = Neo4jUtils.getGraphIDFromString(placeId);
		try {
			PlaceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (PlaceBean) lineRepository.get(PlaceBean.class, graphId);
			} else {
				// id 是rowkey
				bean = (PlaceBean) lineRepository.get(PlaceBean.class,
						"rowKey", placeId);
			}
			if (bean == null) {
				return new ResponseDataVO<List<AbbrVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			graphId = bean.getGraphId();
			List<RestaurantResourceBean> restaurants = lineRepository
					.getRestaurantsByPlace(graphId);
			List<AbbrVO> list = new Vector<AbbrVO>();
			for (RestaurantResourceBean restaurant : restaurants) {
				if (restaurant == null) {
					continue;
				}
				AbbrVO vo = new AbbrVO(String.valueOf(restaurant.getGraphId()),
						restaurant.getName());
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Fetch all the restaurants by PlaceBean[id='%s'] success.",
								placeId));
			}
			return new ResponseDataVO<List<AbbrVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Fetch all the restaurants by PlaceBean[id='%s'] fail.",
								placeId), ex);
			}
			return new ResponseDataVO<List<AbbrVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<LineVO> vos) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request import LineBean data.");
		}
		for (LineVO vo : vos) {
			ResponseVO response = save(null, vo, "admin");
			if (response.getErrorCode() != 0) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"Import LineBean data fail, error: id = '%s'.",
							vo.getRowKey()));
				}
				return response;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Import LineBean data success, total: %d.",
					vos.size()));
		}
		return new ResponseVO();
	}

	@POST
	@Path("save.json")
	public ResponseVO saveByAdd(LineVO vo, @Context HttpServletRequest request) {
		return save(null, vo, WebUtils.getCurrentLoginUser(request));
	}

	@POST
	@Path("save/{id}.json")
	public ResponseVO saveByUpdate(@PathParam("id") String id, LineVO vo,
			@Context HttpServletRequest request) {
		return save(id, vo, WebUtils.getCurrentLoginUser(request));
	}

	private ResponseVO save(String id, LineVO vo, String operator) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The LineVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			LineBean bean = LineVO.transform(vo);
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
			lineRepository.save(bean, operator);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save LineBean['%d'] success.",
						bean.getGraphId()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Save the LineBean[id='%s'] fail.",
						String.valueOf(id)), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@GET
	@Path("remove/{id}.json")
	public ResponseVO delete(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			LineBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (LineBean) lineRepository.get(LineBean.class, graphId);
			} else {
				// id是rowkey
				bean = (LineBean) lineRepository.get(LineBean.class, "rowKey",
						id);
			}
			id = bean.getRowKey();
			lineRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Delete LineBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch LineBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("contain")
	public ResponseVO containScene(RelationConditionVO condition) {
		if (condition == null) {
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		String lineId = condition.getSrcId(), sceneId = condition.getTarId();
		boolean isAdd = condition.isAdd();

		try {
			LineBean line = null;
			long graphId = Neo4jUtils.getGraphIDFromString(lineId);
			if (graphId != -1) {
				// id是GraphID
				line = (LineBean) lineRepository.get(LineBean.class, graphId);
			} else {
				// id是rowkey
				line = (LineBean) lineRepository.get(LineBean.class, "rowKey",
						lineId);
			}
			SceneResourceBean scene = null;
			graphId = Neo4jUtils.getGraphIDFromString(sceneId);
			if (graphId != -1) {
				// id是GraphID
				scene = (SceneResourceBean) lineRepository.get(
						SceneResourceBean.class, graphId);
			} else {
				// id是rowkey
				scene = (SceneResourceBean) lineRepository.get(
						SceneResourceBean.class, "rowKey", lineId);
			}
			if (line == null || scene == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The LineBean(%s) or SceneResourceBean(%s) is null.",
									lineId, sceneId));
				}
				return new ResponseVO(StaticErrorEnum.THE_DATA_NOT_EXIST);
			}

			int found = -1;
			for (int index = 0; index < line.getScenes().size(); index++) {
				if (line.getScenes().get(index).getGraphId().longValue() == scene
						.getGraphId().longValue()) {
					found = index;
					break;
				}
			}
			if (isAdd) {
				if (found != -1) {
					line.getScenes().add(scene);
				}
			} else {
				if (found != -1) {
					line.getScenes().remove(found);
				}
			}
			lineRepository.saveRelationsOnly(line);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("'%s' contain from LineBean['%s'] to SceneResourceBean['%s'] success.",
								isAdd ? "Add" : "Remove", lineId, sceneId));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isDebugEnabled()) {
				LOG.error(
						String.format(
								"Relate from LineBean['%s'] to SceneResourceBean['%s'] fail.",
								lineId, sceneId), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

}
