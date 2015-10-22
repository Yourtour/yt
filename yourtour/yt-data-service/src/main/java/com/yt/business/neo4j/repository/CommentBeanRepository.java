package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.CommentBean;

public interface CommentBeanRepository extends GraphRepository<CommentBean> {
	/**
	 * 获取结伴评论信息
	 * @param graphId
	 * @param startIndex
	 * @param size
	 * @return
	 */
	@Query("START along=node({0}) MATCH along-[:AT]->comment-[]-> user RETURN comment, user skip {1} limit {2}")
	public List<CommentTuple> getCommentsByGraphId(Long graphId, int startIndex, int size);
}
