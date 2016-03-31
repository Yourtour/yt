package com.yt.oms.vo.expert;

import com.yt.business.bean.ExpertApplicationBean.Status;
import com.yt.business.bean.pack.ExpertPackBean;

public class ExpertVO {

	private Long id;
	private String mobileNo, realName, identityCode;
	private Status status;
	private int contentApplicationNum, routeNum, serviceNum;

	public static ExpertVO transform(ExpertPackBean bean) {
		if (bean == null) {
			return null;
		}
		bean.getUserProfile();
		bean.getApplication();
		bean.getContentApplications();
		bean.getRoutes();
		bean.getServices();
		ExpertVO vo = new ExpertVO();
		if (bean.getUserProfile() != null) {
			vo.setId(bean.getUserProfile().getId());
			vo.setMobileNo(bean.getUserProfile().getMobileNo());
			vo.setRealName(bean.getUserProfile().getRealName());
		}
		if (bean.getApplication() != null) {
			vo.setIdentityCode(bean.getApplication().getIdentityCode());
			vo.setStatus(bean.getApplication().getApproveState());
		}
		vo.setContentApplicationNum(bean.getContentApplications() == null ? 0
				: bean.getContentApplications().size());
		vo.setRouteNum(bean.getRoutes() == null ? 0 : bean.getRoutes().size());
		vo.setServiceNum(bean.getServices() == null ? 0 : bean.getServices()
				.size());
		return vo;
	}

	public ExpertVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getContentApplicationNum() {
		return contentApplicationNum;
	}

	public void setContentApplicationNum(int contentApplicationNum) {
		this.contentApplicationNum = contentApplicationNum;
	}

	public int getRouteNum() {
		return routeNum;
	}

	public void setRouteNum(int routeNum) {
		this.routeNum = routeNum;
	}

	public int getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(int serviceNum) {
		this.serviceNum = serviceNum;
	}

}
