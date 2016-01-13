package com.yt.vo.member;

import com.yt.business.bean.*;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.DateUtils;
import com.yt.vo.CommentVO;
import com.yt.vo.route.RouteVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpertVO extends UserVO{
	private static final long serialVersionUID = 7565498288049730405L;

	private String 	places;

	private List<ExpertServiceVO> services = new ArrayList<>();  //提供服务
	private List<CommentVO> comments = new ArrayList<>(); //用户点评
	private List<RouteVO> routes = new ArrayList<>(); //以往服务行程

	public static ExpertVO transform(ExpertBean bean){
		ExpertVO expert = new ExpertVO();

		UserProfileBean profile = bean.getProfile();
		expert.setId(bean.getGraphId());
		expert.setNickName(profile.getNickName());
		expert.setImageUrl(profile.getImageUrl());
		expert.setIdentity(profile.getIdentity());
		expert.setAge(profile.getAge());
		expert.setIdAuthenticate(profile.getIdAuthenticate());
		expert.setMobileAuthenticate(profile.getMobileAuthenticate());
		expert.setSnsAuthenticate(profile.getSnsAuthenticate());
		expert.setMemo(bean.getMemo());
		expert.setTags(bean.getTags());

		List<ExpertServiceBean> services = bean.getServices();
		if(CollectionUtils.isNotEmpty(services)){
			for(ExpertServiceBean service : services){
				expert.getServices().add(ExpertServiceVO.transform(service));
			}
		}

		List<CommentBean> comments = bean.getComments();
		if(CollectionUtils.isNotEmpty(comments)){
			for(CommentBean comment : comments){
				expert.getComments().add(CommentVO.transform(comment));
			}
		}

		List<RouteMainBean> routes = bean.getRecommendRoutes();
		if(CollectionUtils.isNotEmpty(routes)){
			for(RouteMainBean route : routes){
				expert.getRoutes().add(RouteVO.transform(route));
			}
		}

		return expert;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public List<ExpertServiceVO> getServices() {
		return services;
	}

	public void setServices(List<ExpertServiceVO> services) {
		this.services = services;
	}

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}

	public List<RouteVO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteVO> routes) {
		this.routes = routes;
	}
}
