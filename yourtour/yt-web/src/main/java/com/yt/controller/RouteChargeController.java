package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yt.bean.RouteBean;
import com.yt.bean.RouteNotesBean;
import com.yt.bean.RouteScheduleBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.RouteScheduleVO;

/**
 * 
 * @author Tony.Zhang
 * 该控制器定义了和行程随记相关的服务接口
 */
@Controller
@RequestMapping(value="/route/notes/")
public class RouteChargeController {
	/**
	 * 保存行程随记信息
	 * @param note
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/route/{routeId}")
	public @ResponseBody ResponseMessage saveRouteNoteInfo(@RequestBody RouteNotesBean note , MultipartHttpServletRequest request) throws Exception{
		//List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route_user_" + userId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);
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
	public @ResponseBody ResponseMessage getRouteInfo(@PathVariable("routeId") String routeId)throws Exception{
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
