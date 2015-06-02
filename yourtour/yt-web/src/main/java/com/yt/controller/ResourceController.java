package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.ResourceVO;

/**
 * 
 * @author Tony.Zhang
 * 该控制器定义了和行程资源相关的服务接口
 */
@Controller
@RequestMapping("/resource/")
public class ResourceController {
	public ResourceController() {
	}
	
	/**
	 * 根据资源ID获取资源信息
	 * @param resId
	 * @return
	 */
	@RequestMapping(value="/{resId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage getResourceInfo(@PathVariable(value="resId") String resId)throws Exception{
		ResourceVO data = (ResourceVO) MockDataFactory.getMockData(ResourceVO.class, "resource_" + resId);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, data);
		return response;
	}
	
	/**
	 * 根据目的地和类型获取相关资源
	 * @param place
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/place/{place}/{type}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryResourceInfoes(@PathVariable(value="place") String place,@PathVariable(value="type") String type )throws Exception{
		List<ResourceVO> data = (List<ResourceVO>) MockDataFactory.getMockListData(ResourceVO.class, "resource_place_" + place + "_type_" + type);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, data);
		return response;
	}
	
	/**
	 * 获取指定资源附近的其他资源
	 * @param resId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/nearby/{resId}/{type}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage queryNearbyResourceInfoes(@PathVariable(value="resId") String resId,@PathVariable(value="type") String type )throws Exception{
		List<ResourceVO> data = (List<ResourceVO>) MockDataFactory.getMockListData(ResourceVO.class, "resource_" + resId + "_type_" + type);
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, data);
		return response;
	}
}
