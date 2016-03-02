package com.yt.rest.resource;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.AlongRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.error.StaticErrorEnum;
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
	private AlongRepository repository;

	/**
	 * 根据行程查询结伴信息
 	 * @param routeId
	 * @return
	 */
	@GET
	@Path("/route/{routeId}")
	public ResponseDataVO<List<AlongVO>> getRouteAlong(@PathParam("routeId") String routeId) {
		try{
			List<AlongVO> alongs = new ArrayList<>();
			Long rid = Neo4jUtils.getGraphIDFromString(routeId);

			List<AlongBean> beans = this.repository.getAlongsByRoute(rid);
			if(CollectionUtils.isNotEmpty(beans)){
				for(AlongBean along : beans){
					alongs.add(AlongVO.transform(along));
				}
			}

			return new ResponseDataVO<List<AlongVO>>(alongs);
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseDataVO<List<AlongVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
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
	public ResponseDataVO<List<AlongVO>> getPlaceAlong(@PathParam("placeId") String placeId, @QueryParam("start") Long nextCursor, @QueryParam("limit") int limit) {
		try{
			List<AlongVO> alongs = new ArrayList<>();
			Long pid = Neo4jUtils.getGraphIDFromString(placeId);

			List<AlongBean> beans = this.repository.getAlongsByPlace(pid, nextCursor, limit);
			if(CollectionUtils.isNotEmpty(beans)){
				for(AlongBean along : beans){
					alongs.add(AlongVO.transform(along));
				}
			}

			return new ResponseDataVO<List<AlongVO>>(alongs);
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseDataVO<List<AlongVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param vo
	 * @return
	 */
	@POST
	@Path("/{routeId}/{alongId}/save")
	public ResponseDataVO<Long> saveAlongInfo(@PathParam("routeId") String routeId,@PathParam("alongId") String alongId, AlongVO vo){
		try{
			AlongBean along = null;
			Long aid = Neo4jUtils.getGraphIDFromString(alongId);
			if(aid != 0){
				along = (AlongBean) this.repository.get(AlongBean.class, aid, false);
			}else{
				along = new AlongBean();
			}

			if(aid == 0) {
				AlongVO.transform(vo, along);
				RouteMainBean route = new RouteMainBean();
				route.setGraphId(Neo4jUtils.getGraphIDFromString(routeId));
				along.setRoute(route);

				UserProfileBean user = new UserProfileBean();
				user.setGraphId(Neo4jUtils.getGraphIDFromString(this.getCurrentUserId()));
				along.setPublisher(user);
			}

			this.repository.save(along, this.getCurrentUserId());
			return new ResponseDataVO<>(along.getGraphId());
		} catch (Exception ex) {
			LOG.error("Exception raised.", ex);
			return new ResponseDataVO<>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}


	/**
	 *
	 * @param alongId
	 * @return
	 */
	@GET
	@Path("/{alongId}/delete")
	public ResponseVO deleteAlongInfo(@PathParam("alongId") String alongId){
		try{
			AlongBean along = (AlongBean) this.repository.get(AlongBean.class, Neo4jUtils.getGraphIDFromString(alongId), false);;
			if(along == null){
				return new ResponseVO();
			}

			this.repository.delete(along);

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Exception raised.", ex);
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}

