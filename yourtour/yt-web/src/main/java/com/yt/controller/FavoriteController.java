package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.bean.FavoriteBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.RouteVO;

/**
 * 
 * @author Tony.Zhang
 * 本控制器定义和实现了与用户关注相关的服务接口
 */
@Controller
@RequestMapping("/favorite")
public class FavoriteController {

	public FavoriteController() {
	}
	
	/**
	 * 查询关注信息
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/{userId}")
	public @ResponseBody ResponseMessage queryFavoriteInfoes(@PathVariable("userId") String userId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 保存关注信息
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{targetId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveFavoriteInfo(@RequestBody FavoriteBean favorite) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 删除关注信息
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{favoriteId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteFavoriteInfo(@PathVariable("favoriteId") String favoriteId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
}