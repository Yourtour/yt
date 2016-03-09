package com.yt.vo;

import java.io.Serializable;

import com.yt.business.bean.LineBean;

public class LineVO implements Serializable {
	private static final long serialVersionUID = -6629674609601413323L;
	
	private LineBean lineBean;
	
	public LineVO(LineBean lineBean) {
		this.lineBean = lineBean;
	}
	
	public String getName(){
		return lineBean.getName();
	}
	
	public String getRowKey(){
		return String.valueOf(lineBean.getId());
	}
	
	public String getImageUrl(){
		return lineBean.getImageUrl();
	}
}
