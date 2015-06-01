package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.bean.RouteBean;
import com.yt.bean.RouteScheduleBean;
import com.yt.common.MockDataFactory;
import com.yt.common.ResponseMessage;
import com.yt.vo.RouteScheduleVO;
import com.yt.vo.RouteVO;

@Controller
@RequestMapping(value="/route/")
public class RouteController {
	/**
	 * 获取用户的行程信息
	 * @param userId 用户标识ID
	 * @return
	 */
	@RequestMapping(value="/user/{userId}")
	public @ResponseBody ResponseMessage queryRouteInfoes(@PathVariable("userId") String userId){
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route_user_" + userId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 保存行程概要信息
	 * @param routeId
	 * @param routeBean
	 * @return
	 */
	@RequestMapping(value="/{routeId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveRouteInfo(@PathVariable(value="routeId") String routeId, @RequestBody RouteBean routeBean){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", routeBean);
		return response;
	}
	
	/**
	 * 根据行程ID删除行程信息
	 * @param routeId
	 * @return
	 */
	@RequestMapping(value="/{routeId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteRouteInfo(@PathVariable(value="routeId") String routeId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	/**
	 * 获取指定行程的计划安排信息
	 * @param routeId 具体行程标识ID
	 * @return
	 */
	@RequestMapping(value="/schedule/{routeId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryRouteInfo(@PathVariable("routeId") String routeId){
		List<RouteScheduleVO> data =   (List<RouteScheduleVO>) MockDataFactory.getMockListData(RouteScheduleVO.class, "route_" + routeId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 保存行程安排信息
	 * @param routeId
	 * @param routeBean
	 * @return
	 */
	@RequestMapping(value="/schedule/{scheduleId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveRouteScheduleInfo(@PathVariable(value="scheduleId") String scheduleId, @RequestBody RouteScheduleBean scheduleBean){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", scheduleBean);
		return response;
	}
	
	/**
	 * 保存行程安排信息
	 * @param routeId
	 * @param routeBean
	 * @return
	 */
	@RequestMapping(value="/schedule/{scheduleId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteRouteScheduleInfo(@PathVariable(value="scheduleId") String scheduleId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);
		return response;
	}
}
