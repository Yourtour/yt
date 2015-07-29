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
import com.yt.error.StaticErrorEnum;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.vo.PersonalRouteVO;
import com.yt.vo.ResponseVO;
import com.yt.vo.RouteVO;

@Component
@Path("routes")
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

	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseVO<List<RouteVO>> getAllRoutes() {
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
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the RouteBean fail.", ex);
			}
		}
		return new ResponseVO<List<RouteVO>>(list);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseVO<RouteVO> getRoute(@PathParam("id") String id) {
		long graphId = getGraphIDFromString(id);
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
				return new ResponseVO<RouteVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			RouteVO vo = RouteVO.transform(bean);
			return new ResponseVO<RouteVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch RouteBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseVO<RouteVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
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
	public void importData(List<RouteVO> vos) {
		for (RouteVO vo : vos) {
			save(vo);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(RouteVO vo) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteVO is null.");
			}
			return;
		}
		try {
			RouteBean bean = RouteVO.transform(vo);
			crudOperate.save(bean, true);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the RouteBean[id='%s'] fail.",
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
			RouteBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, RouteBean.class);
				id = bean.getRowKey();
			}
			crudOperate.delete(RouteBean.class, id);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch RouteBean[id='%s'] fail.", id),
						ex);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("personal")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseVO<List<PersonalRouteVO>> getPersonalRoute() {
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
				// TODO 未来是否需要考虑向上取整？
				vo.setPeriod(bean.getPeriod() / 3600 / 24); // 将秒换算为天，并取整
				vo.setStartTime(DATE_FORMAT.format(new Date(bean.getStartTime())));
				vo.setEndTime(DATE_FORMAT.format(new Date(bean.getEndTime())));
				list.add(vo);
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the RouteBean fail.", ex);
			}
		}
		return new ResponseVO<List<PersonalRouteVO>>(list);
	}
}
