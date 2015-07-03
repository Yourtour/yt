package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.business.bean.RouteMemberBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.RouteMemberVO;

/**
 * 
 * @author Tony.Zhang
 * 该控制器定义了和行程成员相关的服务接口
 */
@Controller
@RequestMapping("/member")
public class RouteMemberController {
	public RouteMemberController() {
	}
	
	/**
	 * 根据行程ID获取参与该行程的所有用户
	 * @param routeId
	 * @return
	 */
	@RequestMapping(value="/route/{routeId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryMemberInfoes(@PathVariable(value="routeId") String routeId)throws Exception{
		List<RouteMemberVO> data = (List<RouteMemberVO>) MockDataFactory.getMockData(RouteMemberVO.class, "member_route_" + routeId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, data);
		return response;
	}
	
	/**
	 * 添加行程成员
	 * @param member
	 * @return
	 */
	@RequestMapping(value="/{routeId}/{member}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveMemberInfo(@RequestBody RouteMemberBean member){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "");
		return response;
	}
	
	/**
	 * 删除行程成员
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value="/{routeId}/{memberId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteMemberInfo(@PathVariable(value="memberId") String memberId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);
		return response;
	}
	
}
