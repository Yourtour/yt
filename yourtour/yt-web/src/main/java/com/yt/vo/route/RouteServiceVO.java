package com.yt.vo.route;

import com.yt.business.bean.RouteServiceBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.BaseVO;
import com.yt.vo.member.ExpertServiceVO;

public class RouteServiceVO extends BaseVO {
	private long 	fromDate;
	private long 	endDate;

	private int 	adultNum;
	private int 	oldNum;
	private int 	childNum;
	private int		fee;
	private String	place;
	private String	memo;

	private ExpertServiceVO expertService;

	public RouteServiceVO() {
		super();
	}

	public static RouteServiceVO transform(RouteServiceBean bean) {
		if (bean == null) {
			return null;
		}

		RouteServiceVO valueObject = new RouteServiceVO();
		valueObject.setId(bean.getId());
		valueObject.setMemo(bean.getMemo());
		valueObject.setFromDate(bean.getFromDate());
		valueObject.setEndDate(bean.getEndDate());
		valueObject.setPlace(bean.getPlace());
		valueObject.setAdultNum(bean.getAdultNum());
		valueObject.setOldNum(bean.getOldNum());
		valueObject.setChildNum(bean.getChildNum());

		valueObject.setExpertService(ExpertServiceVO.transform(bean.getService()));
		return valueObject;
	}

	public static RouteServiceBean transform(RouteServiceVO vo) {
		RouteServiceBean bean = new RouteServiceBean();

		bean.setId(vo.getId());
		bean.setChildNum(vo.getChildNum());
		bean.setAdultNum(vo.getAdultNum());
		bean.setOldNum(vo.getOldNum());
		bean.setPlace(vo.getPlace());
		bean.setMemo(vo.getMemo());
		bean.setFromDate(vo.getFromDate());
		bean.setEndDate(vo.getEndDate());

		return bean;
	}

	public long getFromDate() {
		return fromDate;
	}

	public void setFromDate(long fromDate) {
		this.fromDate = fromDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public int getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(int adultNum) {
		this.adultNum = adultNum;
	}

	public int getOldNum() {
		return oldNum;
	}

	public void setOldNum(int oldNum) {
		this.oldNum = oldNum;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public ExpertServiceVO getExpertService() {
		return this.expertService;
	}

	public void setExpertService(ExpertServiceVO expertServiceVO) {
		this.expertService = expertServiceVO;
	}

	public String getFromDateStr(){
		if(this.fromDate == 0) return "";

		return DateUtils.formatDate(this.fromDate, "yyyy-MM-dd");
	}

	public String getEndDateStr(){
		if(this.endDate == 0) return "";

		return DateUtils.formatDate(this.endDate, "yyyy-MM-dd");
	}
}
