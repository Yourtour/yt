/**
 * 
 */
package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 旅游景点实体对象
 * 
 * @author john.peng
 * 
 */
@HbaseTable(name = "T_SCENE_INFO")
@NodeEntity
public class SceneResourceBean extends ResourceBean {
	private static final long serialVersionUID = 563053332776568183L;
	private static final String INDEX_NAME = "scene";
	
	private @HbaseColumn(name = "intr")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String intro; // 简介
	private @HbaseColumn(name = "tikt")
	String ticket; // 门票信息
	private @HbaseColumn(name = "map")
	String sceneMap; // 景区地图
	private @HbaseColumn(name="spsc")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String specialScene; // 必游特殊景色
	private @HbaseColumn(name = "'")
	String sceneTraffic; // 景区交通信息

	public SceneResourceBean() {
		super();
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
