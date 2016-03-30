package com.yt.vo.member;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.vo.SocialVO;

public class ExpertServiceVO extends SocialVO {
	private String title;
	private String fee;
	private String feeIncluding;
	private String feeExcluding;
	private String withdraw;
	private String content;
	private String category;
	private String imageUrl;
	private UserVO user;

	public ExpertServiceVO() {
	}

	public static ExpertServiceBean transform(ExpertServiceVO vo) {
		ExpertServiceBean bean = new ExpertServiceBean();
		vo.toBean(bean);

		bean.setTitle(vo.getTitle());
		bean.setFee(vo.getFee());
		bean.setContent(vo.getContent());
		bean.setWithdraw(vo.getWithdraw());
		bean.setFeeExcluding(vo.getFeeExcluding());
		bean.setFeeIncluding(vo.getFeeIncluding());

		return bean;
	}

	public static ExpertServiceVO transform(ExpertServiceBean bean) {
		ExpertServiceVO valueObject = new ExpertServiceVO();
		valueObject.fromBean(bean);
		valueObject.setFee(bean.getFee());
		valueObject.setTitle(bean.getTitle());
		valueObject.setContent(bean.getContent());
		valueObject.setWithdraw(bean.getWithdraw());
		valueObject.setFeeExcluding(bean.getFeeExcluding());
		valueObject.setFeeIncluding(bean.getFeeIncluding());
		valueObject.setImageUrl(bean.getImageUrl());

		if (bean.getUser() != null) {
			valueObject.setUser(UserVO.transform(bean.getUser()));
		}
		return valueObject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFeeIncluding() {
		return feeIncluding;
	}

	public void setFeeIncluding(String feeIncluding) {
		this.feeIncluding = feeIncluding;
	}

	public String getFeeExcluding() {
		return feeExcluding;
	}

	public void setFeeExcluding(String feeExcluding) {
		this.feeExcluding = feeExcluding;
	}

	public String getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
}
