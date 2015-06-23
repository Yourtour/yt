package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.bean.RouteBean;
import com.yt.bean.RouteScheduleBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.LineVO;
import com.yt.vo.RouteScheduleVO;
import com.yt.vo.RouteVO;

/**
 * 
 * @author Tony.Zhang
 * 该控制器定义了和行程相关的服务接口
 */
@Controller
@RequestMapping(value="/route")
public class RouteController {
	/**
	 * 获取用户的行程信息
	 * @param userId 用户标识ID
	 * @return
	 */
	@RequestMapping(value="/user/Query")
	public @ResponseBody ResponseMessage queryRouteInfoesByUser(@RequestHeader("user_id") String userId) throws Exception{
		System.out.println(userId);
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/user/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据目的地代码查询行程信息
	 * @param place
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/place/{place}")
	public @ResponseBody ResponseMessage queryRouteInfoesByPlace(@PathVariable("place") String place) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据资源查询相关行程
	 * @param resId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resource/{resId}")
	public @ResponseBody ResponseMessage queryRouteInfoesByResource(@PathVariable("resId") String resId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/resource/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据用户输入的搜索关键字（线路名称，景点名称等）进行检索
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage searchRouteInfoes(@RequestParam("name") String name) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据主题查询行程 
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/subject/{subject}")
	public @ResponseBody ResponseMessage queryRouteInfoesBySubject(@PathVariable("subject") String subject) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据线路查询行程 
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/line/{lineId}")
	public @ResponseBody ResponseMessage queryRouteInfoesByLine(@PathVariable("lineId") String lineId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 查找和某行程相近的行程
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{routeId}/relationship")
	public @ResponseBody ResponseMessage queryRelatedRouteInfoes(@PathVariable("routeId") String routeId) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "route/place/query");
		
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
