package com.yt.business.bean;

import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义了用户调查信息
 */
//@HbaseTable(name = "T_USER_SURVEY_INFO")
public class UserSurveyBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	private 	@HbaseColumn(name = "uid")		String 	userId; 	//用户ID
	private 	@HbaseColumn(name = "qid")		String 	questionId; 	//调查问题ID
	private 	@HbaseColumn(name = "title")		String	title; 	//调查标题
	private 	@HbaseColumn(name = "answ")		String 	answer; 	//调查回答
	private 	@HbaseColumn(name = "cuid")		String createdUserId = "";
	private 	@HbaseColumn(name = "ct")			long createdTime;
	private 	@HbaseColumn(name = "uuid")		String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")			long updatedTime;
	private 	@HbaseColumn(name = "stat")		Status	status;

	public UserSurveyBean() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
