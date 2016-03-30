package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 达人资格审核申请实体
 * 
 * @author John.Peng
 * 
 */
@HbaseTable(name = "T_EXPERT_APPLICATION")
@NodeEntity
public class ExpertApplicationBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	public enum IdentityCardType {
		/**
		 * 身份证
		 */
		IDENTITY_CARD,
		/**
		 * 护照
		 */
		PASSPORT,
		/**
		 * 驾驶证
		 */
		DRIVER_LICENSE
	}

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

	private IdentityCardType identityType = IdentityCardType.IDENTITY_CARD;
	private String realName, identityCode, identityCardCopyUrl; // 实名，证件号码，身份证复印件存储路径

	private String tourGuideCode, tourGuideCopyUrl; // 导游证号码，复印件存储路径
	private String tags; // 达人标签

	private long requestTime, approvedTime;
	private String approvedResult;
	private Status approveState = Status.REQUESTED;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private transient UserProfileBean expert = null; // 相关达人

	public ExpertApplicationBean() {
		super();
	}

	public IdentityCardType getIdentityType() {
		return identityType;
	}

	public void setIdentityType(IdentityCardType identityType) {
		this.identityType = identityType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getIdentityCardCopyUrl() {
		return identityCardCopyUrl;
	}

	public void setIdentityCardCopyUrl(String identityCardCopyUrl) {
		this.identityCardCopyUrl = identityCardCopyUrl;
	}

	public String getTourGuideCode() {
		return tourGuideCode;
	}

	public void setTourGuideCode(String tourGuideCode) {
		this.tourGuideCode = tourGuideCode;
	}

	public String getTourGuideCopyUrl() {
		return tourGuideCopyUrl;
	}

	public void setTourGuideCopyUrl(String tourGuideCopyUrl) {
		this.tourGuideCopyUrl = tourGuideCopyUrl;
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

	public Status getApproveState() {
		return approveState;
	}

	public void setApproveState(Status approveState) {
		this.approveState = approveState;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public UserProfileBean getExpert() {
		return expert;
	}

	public void setExpert(UserProfileBean expert) {
		this.expert = expert;
	}

}
