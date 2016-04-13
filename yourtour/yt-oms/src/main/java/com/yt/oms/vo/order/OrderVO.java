package com.yt.oms.vo.order;

import com.yt.business.bean.OrderBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.UserVO;
import com.yt.vo.BaseVO;

import java.util.Map;

public class OrderVO extends BaseVO {
	private Long   routeId;
	private String title; // 订单名称
	private String type; // 订单分类，来自于字典表
	private String fee;
	private String currency;
	private Map<String, Object> specialties; //每类订单扩展属性
	private Long 	fromTime;  //预计服务开始时间
	private Long 	endTime;   //预计服务结束时间
	private	Long	usedTime;   //实际使用结束时间
	private Long	payTime;   //支付时间
	private String	memo;   //支付时间
	private OrderBean.Status status;	   //订单状态

	private UserVO user;

	private UserVO expert;

	public static OrderVO transform(OrderBean bean) throws Exception{
		if (bean == null) {
			return null;
		}

		OrderVO vo = new OrderVO();
		vo.fromBean(bean);

		vo.setRouteId(bean.getRouteId());
		vo.setTitle(bean.getTitle());
		vo.setType(bean.getType());
		vo.setFromTime(bean.getFromTime());
		vo.setEndTime(bean.getEndTime());
		vo.setUsedTime(bean.getUsedTime());
		vo.setPayTime(bean.getPayTime());
		vo.setMemo(bean.getMemo());
		vo.setStatus(bean.getStatus());

		if(StringUtils.isNotNull(bean.getSpecialties())){
			vo.setSpecialties(BeanUtils.deserializeAsMap(bean.getSpecialties()));
		}

		if(bean.getUser() != null){
			vo.setUser(UserVO.transform(bean.getUser()));
		}

		if(bean.getExpert() != null){
			vo.setExpert(UserVO.transform(bean.getExpert()));
		}
		return vo;
	}

	public static OrderBean transform(OrderVO vo) throws Exception{
		if (vo == null) {
			return null;
		}
		OrderBean bean = new OrderBean();
		vo.toBean(bean);

		bean.setTitle(vo.getTitle());
		bean.setType(vo.getType());
		bean.setFromTime(vo.getFromTime());
		bean.setEndTime(vo.getEndTime());
		bean.setMemo(vo.getMemo());
		bean.setSpecialties(BeanUtils.serialize(vo.getSpecialties()));

		if(vo.getExpert() != null){
			UserProfileBean expert = new UserProfileBean(vo.getExpert().getId());
			bean.setExpert(expert);
		}
		return bean;
	}

	public OrderVO() {
		super();
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Map<String, Object> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(Map<String, Object> specialties) {
		this.specialties = specialties;
	}

	public Long getFromTime() {
		return fromTime;
	}

	public void setFromTime(Long fromTime) {
		this.fromTime = fromTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Long usedTime) {
		this.usedTime = usedTime;
	}

	public Long getPayTime() {
		return payTime;
	}

	public void setPayTime(Long payTime) {
		this.payTime = payTime;
	}

	public OrderBean.Status getStatus() {
		return status;
	}

	public void setStatus(OrderBean.Status status) {
		this.status = status;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public UserVO getExpert() {
		return expert;
	}

	public void setExpert(UserVO expert) {
		this.expert = expert;
	}
}
