package com.yt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yt.business.bean.RouteNotesBean;
import com.yt.common.ResponseMessage;

/**
 * 
 * @author Tony.Zhang
 * 该控制器定义了和行程随记相关的服务接口
 */
@Controller
@RequestMapping(value="/notes/")
public class RouteNotesController {
	/**
	 * 查询行程的随手记
	 * @param routeId
	 * @return
	 */
	@RequestMapping(value="/{routeId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryRouteNoteInfoes(@PathVariable(value="routeId") String routeId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	/**
	 * 保存行程随记信息
	 * @param note
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{routeId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveRouteNoteInfo(@RequestBody RouteNotesBean note , MultipartHttpServletRequest request) throws Exception{
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);
		return response;
	}
	
	/**
	 * 删除随手记内容
	 * @param noteId
	 * @return
	 */
	@RequestMapping(value="/{noteId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteRouteInfo(@PathVariable(value="noteId") String noteId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}

}
