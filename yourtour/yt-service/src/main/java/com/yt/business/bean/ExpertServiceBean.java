package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 达人服务实体
 * 
 * @author John.Peng
 * 
 */
@HbaseTable(name = "T_EXPERT_SERVICE")
@NodeEntity
public class ExpertServiceBean extends SocialBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	@HbaseColumn(name = "title")
	private String title; // 服务名称
	private String category; // 服务分类，来自于字典表
	private String imageUrl;
	private String content; // 服务内容
	private String fee;
	private String currency;
	private String feeIncluding;
	private String feeExcluding;
	private String withdraw; // 退款规则
	private String bookingMemo; //预订说明
	private String useMemo; //使用说明

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private transient UserProfileBean user = null; // 服务相关达人

	public ExpertServiceBean() {
		super();
	}

	public ExpertServiceBean(Long id) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}

	public String getBookingMemo() {
		return bookingMemo;
	}

	public void setBookingMemo(String bookingMemo) {
		this.bookingMemo = bookingMemo;
	}

	public String getUseMemo() {
		return useMemo;
	}

	public void setUseMemo(String useMemo) {
		this.useMemo = useMemo;
	}
}
