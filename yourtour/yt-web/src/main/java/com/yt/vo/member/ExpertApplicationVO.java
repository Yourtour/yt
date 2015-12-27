package com.yt.vo.member;

import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertServiceBean;

/**
 * ��������
 */
public class ExpertApplicationVO {
	private String realName;
	private String certType;
	private String certNo;
	private String address;
	private	String tags;

	public ExpertApplicationVO() {
		super();
	}

	public static ExpertApplicationBean getBean(ExpertApplicationVO vo){
		ExpertApplicationBean bean = new ExpertApplicationBean();

		bean.setRealName(vo.getRealName());
		bean.setCertNo(vo.getCertNo());
		bean.setCertType(vo.getCertType());
		bean.setTags(vo.getTags());

		return bean;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
