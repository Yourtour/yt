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

import com.yt.business.bean.SceneResourceBean;
import com.yt.business.repository.SceneRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.resource.SceneResourceVO;

@Component
@Path("scenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SceneRestResource {
	private static final Log LOG = LogFactory.getLog(SceneRestResource.class);

	@Autowired
	private SceneRepository sceneRepository;

	@SuppressWarnings("unchecked")
	@Path("load")
	@GET
	public ResponseDataVO<List<SceneResourceVO>> getAllScenes() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request to fetch all the SceneResourceBean.");
		}
		List<SceneResourceVO> list = new ArrayList<SceneResourceVO>();
		try {
			List<SceneResourceBean> result = (List<SceneResourceBean>) sceneRepository
					.get(SceneResourceBean.class);
			for (SceneResourceBean bean : result) {
				if (bean == null) {
					continue;
				}
				SceneResourceVO vo = SceneResourceVO.transform(bean);
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch SceneResourceBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<SceneResourceVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the SceneResourceBean fail.", ex);
			}
			return new ResponseDataVO<List<SceneResourceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@Path("loadPage.json")
	@GET
	public ResponsePagingDataVO<List<SceneResourceVO>> loadPage(
			@QueryParam("page") int page, @QueryParam("start") int start,
			@QueryParam("limit") int limit) {
		try {
			long totalSize = sceneRepository.count(SceneResourceBean.class);
			if (start >= totalSize) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The query parameter is invalid, the total scene: %d, but request: page(%d), start(%d), limit(%d).",
									totalSize, page, start, limit));
				}
				return new ResponsePagingDataVO<List<SceneResourceVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}

			Vector<SceneResourceVO> result = new Vector<SceneResourceVO>();
			List<SceneResourceBean> scenes = sceneRepository.getScenesByPage(
					start, limit);
			for (SceneResourceBean scene : scenes) {
				SceneResourceVO vo = SceneResourceVO.transform(scene);
				if (vo == null) {
					continue;
				}
				result.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Query a page scene success, total scene: {%d}, request: page(%d), start(%d), limit(%d).",
								result.size(), page, start, limit));
			}
			return new ResponsePagingDataVO<List<SceneResourceVO>>(totalSize,
					result);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Query a page scene fail, request: page(%d), start(%d), limit(%d).",
								page, start, limit), ex);
			}
			return new ResponsePagingDataVO<List<SceneResourceVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}")
	public ResponseDataVO<SceneResourceVO> getScene(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			SceneResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = sceneRepository.getSceneByGraphId(graphId);
			} else {
				// id 是rowkey
				bean = (SceneResourceBean) sceneRepository.get(
						SceneResourceBean.class, "rowKey", id);
			}
			if (bean == null) {
				return new ResponseDataVO<SceneResourceVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			SceneResourceVO vo = SceneResourceVO.transform(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch SceneResourceBean[id='%s'] success.", id));
			}
			return new ResponseDataVO<SceneResourceVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch SceneResourceBean[id='%s'] fail.", id), ex);
			}
			return new ResponseDataVO<SceneResourceVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<SceneResourceVO> vos) {
		for (SceneResourceVO vo : vos) {
			ResponseVO response = save(null, vo, "admin");
			if (response.getErrorCode() != 0) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("Import SceneResourceBean fail, error Bean[id=''%s'].",
									vo.getRowKey()));
				}
				return response;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Import SceneResourceBean success, total: %d.", vos.size()));
		}
		return new ResponseVO();
	}

	@POST
	@Path("save.json")
	public ResponseVO saveByAdd(SceneResourceVO vo,
			@Context HttpServletRequest request) {
		return save(null, vo, WebUtils.getCurrentLoginUser(request));
	}

	@POST
	@Path("save/{id}.json")
	public ResponseVO saveByUpdate(@PathParam("id") String id,
			SceneResourceVO vo, @Context HttpServletRequest request) {
		return save(id, vo, WebUtils.getCurrentLoginUser(request));
	}

	private ResponseVO save(String id, SceneResourceVO vo, String operator) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The SceneResourceVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			SceneResourceBean bean = SceneResourceVO.transform(vo);
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
			sceneRepository.save(bean, operator);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save SceneResourceBean['%s'] success.", vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Save the SceneResourceBean[id='%s'] fail.",
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
			SceneResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = sceneRepository.getSceneByGraphId(graphId);
			} else {
				// id是rowkey
				bean = (SceneResourceBean) sceneRepository.get(
						SceneResourceBean.class, "rowKey", id);
			}
			id = bean.getRowKey();
			sceneRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete SceneResourceBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch SceneResourceBean[id='%s'] fail.", id), ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
