package com.yt.vo.home;

import java.io.Serializable;
import java.util.List;

import com.yt.vo.AlongVO;
import com.yt.vo.LineVO;

public class HomeVO implements Serializable {
	private static final long serialVersionUID = -4014129603141317820L;
	
	private List<LineVO> lines = null; 
	
	private List<AlongVO> alongs = null; 
	
	public HomeVO(){
		
	}

	public List<LineVO> getLines() {
		return lines;
	}

	public void setLines(List<LineVO> lines) {
		this.lines = lines;
	}

	public List<AlongVO> getAlongs() {
		return alongs;
	}

	public void setAlongs(List<AlongVO> alongs) {
		this.alongs = alongs;
	}
	
}
