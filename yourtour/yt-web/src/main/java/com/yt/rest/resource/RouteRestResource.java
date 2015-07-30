package com.yt.rest.resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.yt.business.bean.RouteBean;
import com.yt.business.repository.RouteRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.rsal.neo4j.util.Neo4jUtils;
import com.yt.vo.PersonalRouteVO;
import com.yt.vo.ResponseDataVO;
import com.yt.vo.ResponseVO;
import com.yt.vo.RouteVO;

@Component
@Path("routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteRestResource {
	private static final Log LOG = LogFactory.getLog(RouteRestResource.class);
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private ICrudOperate crudOperate;

	@Autowired
	private IFullTextSearchOperate ftsOperate;

	@Autowired
	private RouteRepository routeRepo;

	@SuppressWarnings("unchecked")
	@GET
	public ResponseDataVO<List<RouteVO>> getAllRoutes() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request fetch all teh RouteBean.");
		}
		List<RouteVO> list = new ArrayList<RouteVO>();
		try {
			List<RouteBean> result = (List<RouteBean>) crudOperate
					.get(RouteBean.class);
			for (RouteBean bean : result) {
				if (bean == null) {
					continue;
				}
				RouteVO vo = RouteVO.transform(bean);
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch all the RouteBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<RouteVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the RouteBean fail.", ex);
			}
			return new ResponseDataVO<List<RouteVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}")
	public ResponseDataVO<RouteVO> getRoute(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, RouteBean.class);
			} else {
				// id 是rowkey
				bean = (RouteBean) crudOperate.get(RouteBean.class, id);
			}
			if (bean == null) {
				return new ResponseDataVO<RouteVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			RouteVO vo = RouteVO.transform(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Get RouteBean['%s'] success.", id));
			}
			return new ResponseDataVO<RouteVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch RouteBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseDataVO<RouteVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<RouteVO> vos) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request import RouteBean data.");
		}
		for (RouteVO vo : vos) {
			ResponseVO response = save(vo);
			if (response.getErrorCode() != 0) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"Import RouteBean data fail, error: id = '%s'.",
							vo.getRowKey()));
				}
				return response;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Import RouteBean data success, total: %d.", vos.size()));
		}
		return new ResponseVO();
	}

	@POST
	public ResponseVO save(RouteVO vo) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteBean bean = RouteVO.transform(vo);
			crudOperate.save(bean, true);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save RouteBean['%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the RouteBean[id='%s'] fail.",
								vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseVO delete(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, RouteBean.class);
				id = bean.getRowKey();
			}
			crudOperate.delete(RouteBean.class, id);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Delete RouteBean['%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch RouteBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("relate_{type}/{rid}/{lid}")
	public ResponseVO relateLine(@PathParam("type") String type,
			@PathParam("rid") String routeId, @PathParam("lid") String lineId) {
		try {
			routeRepo.relateRoute2Line(routeId, lineId,
					type.equalsIgnoreCase("add"));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("'%s' relateion from RouteBean['%s'] to LineBean['%s'] success.",
								type, routeId, lineId));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isDebugEnabled()) {
				LOG.error(String.format(
						"Relate from RouteBean['%s'] to LineBean['%s'] fail.",
						routeId, lineId), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@GET
	@Path("contain_{type}/{rid}/{lid}")
	public ResponseVO containScene(@PathParam("type") String type,
			@PathParam("rid") String routeId, @PathParam("lid") String sceneId) {
		try {
			routeRepo.relateRoute2Scene(routeId, sceneId,
					type.equalsIgnoreCase("add"));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("'%s' contain from RouteBean['%s'] to SceneResourceBean['%s'] success.",
								type, routeId, sceneId));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isDebugEnabled()) {
				LOG.error(
						String.format(
								"Relate from RouteBean['%s'] to SceneResourceBean['%s'] fail.",
								routeId, sceneId), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("personal")
	public ResponseDataVO<List<PersonalRouteVO>> getPersonalRoute() {
		List<PersonalRouteVO> list = new ArrayList<PersonalRouteVO>();
		try {
			List<RouteBean> result = (List<RouteBean>) crudOperate
					.get(RouteBean.class);
			for (RouteBean bean : result) {
				if (bean == null) {
					continue;
				}
				PersonalRouteVO vo = new PersonalRouteVO(bean.getGraphId(),
						bean.getRowKey(), bean.getName());
				vo.setImageUrl(bean.getImageUrl());
				vo.setLineName(bean.getLineName());
				vo.setPlace(bean.getPlace());
				vo.setReason(bean.getReason());
				// 将秒换算为天，并向上取整
				int period = bean.getPeriod() / 3600 / 24;
				if (period * 24 * 3600 < bean.getPeriod()) {
					period += 1;
				}
				vo.setPeriod(period);
				vo.setStartTime(DATE_FORMAT.format(new Date(bean.getStartTime())));
				vo.setEndTime(DATE_FORMAT.format(new Date(bean.getEndTime())));
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch the personal RouteBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<PersonalRouteVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the RouteBean fail.", ex);
			}
			return new ResponseDataVO<List<PersonalRouteVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
