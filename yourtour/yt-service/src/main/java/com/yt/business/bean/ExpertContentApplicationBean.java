package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 达人内容审核申请实体对象
 * 
 * @author John.Peng
 *
 */
@HbaseTable(name = "T_EXPERT_CONTENT_APPLICATION")
@NodeEntity
public class ExpertContentApplicationBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	public enum Status {
		/**
		 * 已申请
		 */
		REQUESTED,
		/**
		 * 审核中
		 */
		APPROVING,
		/**
		 * 审核通过
		 */
		PASSED,
		/**
		 * 审核不通过
		 */
		NOT_PASS
	}

	private long requestTime, approvedTime;
	private String approvedResult;
	private Status approveStatus = Status.REQUESTED;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private transient UserProfileBean expert = null; // 相关达人

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = ExpertRouteBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<ExpertRouteBean> routes; // 待审核的达人推荐行程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = ExpertServiceBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<ExpertServiceBean> services; // 待审核的达人服务

	public ExpertContentApplicationBean() {
		super();
		this.routes = new Vector<ExpertRouteBean>();
		this.services = new Vector<ExpertServiceBean>();
	}

	public long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}

	public long getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(long approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getApprovedResult() {
		return approvedResult;
	}

	public void setApprovedResult(String approvedResult) {
		this.approvedResult = approvedResult;
	}

	public Status getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Status approveStatus) {
		this.approveStatus = approveStatus;
	}

	public UserProfileBean getExpert() {
		return expert;
	}

	public void setExpert(UserProfileBean expert) {
		this.expert = expert;
	}

	public List<ExpertRouteBean> getRoutes() {
		return routes;
	}

	public List<ExpertServiceBean> getServices() {
		return services;
	}

}
