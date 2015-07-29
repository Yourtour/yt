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
import com.yt.business.repository.LineBeanRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.vo.LineVO;
import com.yt.vo.RecommandLineVO;
import com.yt.vo.ResponseVO;

@Component
@Path("lines")
public class LineRestResource {
	private static final Log LOG = LogFactory.getLog(LineRestResource.class);

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private ICrudOperate crudOperate;

	@Autowired
	private IFullTextSearchOperate ftsOperate;

	@Autowired
	private LineBeanRepository lineRepo;

	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseVO<List<LineVO>> getAllLines() {
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
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the LineBean fail.", ex);
			}
		}
		return new ResponseVO<List<LineVO>>(list);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseVO<LineVO> getLine(@PathParam("id") String id) {
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
				return new ResponseVO<LineVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			LineVO vo = LineVO.transform(bean);
			return new ResponseVO<LineVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch LineBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseVO<LineVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
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
	public void importData(List<LineVO> vos) {
		for (LineVO vo : vos) {
			save(vo);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(LineVO vo) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The LineVO is null.");
			}
			return;
		}
		try {
			LineBean bean = LineVO.transform(vo);
			crudOperate.save(bean, true);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the LineBean[id='%s'] fail.",
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
			LineBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, LineBean.class);
				id = bean.getRowKey();
			}
			crudOperate.delete(LineBean.class, id);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch LineBean[id='%s'] fail.", id),
						ex);
			}
		}
	}

	@POST
	@Path("recommand")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseVO<List<RecommandLineVO>> queryRecommandLine(
			RecommandConditionVO condition) {
		if (condition == null) {
			return new ResponseVO<List<RecommandLineVO>>(
					StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		String[] places = condition.getPlaces().split(",");
		int dayNum = condition.getDayNum();
		String[] scenes = condition.getScenes().split(",");
		try {
			List<LineBean> result = lineRepo.queryRecommandLine(places, dayNum,
					scenes);
			List<RecommandLineVO> list = new Vector<RecommandLineVO>(
					result.size());
			for (LineBean bean : result) {
				if (bean == null) {
					continue;
				}
				RecommandLineVO vo = new RecommandLineVO();
				vo.setCommentIndex(bean.getCommentIndex());
				vo.setCommentNum(bean.getCommentNum());
				vo.setCommentScore(bean.getCommentScore());
				vo.setFavoriteNum(bean.getFavoriteNum());
				vo.setFeature(bean.getFeature());
				vo.setGraphid(bean.getGraphId());
				vo.setImageUrl(bean.getImageUrl());
				vo.setName(bean.getName());
				vo.setPlace(bean.getPlace());
				vo.setReason(bean.getReason());
				vo.setRecommendIndex(bean.getCommentIndex());
				vo.setRowKey(bean.getRowKey());
				vo.setShareNum(bean.getShareNum());
				vo.setTags(bean.getTags());
				vo.setThumbupNum(bean.getThumbupNum());
				list.add(vo);
			}
			return new ResponseVO<List<RecommandLineVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Fetch data fail, condition[places='%s', dayNum=%d, scenes='%s'].",
								condition.getPlaces(), condition.getDayNum(),
								condition.getScenes()), ex);
			}
			return new ResponseVO<List<RecommandLineVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	public class RecommandConditionVO {
		private String places, scenes;
		private int dayNum;

		public RecommandConditionVO() {
			super();
		}

		public String getPlaces() {
			return places;
		}

		public void setPlaces(String places) {
			this.places = places;
		}

		public String getScenes() {
			return scenes;
		}

		public void setScenes(String scenes) {
			this.scenes = scenes;
		}

		public int getDayNum() {
			return dayNum;
		}

		public void setDayNum(int dayNum) {
			this.dayNum = dayNum;
		}
	}

}
