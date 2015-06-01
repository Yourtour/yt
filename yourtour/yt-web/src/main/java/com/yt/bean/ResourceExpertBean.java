package com.yt.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * @author Tony.Zhang
 * 
 * 资源相关达人，定义了和资源相关的达人
 *
 */
@HbaseTable(name = "T_RESOURCE_EXPERT_INFO")
public class ResourceExpertBean extends BaseBean {
	private static final long serialVersionUID = -8980153602025087935L;
	
	private 	@HbaseColumn(name = "id")			String 	id; 	//达人ID
	private 	@HbaseColumn(name = "rid")				String 	resId; 	//资源ID
	private 	@HbaseColumn(name = "uid")				String 	userId; 	//达人用户ID
	private 	@HbaseColumn(name = "url")			String 	imageUrl; 	//图片
	
	public ResourceExpertBean() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
