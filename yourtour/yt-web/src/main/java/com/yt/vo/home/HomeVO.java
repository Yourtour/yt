package com.yt.vo.home;

import java.io.Serializable;
import java.util.List;

public class HomeVO implements Serializable {
	private static final long serialVersionUID = -4014129603141317820L;
	
	private List<LineVO> lines = null; 
	
	public HomeVO(){
		
	}

	public List<LineVO> getLines() {
		return lines;
	}

	public void setLines(List<LineVO> lines) {
		this.lines = lines;
	}
	
}
