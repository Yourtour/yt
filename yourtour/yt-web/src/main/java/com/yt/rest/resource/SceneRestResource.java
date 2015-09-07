package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.business.bean.SceneResourceBean;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.utils.WebUtils;
import com.yt.vo.maintain.SceneResourceVO;

@Component
@Path("scenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SceneRestResource {
	private static final Log LOG = LogFactory.getLog(SceneRestResource.class);

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	@Qualifier("crudGeneralOperate")
	private ICrudOperate crudOperate;

	@Autowired
	private IFullTextSearchOperate ftsOperate;

	@SuppressWarnings("unchecked")
	@GET
	public ResponseDataVO<List<SceneResourceVO>> getAllScenes() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request to fetch all the SceneResourceBean.");
		}
		List<SceneResourceVO> list = new ArrayList<SceneResourceVO>();
		try {
			List<SceneResourceBean> result = (List<SceneResourceBean>) crudOperate
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

	@GET
	@Path("{id}")
	public ResponseDataVO<SceneResourceVO> getScene(@PathParam("id") String id) {
		long graphId = getGraphIDFromString(id);
		try {
			SceneResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, SceneResourceBean.class);
			} else {
				// id 是rowkey
				bean = (SceneResourceBean) crudOperate.get(
						SceneResourceBean.class, id);
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

	private long getGraphIDFromString(String id) {
		try {
			return Long.valueOf(id);
		} catch (Exception ex) {
			return -1;
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<SceneResourceVO> vos) {
		for (SceneResourceVO vo : vos) {
			ResponseVO response = save(vo, "admin");
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
	public ResponseVO save(SceneResourceVO vo,
			@Context HttpServletRequest request) {
		return save(vo, WebUtils.getCurrentLoginUser(request));
	}

	private ResponseVO save(SceneResourceVO vo, String operator) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The SceneVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			SceneResourceBean bean = SceneResourceVO.transform(vo);
			crudOperate.save(bean, operator, true);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save SceneResourceBean[id='%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the SceneResourceBean[id='%s'] fail.",
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
			SceneResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, SceneResourceBean.class);
				id = bean.getRowKey();
			}
			crudOperate.delete(SceneResourceBean.class, id);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete SceneResourceBean['%s'] success.", id));
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
