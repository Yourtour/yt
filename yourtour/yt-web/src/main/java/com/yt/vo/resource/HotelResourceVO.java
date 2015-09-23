/**
 * 
 */
package com.yt.vo.resource;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.common.Constants.ResType;

public class HotelResourceVO extends ResourceVO {
	private String name; // 名称
	private String accommodationStandard; // 住宿标准
	private String specialRoom; // 特色房
	private String roomEquipment; // 房间设施
	private String networkInfo; // 网络信息

	public static HotelResourceVO transform(HotelResourceBean bean) {
		if (bean == null) {
			return null;
		}
		HotelResourceVO vo = new HotelResourceVO();
		vo.fromBean(bean);
		vo.setName(bean.getName());
		vo.setRowKey(bean.getName());
		vo.setAccommodationStandard(bean.getAccommodationStandard());
		vo.setSpecialRoom(bean.getSpecialRoom());
		vo.setRoomEquipment(bean.getRoomEquipment());
		vo.setNetworkInfo(bean.getNetworkInfo());
		return vo;
	}

	public static HotelResourceBean transform(HotelResourceVO vo) {
		if (vo == null) {
			return null;
		}
		HotelResourceBean bean = new HotelResourceBean();
		vo.toBean(bean);
		bean.setName(vo.getName());
		bean.setRowKey(bean.getName());
		bean.setAccommodationStandard(vo.getAccommodationStandard());
		bean.setSpecialRoom(vo.getSpecialRoom());
		bean.setRoomEquipment(vo.getRoomEquipment());
		bean.setNetworkInfo(vo.getNetworkInfo());
		return bean;
	}

	public HotelResourceVO() {
		super();
		super.setType(ResType.HOTEL);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccommodationStandard() {
		return accommodationStandard;
	}

	public void setAccommodationStandard(String accommodationStandard) {
		this.accommodationStandard = accommodationStandard;
	}

	public String getSpecialRoom() {
		return specialRoom;
	}

	public void setSpecialRoom(String specialRoom) {
		this.specialRoom = specialRoom;
	}

	public String getRoomEquipment() {
		return roomEquipment;
	}

	public void setRoomEquipment(String roomEquipment) {
		this.roomEquipment = roomEquipment;
	}

	public String getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(String networkInfo) {
		this.networkInfo = networkInfo;
	}
}