package com.yt.vo.member;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.business.bean.UserProfileBean;

public class UserProfileVO extends RegisterProfileVO {
	private static final Log LOG = LogFactory.getLog(UserProfileVO.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd");
	private String birthday, slogan, residence, nativePlace, email;

	public static void transform(UserProfileVO vo, UserProfileBean profile) {
		profile.setNickName(vo.getNickName());
		profile.setTags(vo.getTags());
		profile.setGender(vo.getGener());
		profile.setMobileNo(vo.getMobile());
		profile.setEmail(vo.getEmail());
		profile.setSlogan(vo.getSlogan());
		profile.setResidence(vo.getResidence());
		profile.setNativePlace(vo.getNativePlace());
		try {
			profile.setBirthday(sdf.parse(vo.getBirthday()).getTime());
		} catch (Exception ex) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Parse the Stirng to Date fail: %s.",
						vo.getBirthday()));
			}
		}
	}

	public UserProfileVO() {
		super();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
