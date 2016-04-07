package com.yt.business.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 该实体定义目的地数据信息。
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月2日</td>
 * <td>Tony.Zhang</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年6月28日</td>
 * <td>John Peng</td>
 * <td>根据定稿的hbase和neo4j操作进行了修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_PLACE_INFO")
@NodeEntity
public class PlaceBean extends SocialBeanImpl implements Cloneable {
	private static final long serialVersionUID = -6977525800090683657L;

	private Long parentId; //父级目的地
	private String code;  //目的地编码，
	private String name;
	private String intro; //简介
	@HbaseColumn(name = "pos")
	private String position; // 位置信息
	private String feature; //特色
	private String traffic; //交通
	private String imageUrl; //图片， 可以有多张
	private String specialty; //特产
	@HbaseColumn(name = "memo")
	private String memo = ""; // 备注

	private int level = 0; //层级

	private int goneNum = 0; // 去过人数
	private int goingNum = 0; // 想去人数

	public PlaceBean() {
		super();
	}

	public PlaceBean(Long id){
		super(id);
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getGoneNum() {
		return goneNum;
	}

	public void setGoneNum(int goneNum) {
		this.goneNum = goneNum;
	}

	public int getGoingNum() {
		return goingNum;
	}

	public void setGoingNum(int goingNum) {
		this.goingNum = goingNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return super.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		PlaceBean target = (PlaceBean) obj;
		return target.getId().equals(super.getId());
	}
}
