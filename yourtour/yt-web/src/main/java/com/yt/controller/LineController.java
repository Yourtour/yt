package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.bean.LineBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.LineVO;
import com.yt.vo.RouteVO;
import com.yt.vo.UserVO;

/**
 * 
 * @author Tony.Zhang
 * 本控制器定义了和线路相关的服务接口定义和实现
 */
@Controller
@RequestMapping("/line")
public class LineController {
	public LineController() {
	}

	/**
	 * 根据目的地代码查询线路信息
	 * @param place
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/place/{place}")
	public @ResponseBody ResponseMessage queryLineInfoesByPlace(@PathVariable("place") String place) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 根据资源找相关的线路信息
	 * @param place
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resource/{resId}")
	public @ResponseBody ResponseMessage queryLineInfoesByResource(@PathVariable("resId") String resId) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "route/place/query");
		
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
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
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
	public @ResponseBody ResponseMessage searchLineInfoes(@RequestParam("name") String name) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 查找和某条线路相近的线路
	 * @param line
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}/relationship")
	public @ResponseBody ResponseMessage queryRelatedLineInfoes(@PathVariable("lineId") String line) throws Exception{
		List<LineVO> data = (List<LineVO>) MockDataFactory.getMockListData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 获取指定线路信息
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryLineInfo(@PathVariable("lineId") String lineId) throws Exception{
		LineVO data = (LineVO) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 获取游玩过指定线路的用户
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}/members", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryLineMemberInfo(@PathVariable("lineId") String lineId) throws Exception{
		List<UserVO> data = (List<UserVO>) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 获取线路推荐行程信息
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}/routes", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryLineRouteInfo(@PathVariable("lineId") String lineId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 更新线路信息
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save/{lineId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage updateLineInfo(@PathVariable("lineId") String lineId, @RequestBody LineBean line) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 新增线路信息
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage insertLineInfo(@RequestBody LineBean line) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 删除线路信息
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteLineInfo(@PathVariable("lineId") String lineId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 增加线路推荐行程信息
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}/route/{routeId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage insertLineRouteInfo(@PathVariable("lineId") String lineId,  @PathVariable("routeId") String routeId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 取消线路推荐行程信息
	 * @param lineId
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{lineId}/route/{routeId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteLineRouteInfo(@PathVariable("lineId") String lineId,  @PathVariable("routeId") String routeId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockData(LineVO.class, "route/place/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
}
