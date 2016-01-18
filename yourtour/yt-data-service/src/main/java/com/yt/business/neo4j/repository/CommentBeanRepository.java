package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.CommentBean;

public interface CommentBeanRepository extends GraphRepository<CommentBean> {
	/**
	 * 获取结伴评论信息
	 * @param subjectId
	 * @param startIndex
	 * @param size
	 * @return
	 */
	@Query("START n=node({0}) MATCH n-[:HAS]->(comment:CommentBean)-[:BELONG]->(user:UserProfileBean) {1}  RETURN comment, user")
	public List<CommentTuple> getComments(Long subjectId, String where, Long startIndex, int size);
}
