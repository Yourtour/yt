package com.yt.rest.resource;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import org.springframework.stereotype.Component;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.UserBean;
import com.yt.business.repository.PlaceRespository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.maintain.basedata.DivisionVO;

@Component
@Path("divisions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DivisionRestResource {
	private static final Log LOG = LogFactory
			.getLog(DivisionRestResource.class);
	private List<DivisionVO> roots = null;

	@Autowired
	private PlaceRespository placeRepository;

	public DivisionRestResource() {
		super();
		roots = new Vector<DivisionVO>();
		for (int index = 1; index < 10; index++) {
			DivisionVO item = new DivisionVO();
			item.setCode("root " + index);
			item.setText("root text " + index);
			item.setId((long) (index));
			item.setExpanded(false);
			item.setLeaf(true);
			roots.add(item);
		}
		roots.get(3).setLeaf(false);
	}

	@Path("load/{id}.json")
	@GET
	public ResponseDataVO<List<DivisionVO>> getDivision(
			@PathParam("id") String key) {
		System.out.println("............load/" + key + ".json");
		if ("4".equals(key)) {
			DivisionVO d3 = roots.get(3);
			for (int index = 11; index < 20; index++) {
				DivisionVO item = new DivisionVO();
				item.setCode("child " + index);
				item.setText("child text " + index);
				item.setId((long) (index));
				item.setExpanded(true);
				item.setLeaf(true);
				d3.getChildren().add(item);
			}
			return new ResponseDataVO<List<DivisionVO>>(d3.getChildren());
		} else {
			return new ResponseDataVO<List<DivisionVO>>();
		}
	}

	@Path("load/root.json")
	@GET
	public ResponseDataVO<List<DivisionVO>> getAllDivisions() {
		System.out.println("load/root.json");
		return new ResponseDataVO<List<DivisionVO>>(roots);
	}

	@POST
	@Path("save.json")
	public ResponseVO saveByAdd(DivisionVO vo,
			@Context HttpServletRequest request) {
		return save(null, vo, WebUtils.getCurrentLoginUser(request));
	}

	@POST
	@Path("save/{id}.json")
	public ResponseVO saveByUpdate(@PathParam("id") String id, DivisionVO vo,
			@Context HttpServletRequest request) {
		return save(id, vo, WebUtils.getCurrentLoginUser(request));
	}

	private ResponseVO save(String id, DivisionVO vo, String operator) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The DivisionVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			//PlaceBean bean = DivisionVO.transform(vo);
			//placeRepository.save(bean, operator, true);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save PlaceBean['%s'] success.",
						vo.getId()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the UserBean[id='%s'] fail.",
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
			PlaceBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				//bean = placeRepository.getPlaceByGraphId(graphId);
				id = bean.getRowKey();
			}
			//placeRepository.delete(UserBean.class, id);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Delete PlaceBean['%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch PlaceBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
