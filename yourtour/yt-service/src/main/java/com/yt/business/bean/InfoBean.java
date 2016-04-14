package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.hbase.annotation.HbaseTable;

/**
 * 系统发布的咨询信息。
 * 
 * Created by John.Peng on 2016/3/12.
 */
@HbaseTable(name = "T_INFORMATION_INFO")
@NodeEntity
public class InfoBean extends ContentBean {
	private static final long serialVersionUID = -5019182758425160992L;

	public InfoBean() {
		super();
	}
}
