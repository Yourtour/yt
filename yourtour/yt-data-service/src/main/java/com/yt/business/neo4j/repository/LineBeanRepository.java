package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;

public interface LineBeanRepository extends GraphRepository<LineBean> {
	/**
	 * 根据GraphId获取指定的线路，并关联查询对应的目的地
	 * 
	 * @param graphId
	 *            图ID
	 * @return 已经关联了目的地信息的线路
	 */
	@Query("START line=node({0}) MATCH line-[:AT]->place RETURN line, place")
	public LinePlaceTuple getLineByGraphId(Long graphId);

	/**
	 * 根据分页要求查询所有的线路
	 * 
	 * @param skip
	 *            分页起始记录数
	 * @param limit
	 *            本页最大记录数
	 * @return 该页的线路
	 */
	@Query("MATCH (line:LineBean)-[:AT]->place RETURN line, place SKIP {0} LIMIT {1}")
	public List<LinePlaceTuple> getLinesByPage(long skip, long limit);

	@QueryResult
	public class LinePlaceTuple {
		@ResultColumn("line")
		private LineBean line;

		@ResultColumn("place")
		private PlaceBean place;

		public LineBean getLine() {
			return line;
		}

		public void setLine(LineBean line) {
			this.line = line;
		}

		public PlaceBean getPlace() {
			return place;
		}

		public void setPlace(PlaceBean place) {
			this.place = place;
		}
	}
}
