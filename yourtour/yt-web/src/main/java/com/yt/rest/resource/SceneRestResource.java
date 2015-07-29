package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

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

import com.yt.business.bean.SceneResourceBean;
import com.yt.error.StaticErrorEnum;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.vo.ResponseVO;
import com.yt.vo.SceneResourceVO;

@Component
@Path("scenes")
public class SceneRestResource {
	private static final Log LOG = LogFactory.getLog(SceneRestResource.class);

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private ICrudOperate crudOperate;

	@Autowired
	private IFullTextSearchOperate ftsOperate;

	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseVO<List<SceneResourceVO>> getAllScenes() {
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
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the SceneResourceBean fail.", ex);
			}
		}
		return new ResponseVO<List<SceneResourceVO>>(list);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseVO<SceneResourceVO> getScene(@PathParam("id") String id) {
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
				return new ResponseVO<SceneResourceVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			SceneResourceVO vo = SceneResourceVO.transform(bean);
			return new ResponseVO<SceneResourceVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch SceneResourceBean[id='%s'] fail.", id), ex);
			}
			return new ResponseVO<SceneResourceVO>(new SceneResourceVO());
			//return new ResponseVO<SceneResourceVO>(
			//		StaticErrorEnum.FETCH_DB_DATA_FAIL);
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
	@Consumes(MediaType.APPLICATION_JSON)
	public void importData(List<SceneResourceVO> vos) {
		for (SceneResourceVO vo : vos) {
			save(vo);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(SceneResourceVO vo) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The SceneVO is null.");
			}
			return;
		}
		try {
			SceneResourceBean bean = SceneResourceVO.transform(vo);
			crudOperate.save(bean, true);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the SceneResourceBean[id='%s'] fail.",
						vo.getRowKey()), ex);
			}
		}
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String id) {
		long graphId = getGraphIDFromString(id);
		try {
			SceneResourceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, SceneResourceBean.class);
				id = bean.getRowKey();
			}
			crudOperate.delete(SceneResourceBean.class, id);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch SceneResourceBean[id='%s'] fail.", id), ex);
			}
		}
	}
}
