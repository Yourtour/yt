package com.yt.vo.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertApplicationBean.IdentityCardType;
import com.yt.business.bean.ExpertApplicationBean.Status;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;

public class ExpertApplicationVO extends RegisterVO {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd");
	private IdentityCardType identityType = IdentityCardType.IDENTITY_CARD;
	private String realName, identityCode, identityCardCopyUrl;
	private String tourGuideCode, tourGuideCopyUrl, tags;
	private UserVO userProfile;
	private String requestTime, approvedTime;
	private String approvedResult;
	private Status approveState = Status.REQUESTED;

	public static ExpertApplicationVO transform(ExpertApplicationBean bean) {
		if (bean == null) {
			return null;
		}
		ExpertApplicationVO vo = new ExpertApplicationVO();
		vo.fromBean(bean);
		vo.setIdentityType(bean.getIdentityType());
		vo.setIdentityCode(bean.getIdentityCode());
		vo.setIdentityCardCopyUrl(bean.getIdentityCardCopyUrl());
		vo.setRealName(bean.getRealName());
		vo.setTags(bean.getTags());
		vo.setTourGuideCode(bean.getTourGuideCode());
		vo.setTourGuideCopyUrl(bean.getTourGuideCopyUrl());
		vo.setRequestTime(sdf.format(new Date(bean.getRequestTime())));
		if (bean.getApprovedTime() > 0) {
			vo.setApprovedTime(sdf.format(new Date(bean.getApprovedTime())));
		}
		vo.setApprovedResult(bean.getApprovedResult());
		vo.setApproveState(bean.getApproveState());
		vo.setUserProfile(UserVO.transform(bean.getExpert()));
		return vo;
	}

	public static void transform(ExpertApplicationVO vo,
			UserAccountBean accountBean, UserProfileBean profileBean,
			ExpertApplicationBean applicationBean) {
		// TODO
	}

	public ExpertApplicationVO() {
		super();
	}

	public IdentityCardType getIdentityType() {
		return identityType;
	}

	public void setIdentityType(IdentityCardType identityType) {
		this.identityType = identityType;
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

	public String getIdentityCardCopyUrl() {
		return identityCardCopyUrl;
	}

	public void setIdentityCardCopyUrl(String identityCardCopyUrl) {
		this.identityCardCopyUrl = identityCardCopyUrl;
	}

	public String getTourGuideCode() {
		return tourGuideCode;
	}

	public void setTourGuideCode(String tourGuideCode) {
		this.tourGuideCode = tourGuideCode;
	}

	public String getTourGuideCopyUrl() {
		return tourGuideCopyUrl;
	}

	public void setTourGuideCopyUrl(String tourGuideCopyUrl) {
		this.tourGuideCopyUrl = tourGuideCopyUrl;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public UserVO getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserVO userProfile) {
		this.userProfile = userProfile;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(String approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getApprovedResult() {
		return approvedResult;
	}

	public void setApprovedResult(String approvedResult) {
		this.approvedResult = approvedResult;
	}

	public Status getApproveState() {
		return approveState;
	}

	public void setApproveState(Status approveState) {
		this.approveState = approveState;
	}

}
