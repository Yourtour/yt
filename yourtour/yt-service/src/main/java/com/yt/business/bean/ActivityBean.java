package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

/**
 * 系统发布的活动信息，包括：平台活动和达人活动等。
 * 
 * Created by John.Peng on 2016/3/12.
 */
@HbaseTable(name = "T_ACTIVITY_INFO")
@NodeEntity
public class ActivityBean extends BaseBeanImpl {
	private static final long serialVersionUID = -5019182758425160992L;

	public enum Status {
		DRAFT, // 草稿
		APPROVED_PASS, // 审核通过
		APPROVED_NOT_PASS, // 审核不通过
		PENDDING, // 排队中
		RELEASED, // 发布
		CLOSED // 关闭
	}

	private String imageUrl;
	private Status status = Status.DRAFT;

	public ActivityBean() {
		super();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
