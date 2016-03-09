package com.yt.business.service.impl;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.CommentBean;
import com.yt.business.repository.neo4j.CommentBeanRepository;
import com.yt.business.repository.neo4j.CommentTuple;
import com.yt.business.service.ICommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommentServiceImpl extends CrudAllInOneOperateImpl<CommentBean> implements ICommentService<CommentBean> {
	private static final Log LOG = LogFactory.getLog(CommentServiceImpl.class);

	@Autowired
	private CommentBeanRepository repository;

	@Override
	public List<CommentBean> getComments(Long subjectId, String filter, Long nextCursor, int limit) throws Exception {
		List<CommentBean> comments = new ArrayList<>();

		//String queryStr = "START n=node(%d) MATCH n-[:HAS]->(comment:CommentBean)-[:BELONG]->(user:UserProfileBean) %s  RETURN comment, user";
		String queryStr = "MATCH (comment:CommentBean)-[:BELONG]->(user:UserProfileBean) %s  RETURN comment, user";
		Map<String, Object> params = new HashMap<>();
		params.put("id", subjectId);

		String where = " ";
		if(filter.equals("imageNum")){
			where = " where comment.imageUrls is not null ";
		}else if(filter.equals("goodNum")){
			where = " where comment.score >= 4.0 ";
		}else if(filter.equals("mediumNum")){
			where =  " where comment.score >= 3.0 and comment.score < 4.0 ";
		}else if(filter.equals("badNum")){
			where = " where comment.score < 3.0 ";
		}
		params.put("where", where);

		//List<CommentTuple> tuples = this.query(String.format(queryStr, subjectId, where), null, CommentTuple.class);
		List<CommentTuple> tuples = this.query(String.format(queryStr, where), null, CommentTuple.class);
		if(tuples != null){
			CommentBean comment = null;
			for(CommentTuple tuple : tuples){
				comment = tuple.getComment();
				comment.setUser(tuple.getUser());

				comments.add(comment);
			}
		}
		return comments;
	}


}
