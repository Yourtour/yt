package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.repository.LineRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.vo.RecommendConditionVO;
import com.yt.vo.RecommendLineVO;
import com.yt.vo.RelationConditionVO;
import com.yt.vo.maintain.LineVO;

@Component
@Path("lines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LineRestResource {
	private static final Log LOG = LogFactory.getLog(LineRestResource.class);

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private ICrudOperate crudOperate;

	@Autowired
	private IFullTextSearchOperate ftsOperate;

	@Autowired
	private LineRepository lineRepo;

	@SuppressWarnings("unchecked")
	@GET
	public ResponseDataVO<List<LineVO>> getAllLines() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request fetch all the LineBean.");
		}
		List<LineVO> list = new ArrayList<LineVO>();
		try {
			List<LineBean> result = (List<LineBean>) crudOperate
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
		long graphId = getGraphIDFromString(id);
		try {
			LineBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, LineBean.class);
			} else {
				// id 是rowkey
				bean = (LineBean) crudOperate.get(LineBean.class, id);
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

	private long getGraphIDFromString(String id) {
		try {
			return Long.valueOf(id);
		} catch (Exception ex) {
			return -1;
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<LineVO> vos) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request import LineBean data.");
		}
		for (LineVO vo : vos) {
			ResponseVO response = save(vo);
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
	public ResponseVO save(LineVO vo) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The LineVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			LineBean bean = LineVO.transform(vo);
			crudOperate.save(bean, true);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save LineBean[‘%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the LineBean[id='%s'] fail.",
								vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@DELETE
	@Path("{id}")
	public ResponseVO delete(@PathParam("id") String id) {
		long graphId = getGraphIDFromString(id);
		try {
			LineBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, LineBean.class);
				id = bean.getRowKey();
			}
			crudOperate.delete(LineBean.class, id);
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
			Neo4jUtils.maintainRelateion(template, crudOperate,
					NodeRelationshipEnum.CONTAIN, lineId, LineBean.class,
					sceneId, SceneResourceBean.class, null, isAdd, false);
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
			List<LineBean> result = lineRepo.queryRecommandLine(places, dayNum,
					scenes);
			// List<LineBean> result = lineRepo.queryRecommandLine2(places,
			// dayNum,
			// scenes);
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
