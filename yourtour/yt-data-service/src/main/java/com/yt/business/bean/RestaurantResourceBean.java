package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_RESTAURANT_INFO")
@NodeEntity
public class RestaurantResourceBean extends ResourceBean {
	private static final long serialVersionUID = -7687082317108442937L;
	private static final String INDEX_NAME = "restaurant";

	@HbaseColumn(name = "delf")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String deliciouFood; // 特色菜品

	@HbaseColumn(name = "rtst")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String foodStandard; // 餐饮标准

	@HbaseColumn(name = "tags")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String foodTags; // 美食标签

	@HbaseColumn(name = "neti")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String networkInfo; // 网络信息

	public RestaurantResourceBean() {
		super();
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
