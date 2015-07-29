package com.yt.business.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义了用户会话信息
 */
//@HbaseTable(name = "T_USER_SESSION_INFO")
public class UserSessionBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	public UserSessionBean() {
	}
}
