package com.yt.rest.resource;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.service.IAlongService;
import com.yt.business.utils.Neo4jUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.vo.AlongVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("app/along/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlongRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(AlongRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private IAlongService alongService;

	/**
	 * 根据行程查询结伴信息
 	 * @param routeId
	 * @return
	 */
	@GET
	@Path("/route/{routeId}")
	public ResponseDataVO<List<AlongVO>> getRouteAlong(@PathParam("routeId") String routeId) throws Exception {
		List<AlongVO> alongs = new ArrayList<>();
		Long rid = Neo4jUtils.getGraphIDFromString(routeId);

		List<AlongBean> beans = this.alongService.getAlongsByRoute(rid);
		if(CollectionUtils.isNotEmpty(beans)){
			for(AlongBean along : beans){
				alongs.add(AlongVO.transform(along));
			}
		}

		return new ResponseDataVO<List<AlongVO>>(alongs);
	}

	/**
	 * 根据目的地查询结伴信息
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 */
	@GET
	@Path("/place/{placeId}")
	public ResponseDataVO<List<AlongVO>> getPlaceAlong(@PathParam("placeId") String placeId,
													   @QueryParam("start") Long nextCursor,
													   @QueryParam("limit") int limit) throws Exception{
		List<AlongVO> alongs = new ArrayList<>();
		Long pid = Neo4jUtils.getGraphIDFromString(placeId);

		List<AlongBean> beans = this.alongService.getAlongsByPlace(pid, nextCursor, limit);
		if(CollectionUtils.isNotEmpty(beans)){
			for(AlongBean along : beans){
				alongs.add(AlongVO.transform(along));
			}
		}

		return new ResponseDataVO<List<AlongVO>>(alongs);
	}

	/**
	 *
	 * @param vo
	 * @return
	 */
	@POST
	@Path("/{routeId}/{alongId}/save")
	public ResponseDataVO<Long> saveAlongInfo(@PathParam("routeId") String routeId,@PathParam("alongId") String alongId, AlongVO vo) throws Exception{
		AlongBean along = null;
		Long aid = Neo4jUtils.getGraphIDFromString(alongId);
		if(aid != 0){
			along = this.alongService.getAlongInfo(aid);
		}else{
			along = new AlongBean();
		}

		if(aid == 0) {
			AlongVO.transform(vo, along);
			RouteMainBean route = new RouteMainBean();
			route.setId(Neo4jUtils.getGraphIDFromString(routeId));
			along.setRoute(route);

			UserProfileBean user = new UserProfileBean();
			user.setId(this.getCurrentUserId());
			along.setPublisher(user);
		}

		this.alongService.saveAlongInfo(along);
		return new ResponseDataVO<>(along.getId());
	}


	/**
	 *
	 * @param alongId
	 * @return
	 */
	@GET
	@Path("/{alongId}/delete")
	public ResponseVO deleteAlongInfo(@PathParam("alongId") String alongId) throws Exception{
		this.alongService.deleteAlongInfo(Neo4jUtils.getGraphIDFromString(alongId), this.getCurrentUserId());

		return new ResponseVO();
	}
}

