package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.common.Constants.ContentType;
import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

/**
 * 行程随记bean，定义了行程过程中的随记信息
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
 * <td>2015年6月28日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和neo4j操作进行了修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_ROUTE_NOTES_INFO")
@NodeEntity
public class RouteNotesBean extends Neo4JBaseBean {
	private static final long serialVersionUID = 1857376918585112905L;
	private static final String INDEX_NAME = "routeNotes";

	private @HbaseColumn(name = "rid")
	@RelatedTo(type = "route", direction = Direction.BOTH, elementClass = RouteBean.class)
	RouteBean route = null; // 行程ID
	private @HbaseColumn(name = "sid")
	@RelatedTo(type = "route", direction = Direction.BOTH, elementClass = RouteScheduleBean.class)
	RouteScheduleBean schedule = null; // 行程安排ID
	private @HbaseColumn(name = "url")
	transient String url = ""; //
	private @HbaseColumn(name = "type")
	transient ContentType type;
	private @HbaseColumn(name = "words")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String words = "";
	private @HbaseColumn(name = "live")
	int live;
	private @HbaseColumn(name = "cuid")
	transient String createdUserId = "";
	private @HbaseColumn(name = "ct")
	transient long createdTime;
	private @HbaseColumn(name = "uuid")
	transient String updatedUserId = "";
	private @HbaseColumn(name = "ut")
	transient long updatedTime;
	private @HbaseColumn(name = "stat")
	@Indexed
	Status status;

	public RouteNotesBean() {
		super();
	}

	public RouteBean getRoute() {
		return this.route;
	}

	public void setRoute(RouteBean route) {
		this.route = route;
	}

	public RouteScheduleBean getSchedule() {
		return this.schedule;
	}

	public void setSchedule(RouteScheduleBean schedule) {
		this.schedule = schedule;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
