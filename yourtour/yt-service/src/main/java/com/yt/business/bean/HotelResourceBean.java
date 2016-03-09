package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

@HbaseTable(name = "T_HOTEL_INFO")
@NodeEntity
public class HotelResourceBean extends ResourceBean {
	private static final long serialVersionUID = -6614601113440754661L;
	private static final String INDEX_NAME = "hotel";

	@HbaseColumn(name = "accs")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String accommodationStandard; // 住宿标准

	@HbaseColumn(name = "srom")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String specialRoom; // 特色房

	@HbaseColumn(name = "rmeq")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String roomEquipment; // 房间设施

	@HbaseColumn(name = "neti")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String networkInfo; // 网络信息

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_CONTAIN, type = LineBean.class, direction = Direction.INCOMING)
	private transient LineBean line = null; // 关联的线路

	public HotelResourceBean() {
		super();
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

	public LineBean getLine() {
		return line;
	}

	public void setLine(LineBean line) {
		this.line = line;
	}
}
