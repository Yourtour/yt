package com.yt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yt.bean.UserBean;
import com.yt.common.ResponseMessage;

/**
 * 
 * @author Tony.Zhang
 * 该控制器定义了和用户账号相关的服务接口
 */
@Controller
@RequestMapping(value="/user/")
public class UserController {

	public UserController() {
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/registeration", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage registerUserInfo(@RequestBody UserBean user, MultipartHttpServletRequest request){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	/**
	 * 注销用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/{userId}/unregisteration", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage unregisterUserInfo(@PathVariable("userId") String userId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	/**
	 * 用户信息更新
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/{userId}/updation", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage updateUserInfo(@RequestBody UserBean user){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	/**
	 * 获取指定用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage getUserInfo(@RequestBody UserBean user){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	/**
	 * 获取用户权益信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/equity/{userId}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage 	queryUserEquityInfoes(@PathVariable("userId") String userId){
		ResponseMessage response = new ResponseMessage(ResponseMessage.SUCCESS);;
		return response;
	}
	
	
}