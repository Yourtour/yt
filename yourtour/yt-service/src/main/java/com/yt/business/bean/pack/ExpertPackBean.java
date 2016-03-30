package com.yt.business.bean.pack;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertContentApplicationBean;
import com.yt.business.bean.ExpertRouteBean;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.UserProfileBean;

/**
 * 达人包装实体，是数据库实体的组合
 * 
 * @author John.Peng
 * 
 */
public class ExpertPackBean implements Serializable {
	private static final long serialVersionUID = 1835683925891809849L;
	private UserProfileBean userProfile;
	private ExpertApplicationBean application;
	private List<ExpertContentApplicationBean> contentApplications;
	private List<ExpertServiceBean> services;
	private List<ExpertRouteBean> routes;

	public ExpertPackBean() {
		super();
		contentApplications = new Vector<ExpertContentApplicationBean>();
		services = new Vector<ExpertServiceBean>();
		routes = new Vector<ExpertRouteBean>();
	}

	public UserProfileBean getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileBean userProfile) {
		this.userProfile = userProfile;
	}

	public ExpertApplicationBean getApplication() {
		return application;
	}

	public void setApplication(ExpertApplicationBean application) {
		this.application = application;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ExpertContentApplicationBean> getContentApplications() {
		return contentApplications;
	}

	public List<ExpertServiceBean> getServices() {
		return services;
	}

	public List<ExpertRouteBean> getRoutes() {
		return routes;
	}

}
