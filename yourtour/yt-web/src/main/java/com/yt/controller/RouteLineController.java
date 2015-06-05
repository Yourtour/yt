package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.bean.LineBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.LineVO;
import com.yt.vo.RouteScheduleVO;

/**
 * 
 * @author Tony.Zhang
 * 本控制器定义和实现了和行程线路相关的服务接口
 */
@Controller
@RequestMapping("/line")
public class RouteLineController {
	public RouteLineController() {
	}
	
	
	/**
	 * 根据目的地查询仙侣信息
	 * @param place
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/place/{place}")
	public @ResponseBody ResponseMessage queryLineInfoesByPlace(@PathVariable("place") String place) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "/line/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据资源查询相关线路
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resource/{resource}")
	public @ResponseBody ResponseMessage queryLineInfoesByResource(@PathVariable("resource") String resource) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "/line/resource/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据主题查询线路 
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/subject/{subject}")
	public @ResponseBody ResponseMessage queryLineInfoesBySubject(@PathVariable("subject") String subject) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 保存线路信息
	 * @param routeId
	 * @param routeBean
	 * @return
	 */
	@RequestMapping(value="/{lineId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveLineInfo(@PathVariable(value="lineId") String lineId, @RequestBody LineBean lineBean){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", lineBean);
		return response;
	}
	
	/**
	 * 根据线路ID删除线路信息
	 * @param routeId
	 * @return
	 */
	@RequestMapping(value="/{lineId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteLineInfo(@PathVariable(value="lineId") String lineId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	/**
	 * 获取指定的线路
	 * @param routeId 具体行程标识ID
	 * @return
	 */
	@RequestMapping(value="/{lineId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage getLineInfo(@PathVariable("lineId") String lineId)throws Exception{
		List<RouteScheduleVO> data =   (List<RouteScheduleVO>) MockDataFactory.getMockListData(RouteScheduleVO.class, "line_" + lineId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 为指定线路添加参考行程
	 * @param routeId 具体行程标识ID
	 * @return
	 */
	@RequestMapping(value="/{lineId}/route/{routeId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage addRouteInfo(@PathVariable("lineId") String lineId, @PathVariable("routeID") String routeId)throws Exception{
		List<RouteScheduleVO> data =   (List<RouteScheduleVO>) MockDataFactory.getMockListData(RouteScheduleVO.class, "line_" + lineId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 取消线路的推荐行程
	 * @param lineId
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}/route/{routeId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage detachRouteInfo(@PathVariable("lineId") String lineId, @PathVariable("routeID") String routeId)throws Exception{
		List<RouteScheduleVO> data =   (List<RouteScheduleVO>) MockDataFactory.getMockListData(RouteScheduleVO.class, "line_" + lineId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
}
