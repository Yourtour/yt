/**
 * 
 */
package com.yt.vo.resource;

import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.bean.SceneResourceBean;

public class SceneResourceVO extends ResourceVO {
	private String intro; // 简介
	private String ticket; // 门票信息
	private String sceneMap; // 景区地图
	private String specialScene; // 必游特殊景色
	private String sceneTraffic; // 景区交通信息

	public static SceneResourceVO transform(SceneResourceBean bean) {
		if (bean == null) {
			return null;
		}
		SceneResourceVO vo = new SceneResourceVO();
		vo.fromBean(bean);
		vo.setRowKey(bean.getName());
		vo.setIntro(bean.getIntro());
		vo.setTicket(bean.getTicket());
		vo.setSceneMap(bean.getSceneMap());
		vo.setSpecialScene(bean.getSpecialScene());
		vo.setSceneTraffic(bean.getSceneTraffic());
		return vo;
	}

	public static SceneResourceBean transform(SceneResourceVO vo) {
		if (vo == null) {
			return null;
		}
		SceneResourceBean bean = new SceneResourceBean();
		vo.toBean(bean);
		bean.setRowKey(bean.getName());
		bean.setIntro(vo.getIntro());
		bean.setTicket(vo.getTicket());
		bean.setSceneMap(vo.getSceneMap());
		bean.setSpecialScene(vo.getSpecialScene());
		bean.setSceneTraffic(vo.getSceneTraffic());
		return bean;
	}

	public SceneResourceVO() {
		super();
		super.setType(ResourceType.SCENE);
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getSceneMap() {
		return sceneMap;
	}

	public void setSceneMap(String sceneMap) {
		this.sceneMap = sceneMap;
	}

	public String getSpecialScene() {
		return specialScene;
	}

	public void setSpecialScene(String specialScene) {
		this.specialScene = specialScene;
	}

	public String getSceneTraffic() {
		return sceneTraffic;
	}

	public void setSceneTraffic(String sceneTraffic) {
		this.sceneTraffic = sceneTraffic;
	}
}
