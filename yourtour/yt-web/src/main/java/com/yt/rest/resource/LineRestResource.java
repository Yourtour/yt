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

import com.yt.business.bean.LineBean;
import com.yt.business.repository.LineRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.RelationConditionVO;
import com.yt.vo.route.LineVO;
import com.yt.vo.route.RecommendConditionVO;
import com.yt.vo.route.RecommendLineVO;

@Component
@Path("lines")
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

	@Path("loadPage.json")
	@GET
	public ResponsePagingDataVO<List<LineVO>> loadPage(
			@QueryParam("page") int page, @QueryParam("start") int start,
			@QueryParam("limit") int limit) {
		try {
			long totalSize = lineRepository.count(LineBean.class);
			if (start >= totalSize) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The query parameter is invalid, the total line: %d, but request: page(%d), start(%d), limit(%d).",
									totalSize, page, start, limit));
				}
				return new ResponsePagingDataVO<List<LineVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}

			Vector<LineVO> result = new Vector<LineVO>();
			List<LineBean> lines = lineRepository.getLinesByPage(start, limit);
			for (LineBean line : lines) {
				LineVO vo = LineVO.transform(line);
				if (vo == null) {
					continue;
				}
				result.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Query a page line success, total line: {%d}, request: page(%d), start(%d), limit(%d).",
								result.size(), page, start, limit));
			}
			return new ResponsePagingDataVO<List<LineVO>>(totalSize, result);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Query a page line fail, request: page(%d), start(%d), limit(%d).",
								page, start, limit), ex);
			}
			return new ResponsePagingDataVO<List<LineVO>>(
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
				bean = lineRepository.getLineByGraphId(graphId);
			} else {
				// id 是rowkey
				bean = (LineBean) lineRepository.get(LineBean.class, id);
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
			lineRepository.save(bean, operator, true);
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
				bean = lineRepository.getLineByGraphId(graphId);
				id = bean.getRowKey();
			}
			lineRepository.delete(LineBean.class, id);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Delete LineBean['%s'] success.", id));
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
			if (isAdd) {
				lineRepository.containScene(lineId, sceneId);
			} else {
				lineRepository.uncontainScene(lineId, sceneId);
			}
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

	@POST
	@Path("recommend")
	public ResponseDataVO<List<RecommendLineVO>> queryRecommendLine(
			RecommendConditionVO condition) {
		if (condition == null) {
			return new ResponseDataVO<List<RecommendLineVO>>(
					StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		if (condition.getPlaces() == null) {
			condition.setPlaces("");
		}
		if (condition.getScenes() == null) {
			condition.setScenes("");
		}
		String[] places = condition.getPlaces().split(",");
		int dayNum = condition.getDayNum();
		String[] scenes = condition.getScenes().split(",");
		try {
			List<LineBean> result = lineRepository.queryRecommandLine(places,
					dayNum, scenes);
			List<RecommendLineVO> list = new Vector<RecommendLineVO>(
					result.size());
			for (LineBean bean : result) {
				RecommendLineVO vo = RecommendLineVO.transform(bean);
				if (vo == null) {
					continue;
				}
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Fetch the recommand line success, total: %d, condition: places['%s'], dayNum[%d], scenes[’%s'].",
								list.size(), condition.getPlaces(),
								condition.getDayNum(), condition.getScenes()));
			}
			return new ResponseDataVO<List<RecommendLineVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Fetch data fail, condition[places='%s', dayNum=%d, scenes='%s'].",
								condition.getPlaces(), condition.getDayNum(),
								condition.getScenes()), ex);
			}
			return new ResponseDataVO<List<RecommendLineVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("recommend2")
	public ResponseDataVO<List<RecommendLineVO>> queryRecommendLine2(
			RecommendConditionVO condition) {
		if (condition == null) {
			return new ResponseDataVO<List<RecommendLineVO>>(
					StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		if (condition.getPlaces() == null) {
			condition.setPlaces("");
		}
		if (condition.getScenes() == null) {
			condition.setScenes("");
		}
		String[] places = condition.getPlaces().split(",");
		int dayNum = condition.getDayNum();
		String[] scenes = condition.getScenes().split(",");
		try {
			List<LineBean> result = lineRepository.queryRecommandLine2(places,
					dayNum, scenes);
			List<RecommendLineVO> list = new Vector<RecommendLineVO>(
					result.size());
			for (LineBean bean : result) {
				RecommendLineVO vo = RecommendLineVO.transform(bean);
				if (vo == null) {
					continue;
				}
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Fetch the recommand line success, total: %d, condition: places['%s'], dayNum[%d], scenes[’%s'].",
								list.size(), condition.getPlaces(),
								condition.getDayNum(), condition.getScenes()));
			}
			return new ResponseDataVO<List<RecommendLineVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Fetch data fail, condition[places='%s', dayNum=%d, scenes='%s'].",
								condition.getPlaces(), condition.getDayNum(),
								condition.getScenes()), ex);
			}
			return new ResponseDataVO<List<RecommendLineVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
