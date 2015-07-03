package com.yt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yt.business.bean.CommentBean;
import com.yt.common.ResponseMessage;
import com.yt.mocker.MockDataFactory;
import com.yt.vo.RouteVO;

/**
 * 
 * @author Tony.Zhang
 * 本控制器定义和实现了与用户评论相关的服务接口
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	public CommentController() {
	}
	
	/**
	 * 查询评论信息
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/comment/{contentId}")
	public @ResponseBody ResponseMessage queryCommentInfoes(@PathVariable("contentId") String contentId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 保存评论信息
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/comment/content/{contentId}", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveCommentInfo(@RequestBody CommentBean comment) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}
	
	/**
	 * 删除评论信息
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/comment/{commentId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseMessage deleteRouteLiveCommentInfo(@PathVariable("commentId") String commentId) throws Exception{
		List<RouteVO> data = (List<RouteVO>) MockDataFactory.getMockListData(RouteVO.class, "route/subject/query");
		
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS, "", data);
		return response;
	}

}
