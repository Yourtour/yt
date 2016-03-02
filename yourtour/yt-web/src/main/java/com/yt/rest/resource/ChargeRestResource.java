package com.yt.rest.resource;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.RouteChargeRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.vo.route.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("app/route/{routeId}/charge")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChargeRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(ChargeRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private RouteChargeRepository chargeRepository;

	/**
	 * 获取用户行程费用清单
	 * @param routeId
	 * @return
	 */
	@GET
	@Path("/query")
	public ResponseDataVO<List<RouteChargeVO>> getCharges(@PathParam("routeId") String routeId) {
		try {
			List<RouteChargeVO> voes = new ArrayList<>();

			Long rid = Neo4jUtils.getGraphIDFromString(routeId);
			Long uid = Neo4jUtils.getGraphIDFromString(super.getCurrentUserId());

			List<RouteChargeBean> charges = chargeRepository.getCharges(rid, uid);
			for (RouteChargeBean charge : charges) {
				voes.add(RouteChargeVO.transform(charge));
			}
			return new ResponseDataVO<List<RouteChargeVO>>(voes);
		} catch (Exception ex) {
			LOG.error("Querying route charge failed.", ex);
			return new ResponseDataVO<List<RouteChargeVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 保存行程费用
	 * @param routeId
	 * @param charge
	 * @return
	 */
	@POST
	@Path("save")
	public ResponseDataVO<Long> saveCharge(@PathParam("routeId") String routeId, RouteChargeVO charge) {
		try {
			RouteMainBean route = new RouteMainBean();
			route.setGraphId(Neo4jUtils.getGraphIDFromString(routeId));

			String userId = super.getCurrentUserId();
			UserProfileBean user = new UserProfileBean();
			user.setGraphId(Neo4jUtils.getGraphIDFromString(userId));

			RouteChargeBean chargeBean = RouteChargeVO.transform(charge);
			chargeBean.setRoute(route);
			chargeBean.setOwner(user);
			chargeBean.setType("1");

			chargeRepository.save(chargeBean, true, userId);

			return new ResponseDataVO<Long>(chargeBean.getGraphId());
		} catch (Exception ex) {
			LOG.error("Save route charge failed.",ex);
			return new ResponseDataVO<Long>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 删除行程费用
	 * @param chargeId
	 * @return
	 */
	@GET
	@Path("{chargeId}/delete")
	public ResponseVO deleteCharge(@PathParam("chargeId") String chargeId) {
		try {
			Long cid = Neo4jUtils.getGraphIDFromString(chargeId);
			Long uid = Neo4jUtils.getGraphIDFromString(super.getCurrentUserId());

			chargeRepository.deleteCharge(cid, uid);

			return new ResponseVO();
		} catch (Exception ex) {
			LOG.error("Save charge for route failed.",ex);
			return new ResponseVO(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 获取用户行程费用清单
	 * @param routeId
	 * @return
	 */
	@GET
	@Path("{chargeId}/division/query")
	public ResponseDataVO<List<RouteChargeVO>> queryChargeDivision(@PathParam("routeId") String routeId, @PathParam("chargeId") String chargeId){
		try {
			List<RouteChargeVO> voes = new ArrayList<>();

			Long cid = Neo4jUtils.getGraphIDFromString(chargeId);
			List<RouteChargeBean> charges = chargeRepository.getChargeDivisions(cid);
			for (RouteChargeBean charge : charges) {
				voes.add(RouteChargeVO.transform(charge));
			}
			return new ResponseDataVO<List<RouteChargeVO>>(voes);
		} catch (Exception ex) {
			LOG.error("Querying route charge failed.", ex);
			return new ResponseDataVO<List<RouteChargeVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 删除行程费用
	 * @param chargeId
	 * @return
	 */
	@POST
	@Path("{chargeId}/division/{userId}/save")
	public ResponseDataVO<Long> divideCharge(@PathParam("routeId") String routeId, @PathParam("chargeId") String chargeId, @PathParam("userId") String userId, RouteChargeVO vo) {
		try {
			String uid = this.getCurrentUserId();

			RouteChargeBean master = (RouteChargeBean) chargeRepository.get(RouteChargeBean.class, Neo4jUtils.getGraphIDFromString(chargeId));
			if(master == null){
					return new ResponseDataVO<Long>(
							StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			master.setPayment(master.getPayment() - vo.getPayment());
			chargeRepository.save(master, uid);

			RouteMainBean route = new RouteMainBean();
			route.setGraphId(Neo4jUtils.getGraphIDFromString(routeId));

			UserProfileBean user = new UserProfileBean();
			user.setGraphId(Neo4jUtils.getGraphIDFromString(userId));

			RouteChargeBean chargeBean = RouteChargeVO.transform(vo);
			chargeBean.setType("2");
			chargeBean.setRoute(route);
			chargeBean.setOwner(user);

			chargeRepository.save(chargeBean, true, this.getCurrentUserId());

			chargeRepository.createRelation(master, chargeBean, Constants.RELATION_TYPE_DIVIDED, Direction.OUTGOING);
			return new ResponseDataVO<Long>(chargeBean.getGraphId());
		} catch (Exception ex) {
			LOG.error("Save route charge failed.",ex);
			return new ResponseDataVO<Long>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
