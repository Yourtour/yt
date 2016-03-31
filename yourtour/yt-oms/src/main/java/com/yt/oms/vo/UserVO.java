package com.yt.oms.vo;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.business.bean.UserProfileBean;
import com.yt.business.bean.UserProfileBean.GenderType;
import com.yt.vo.BaseVO;

public class UserVO extends BaseVO {
	private static final Log LOG = LogFactory.getLog(UserVO.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd");
	private String userName; // 登录名
	private String password; // 登录密码
	private String realName; // 真实姓名
	private String nickName; // 昵称
	private GenderType gender = GenderType.NA; // 性别 F/M

	public static UserVO transform(UserProfileBean bean) {
		if (bean == null) {
			return null;
		}
		UserVO vo = new UserVO();

		transform(vo, bean);

		return vo;
	}

	public static void transform(UserVO vo, UserProfileBean bean){
		vo.fromBean(bean);
		vo.setNickName(bean.getNickName());
		vo.setRealName(bean.getRealName());
		vo.setGender(bean.getGender());
	}

	public static UserProfileBean transform(UserVO vo) {
		if (vo == null) {
			return null;
		}
		UserProfileBean bean = new UserProfileBean();
		vo.toBean(bean);
		bean.setRowKey(vo.getUserName());
		vo.setUserName(vo.getUserName());
		return bean;
	}

	public UserVO() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
