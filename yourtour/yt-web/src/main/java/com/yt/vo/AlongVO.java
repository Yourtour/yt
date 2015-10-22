package com.yt.vo;

import java.io.Serializable;

import com.yt.business.bean.AlongBean;

public class AlongVO implements Serializable {
	private static final long serialVersionUID = -6629674609601413323L;
	
	private AlongBean alongBean;
	
	public AlongVO(AlongBean alongBean) {
		this.alongBean = alongBean;
	}
	
	public String getName(){
		return alongBean.getName();
	}
	
	public String getRowKey(){
		return String.valueOf(alongBean.getGraphId());
	}
	
	public String getImageUrl(){
		return alongBean.getImageUrl();
	}
}
