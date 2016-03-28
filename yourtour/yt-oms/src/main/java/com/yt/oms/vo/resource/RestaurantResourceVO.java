/**
 * 
 */
package com.yt.oms.vo.resource;

import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.bean.RestaurantResourceBean;

public class RestaurantResourceVO extends ResourceVO {
	private String deliciouFood; // 特色菜品
	private String foodStandard; // 餐饮标准
	private String foodTags; // 美食标签
	private String networkInfo; // 网络信息

	public static RestaurantResourceVO transform(RestaurantResourceBean bean) {
		if (bean == null) {
			return null;
		}
		RestaurantResourceVO vo = new RestaurantResourceVO();
		vo.fromBean(bean);
		vo.setRowKey(bean.getName());
		vo.setDeliciouFood(bean.getDeliciouFood());
		vo.setFoodStandard(bean.getFoodStandard());
		vo.setFoodTags(bean.getFoodTags());
		vo.setNetworkInfo(bean.getNetworkInfo());
		return vo;
	}

	public static RestaurantResourceBean transform(RestaurantResourceVO vo) {
		if (vo == null) {
			return null;
		}
		RestaurantResourceBean bean = new RestaurantResourceBean();
		vo.toBean(bean);
		bean.setRowKey(bean.getName());
		bean.setDeliciouFood(vo.getDeliciouFood());
		bean.setFoodStandard(vo.getFoodStandard());
		bean.setFoodTags(vo.getFoodTags());
		bean.setNetworkInfo(vo.getNetworkInfo());
		return bean;
	}

	public RestaurantResourceVO() {
		super();
		super.setType(ResourceType.FOOD);
	}

	public String getDeliciouFood() {
		return deliciouFood;
	}

	public void setDeliciouFood(String deliciouFood) {
		this.deliciouFood = deliciouFood;
	}

	public String getFoodStandard() {
		return foodStandard;
	}

	public void setFoodStandard(String foodStandard) {
		this.foodStandard = foodStandard;
	}

	public String getFoodTags() {
		return foodTags;
	}

	public void setFoodTags(String foodTags) {
		this.foodTags = foodTags;
	}

	public String getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(String networkInfo) {
		this.networkInfo = networkInfo;
	}
}
