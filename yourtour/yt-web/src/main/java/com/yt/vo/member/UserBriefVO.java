package com.yt.vo.member;

import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants.GenderType;
import com.yt.vo.BaseVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;

public class UserBriefVO extends BaseVO {
	private static final Log LOG = LogFactory.getLog(UserBriefVO.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd");
	private String userName; // 登录名
	private String realName; // 真实姓名
	private String nickName; // 昵称
	private GenderType gender = GenderType.NA; // 性别 F/M
	private String imageUrl; // 头像

	public static UserBriefVO transform(UserProfileBean bean) {
		if (bean == null) {
			return null;
		}
		UserBriefVO vo = new UserBriefVO();
		vo.fromBean(bean);
		vo.setImageUrl(bean.getImageUrl());
		vo.setNickName(bean.getNickName());
		vo.setRealName(bean.getRealName());
		vo.setGender(bean.getGender());
		return vo;
	}

	public static UserProfileBean transform(UserBriefVO vo) {
		if (vo == null) {
			return null;
		}
		UserProfileBean bean = new UserProfileBean();
		vo.toBean(bean);
		bean.setRowKey(vo.getUserName());
		vo.setUserName(vo.getUserName());
		bean.setImageUrl(vo.getImageUrl());
		bean.setNickName(vo.getNickName());
		bean.setRealName(vo.getRealName());
		bean.setGender(vo.getGender());
		return bean;
	}

	public UserBriefVO() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
