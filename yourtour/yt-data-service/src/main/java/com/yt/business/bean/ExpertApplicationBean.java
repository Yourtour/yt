package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.List;

@HbaseTable(name = "T_EXPERT_APPLICATION")
@NodeEntity
public class ExpertApplicationBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	private Long   expertId;
	private String realName;
	private String certType;
	private String certNo;
	private String tags;
	private String address;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = ExpertBean.class, direction = Direction.INCOMING)
	private transient ExpertBean expert = null; // 服务相关达人

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_APPROVED, type = ExpertApprovementBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<ExpertApprovementBean> approvements;

	public ExpertApplicationBean() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ExpertBean getExpert() {
		return expert;
	}

	public void setExpert(ExpertBean expert) {
		this.expert = expert;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<ExpertApprovementBean> getApprovements() {
		return approvements;
	}

	public void setApprovements(List<ExpertApprovementBean> approvements) {
		this.approvements = approvements;
	}

	public Long getExpertId() {
		return expertId;
	}

	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}
}
