package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 达人推荐行程实体
 * 
 * @author John.Peng
 * 
 */
@HbaseTable(name = "T_EXPERT_ROUTE")
@NodeEntity
public class ExpertRouteBean extends SocialBeanImpl {
	private static final long serialVersionUID = 8766988875819182198L;

	@HbaseColumn(name = "title")
	private String title; // 行程名称
	private String imageUrl;
	private String intro; // 行程简介
	private String tags; // 行程标签
	private String fee; // 行程费用
	private String currency;
	private String feeIncluding;
	private String feeExcluding;
	private String withdraw; // 退款规则

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private transient UserProfileBean user = null; // 服务相关达人

	public ExpertRouteBean() {
		super();
	}

	public ExpertRouteBean(Long id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeeIncluding() {
		return feeIncluding;
	}

	public void setFeeIncluding(String feeIncluding) {
		this.feeIncluding = feeIncluding;
	}

	public String getFeeExcluding() {
		return feeExcluding;
	}

	public void setFeeExcluding(String feeExcluding) {
		this.feeExcluding = feeExcluding;
	}

	public String getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}
