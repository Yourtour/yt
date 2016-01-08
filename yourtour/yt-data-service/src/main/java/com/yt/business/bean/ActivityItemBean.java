package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.BaseDictBeanImpl;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.ResType;
import com.yt.business.common.Constants.Status;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.List;

/**
 * 资源bean，定义了各类资源的公共信息，不直接创建表，被后续其他资源对象继承（如：景点、宾馆、饭店等）
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月2日</td>
 * <td>Tony.Zhang</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年7月29日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和neo4j的操作模式进行修改完善，并抽象为一个基类。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@NodeEntity
public class ActivityItemBean extends BaseBeanImpl {
	private static final long serialVersionUID = -8980153602025087935L;

	@HbaseColumn(name = "img")
	private String imageUrl; // 图片

	private String title;

	private String memo;

	private int thumbupNum; // 点评数

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = SceneResourceBean.class, direction = Direction.INCOMING)
	private transient SceneResourceBean resource = null;

	public ActivityItemBean() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getThumbupNum() {
		return thumbupNum;
	}

	public void setThumbupNum(int thumbupNum) {
		this.thumbupNum = thumbupNum;
	}

	public SceneResourceBean getResource() {
		return resource;
	}

	public void setResource(SceneResourceBean resource) {
		this.resource = resource;
	}
}
