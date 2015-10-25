package com.yt.business.bean;

import java.util.List;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants.RouteSectionType;
import com.yt.hbase.annotation.HbaseTable;

/**
 * 行程bean，定义了行程基本信息
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
 * <td>2015年6月27日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和neo4j的操作模式进行修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_ROUTE_SECTION_INFO")
@NodeEntity
public class RouteSection extends BaseBeanImpl {
	private static final long serialVersionUID = -8980153602025087935L;

	private RouteSectionType type;
	
	private String name;
	
	private Long   date;
	
	private String memo;
	
	private List<RouteContent> contents;
	
	private Integer seq;
	
	public RouteSection() {
		super();
	}

	public RouteSectionType getType() {
		return type;
	}

	public void setType(RouteSectionType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<RouteContent> getContents() {
		return contents;
	}

	public void setContents(List<RouteContent> contents) {
		this.contents = contents;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	
}
