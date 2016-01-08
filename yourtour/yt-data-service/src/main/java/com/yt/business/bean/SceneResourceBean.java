/**
 * 
 */
package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

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

	@HbaseColumn(name = "intr")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String intro; // 简介

	@HbaseColumn(name = "tikt")
	private String ticket; // 门票信息

	@HbaseColumn(name = "map")
	private String sceneMap; // 景区地图

	@HbaseColumn(name = "spsc")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String specialScene; // 必游特殊景色

	@HbaseColumn(name = "'sctr")
	private String sceneTraffic; // 景区交通信息

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_CONTAIN, type = LineBean.class, direction = Direction.INCOMING)
	private transient LineBean line = null; // 关联的线路

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

	public LineBean getLine() {
		return line;
	}

	public void setLine(LineBean line) {
		this.line = line;
	}
}
