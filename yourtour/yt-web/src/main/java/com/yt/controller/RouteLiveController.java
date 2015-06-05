package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.bean.CommentBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.RouteVO;

/**
 * 
 * @author Tony.Zhang
 * 该控制类实现个定义了与行程直播相关的服务接口
 */

@Controller
@RequestMapping("/route/live")
public class RouteLiveController {
	public RouteLiveController() {
	}
	
	/**
	 * 查询正在直播的行程
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/now")
	public @ResponseBody ResponseMessage queryRouteLiveInfoes() throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 查询直播历史
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/history")
	public @ResponseBody ResponseMessage queryRouteLiveHistoryInfoes() throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 查询行程直播间信息
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/room/{routeId}")
	public @ResponseBody ResponseMessage queryRouteLiveRoomInfoes4Id(@PathVariable("routeId") String routeId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据名称查询行程直播间信息
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/room/name/{name}")
	public @ResponseBody ResponseMessage queryRouteLiveRoomInfoes4Name(@PathVariable("name") String name) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 查询直播内容
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/content/room/{roomId}")
	public @ResponseBody ResponseMessage queryRouteLiveContentInfoes(@PathVariable("roomId") String roomId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
}
