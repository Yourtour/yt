package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.CommentBean;

public interface CommentBeanRepository extends GraphRepository<CommentBean> {
	/**
	 *
	 * @param subjectId
	 * @param nextCursor
	 * @param startIndex
	 * @param limit
	 * @return
	 */
	@Query("START n=node({0}) MATCH n-[:HAS]->(comment:CommentBean)-[:BELONG]->(user:UserProfileBean) RETURN comment, user")
	public List<CommentTuple> getComments(Long subjectId, Long nextCursor, Long startIndex, int limit);
}
