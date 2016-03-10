package com.yt.vo.route;

import com.yt.business.bean.*;
import com.yt.core.utils.CollectionUtils;
import com.yt.vo.BaseVO;

import java.util.ArrayList;
import java.util.List;

public class RouteChargeVO extends BaseVO {
	private String  name;
	private String 	item; // 行程名称
	private String	type;
	private double 	amount;
	private double  payment;
	private int		percent;
	private Long 	chargeDate;
	private String 	memo;
	private String 	imageUrl;
	private Long 	userId;
	private String	nickName;
	private String  userImageUrl;

	private List<RouteChargeVO> divisions = null;

	public static RouteChargeVO transform(RouteChargeBean bean) {
		if (bean == null) {
			return null;
		}

		RouteChargeVO vo = new RouteChargeVO();
		vo.setId(bean.getId());
		vo.setName(bean.getName());
		vo.setItem(bean.getItem());
		vo.setAmount(bean.getAmount());
		vo.setPayment(bean.getPayment());
		vo.setPercent(bean.getPercent());
		vo.setMemo(bean.getMemo());
		vo.setImageUrl(bean.getImageUrl());
		vo.setChargeDate(bean.getChargeDate());
		vo.setType(bean.getType());

		UserProfileBean user = bean.getOwner();
		if(user != null) {
			vo.setUserId(user.getId());
			vo.setNickName(user.getNickName());
			vo.setUserImageUrl(user.getImageUrl());
		}

		List<RouteChargeBean> divisions = bean.getDivision();
		if(CollectionUtils.isNotEmpty(divisions)){
			List<RouteChargeVO> divisionvo = new ArrayList<>();

			for(RouteChargeBean division : divisions){
				divisionvo.add(RouteChargeVO.transform(division));
			}

			vo.setDivisions(divisionvo);
		}
		return vo;
	}

	public static RouteChargeBean transform(RouteChargeVO vo) {
		if (vo == null) {
			return null;
		}

		RouteChargeBean bean = new RouteChargeBean();
		vo.toBean(bean);

		bean.setImageUrl(vo.getImageUrl());
		bean.setName(vo.getName());
		bean.setChargeDate(vo.getChargeDate());
		bean.setItem(vo.getItem());
		bean.setAmount(vo.getAmount());
		bean.setType(vo.getType());
		bean.setMemo(vo.getMemo());
		bean.setPercent(vo.getPercent());

		return bean;
	}

	public RouteChargeVO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getType() {
		return type == null?"1":"2";
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPayment() {
		return payment == 0 ? amount : payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public Long getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Long chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}

	public List<RouteChargeVO> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<RouteChargeVO> divisions) {
		this.divisions = divisions;
	}
}
