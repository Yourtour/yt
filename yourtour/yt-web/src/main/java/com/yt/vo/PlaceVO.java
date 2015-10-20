package com.yt.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yt.business.bean.PlaceBean;

public class PlaceVO implements Serializable {
	private static final long serialVersionUID = 7353580902980438916L;
	
	private PlaceBean  bean;
	public PlaceVO(PlaceBean bean) {
		this.bean = bean;
	}

	public String getName(){
		return bean.getName();
	}
	
	public String getRowKey(){
		return String.valueOf(bean.getGraphId());
	}
	
	public String getNum(){
		return bean.getSubs() == null?"0":String.valueOf(bean.getSubs().size());
	}
	
	public List<PlaceVO> getSubs(){
		List<PlaceVO> subs = new ArrayList<PlaceVO>();
		
		List<PlaceBean> subBeans = this.bean.getSubs();
		if(subBeans != null){
			for(PlaceBean sub : subBeans){
				subs.add(new PlaceVO(sub));
			}
		}
		return subs;
	}
}
