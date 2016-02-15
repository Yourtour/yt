package com.yt.vo.route;

import com.yt.business.bean.RouteServiceBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.BaseVO;
import com.yt.vo.member.ExpertServiceVO;

public class RouteServiceVO extends BaseVO {
	private String 	useDate;
	private int 	memberNum;
	private String	address;
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
		valueObject.setId(bean.getGraphId());
		valueObject.setMemo(bean.getMemo());
		valueObject.setUseDate(DateUtils.formatDate(bean.getUseDate()));
		valueObject.setMemberNum(bean.getMemberNum());

		valueObject.setExpertService(ExpertServiceVO.transform(bean.getService()));
		return valueObject;
	}

	public static RouteServiceBean transform(RouteServiceVO vo) {
		RouteServiceBean bean = new RouteServiceBean();

		return bean;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}
