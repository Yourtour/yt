package com.yt.rest.resource;

import com.yt.business.bean.RouteChargeBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.service.IRouteChargeService;
import com.yt.business.utils.Neo4jUtils;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.vo.route.RouteChargeVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private IRouteChargeService chargeService;

	/**
	 * 获取用户行程费用清单
	 * @param routeId
	 * @return
	 */
	@GET
	@Path("/query")
	public ResponseDataVO<List<RouteChargeVO>> getCharges(@PathParam("routeId") String routeId) throws Exception {
		List<RouteChargeVO> voes = new ArrayList<>();

		Long rid = Neo4jUtils.getGraphIDFromString(routeId);
		Long uid = super.getCurrentUserId();

		List<RouteChargeBean> charges = chargeService.getCharges(rid, uid);
		for (RouteChargeBean charge : charges) {
			voes.add(RouteChargeVO.transform(charge));
		}
		return new ResponseDataVO<List<RouteChargeVO>>(voes);
	}

	/**
	 * 保存行程费用
	 * @param routeId
	 * @param charge
	 * @return
	 */
	@POST
	@Path("save")
	public ResponseDataVO<Long> saveCharge(@PathParam("routeId") String routeId, RouteChargeVO charge) throws Exception{
		RouteMainBean route = new RouteMainBean();
		route.setId(Neo4jUtils.getGraphIDFromString(routeId));

		Long userId = super.getCurrentUserId();
		UserProfileBean user = new UserProfileBean();
		user.setId(userId);

		RouteChargeBean chargeBean = RouteChargeVO.transform(charge);
		chargeBean.setRoute(route);
		chargeBean.setOwner(user);
		chargeBean.setType("1");

		chargeService.saveCharge(chargeBean);

		return new ResponseDataVO<Long>(chargeBean.getId());
	}

	/**
	 * 删除行程费用
	 * @param chargeId
	 * @return
	 */
	@GET
	@Path("{chargeId}/delete")
	public ResponseVO deleteCharge(@PathParam("chargeId") String chargeId) throws Exception {
		Long cid = Neo4jUtils.getGraphIDFromString(chargeId);
		Long uid = super.getCurrentUserId();

		this.chargeService.deleteCharge(cid, uid);

		return new ResponseVO();
	}

	/**
	 * 获取用户行程费用清单
	 * @param routeId
	 * @return
	 */
	@GET
	@Path("{chargeId}/division/query")
	public ResponseDataVO<List<RouteChargeVO>> queryChargeDivision(@PathParam("routeId") String routeId,
																   @PathParam("chargeId") String chargeId) throws Exception{
		List<RouteChargeVO> voes = new ArrayList<>();

		Long cid = Neo4jUtils.getGraphIDFromString(chargeId);
		List<RouteChargeBean> charges = this.chargeService.getChargeDivisions(cid);
		for (RouteChargeBean charge : charges) {
			voes.add(RouteChargeVO.transform(charge));
		}
		return new ResponseDataVO<List<RouteChargeVO>>(voes);
	}

	/**
	 * 删除行程费用
	 * @param chargeId
	 * @return
	 */
	@POST
	@Path("{chargeId}/division/{userId}/save")
	public ResponseDataVO<Long> divideCharge(@PathParam("routeId") String routeId,
											 @PathParam("chargeId") String chargeId,
											 @PathParam("userId") String userId, RouteChargeVO vo) throws Exception{
		Long uid = this.getCurrentUserId();

		RouteMainBean route = new RouteMainBean();
		route.setId(Neo4jUtils.getGraphIDFromString(routeId));

		UserProfileBean user = new UserProfileBean();
		user.setId(Neo4jUtils.getGraphIDFromString(userId));

		RouteChargeBean chargeBean = RouteChargeVO.transform(vo);
		chargeBean.setType("2");
		chargeBean.setRoute(route);
		chargeBean.setOwner(user);

		this.chargeService.saveChargeDivisions(Neo4jUtils.getGraphIDFromString(chargeId), chargeBean);
		return new ResponseDataVO<Long>(chargeBean.getId());
	}
}
