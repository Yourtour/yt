package com.yt.rest.resource;

import com.yt.business.bean.CommentBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.CommentRepository;
import com.yt.business.repository.RouteRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.vo.CommentVO;
import com.yt.vo.route.RouteMemberVO;
import com.yt.vo.route.RouteSettingVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentRestResource extends BaseRestResource{
	private static final Log LOG = LogFactory.getLog(CommentRestResource.class);

	private @Autowired
	CommentRepository commentRepository;

	/**
	 *
	 * @param id
	 * @param nextCursor
	 * @param filter
	 * @param step
	 * @return
	 */
	@GET
	@Path("/{id}/query")
	public ResponseDataVO<List<CommentVO>> getComments(@PathParam("id") String id, @QueryParam("nextCursor") String nextCursor, @QueryParam("filter") String filter, @QueryParam("limit") String step) {
		try{
			List<CommentVO> commentvos = new ArrayList<>();
			
			List<CommentBean> commentBeans = commentRepository.getComments(Neo4jUtils.getGraphIDFromString(id), filter, Long.valueOf(nextCursor), Integer.valueOf(step));
			if(commentBeans != null){
				for(CommentBean commentBean : commentBeans){
					commentvos.add(CommentVO.transform(commentBean));
				}
			}
			return new ResponseDataVO<List<CommentVO>>(commentvos);
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseDataVO<List<CommentVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 保存点评
	 * @param id
	 * @param comment
	 * @return
	 */
	@POST
	@Path("/resource/{id}/save")
	public ResponseDataVO<Long> saveResourceComment(@PathParam("id") String id, CommentVO comment) {
		try{
			return new ResponseDataVO<Long>(0l);
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseDataVO<Long>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}

