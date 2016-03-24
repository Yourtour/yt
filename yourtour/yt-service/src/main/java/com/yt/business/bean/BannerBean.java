package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 定义了首页中Banner中显示的相关信息，包括：旅游资讯、旅游活动。<br>
 * 相关旅游资讯应当由运维人员通过运维管理系统编撰发布而成。
 * 
 * @author John.Peng
 * 
 */
@HbaseTable(name = "T_BANNER_INFO")
@NodeEntity
public class BannerBean extends SocialBeanImpl {
	private static final long serialVersionUID = -2639574489334772005L;
	private static final String INDEX_NAME = "banner";

	public enum Status {
		DRAFT, // 草稿
		CLOSED, // 已关闭
		RELEASED, // 发布
		APPROVED_PASS, // 审核通过
		APPROVED_NOT_PASS // 审核不通过
	}

	private String imageUrl; // Banner图片
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String title; // Banner标题
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String subTitle; // Banner子（副）标题
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String content; // Banner内容，可能是一篇编辑好的HTML5文档

	private long startTime; // Banner有效开始时间
	private long endTime; // Banner有效结束时间
	private Status status = Status.DRAFT;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = RouteMainBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteMainBean> routes; // Banner关联的行程
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ExpertBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<ExpertBean> experts; // Banner关联的达人

	public BannerBean() {
		super();
		this.routes = new Vector<RouteMainBean>();
		this.experts = new Vector<ExpertBean>();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<RouteMainBean> getRoutes() {
		return routes;
	}

	public List<ExpertBean> getExperts() {
		return experts;
	}

}
