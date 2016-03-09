package com.yt.rest.resource;

import com.yt.business.bean.RouteMemberBean;
import com.yt.business.common.Constants;
import com.yt.business.service.IRouteMemberService;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.SessionUtils;
import com.yt.vo.route.RouteMemberVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/app/route/{routeId}/member")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(MemberRestResource.class);

	@Autowired
	private IRouteMemberService memberService;

	/**
	 * 获取行程伙伴
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/query")
	public ResponseDataVO<List<RouteMemberVO>> getRouteMembers(@PathParam("routeId") Long routeId) throws Exception{
		List<RouteMemberVO> voes = new ArrayList<>();

		List<RouteMemberBean> members = memberService.getMembers(routeId);
		if(members != null){
			int index = 0;
			for(RouteMemberBean member : members){
				RouteMemberVO vo = RouteMemberVO.transform(member);
				if(index == 0){
					vo.setRole(Constants.GroupRole.LEADER.code.toLowerCase());
				}

				if(index == 1){
					vo.setRole(Constants.GroupRole.EXPERT.code.toLowerCase());
				}

				voes.add(vo);
				index++;
			}
		}
		return new ResponseDataVO<List<RouteMemberVO>>(voes);
	}

	/**
	 * 添加伙伴
	 * @param routeId
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/save")
	public ResponseDataVO<RouteMemberVO> addRouteMember(@PathParam("routeId") Long routeId, RouteMemberVO member) throws Exception{
		RouteMemberBean memberBean = RouteMemberVO.transform(member);

		this.memberService.saveMember(memberBean, SessionUtils.getCurrentLoginUser());

		return new ResponseDataVO<RouteMemberVO>(RouteMemberVO.transform(memberBean));
	}

	/**
	 *
	 * @param rid
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{uid}/delete")
	public ResponseVO deleteRouteMember(@PathParam("routeId") Long rid, @PathParam("uid") Long uid) throws Exception{
		this.memberService.deleteMember(rid, uid, SessionUtils.getCurrentLoginUser());

		return new ResponseVO();
	}
}