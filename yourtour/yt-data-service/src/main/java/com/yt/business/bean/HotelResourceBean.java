package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.dal.hbase.annotation.HbaseColumn;

public class HotelResourceBean extends ResourceBean {
	private static final long serialVersionUID = -6614601113440754661L;
	private static final String INDEX_NAME = "hotel";

	private @HbaseColumn(name = "name")
	@Indexed
	String name; // 名称
	private @HbaseColumn(name = "accs")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String accommodationStandard; // 住宿标准
	private @HbaseColumn(name = "srom")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String specialRoom; // 特色房
	private @HbaseColumn(name = "rmeq")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String roomEquipment; // 房间设施
	private @HbaseColumn(name = "neti")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String networkInfo; // 网络信息

	public HotelResourceBean() {
		super();
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
