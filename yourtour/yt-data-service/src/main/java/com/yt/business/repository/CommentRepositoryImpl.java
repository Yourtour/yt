package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.AlongBean;
import com.yt.business.bean.CommentBean;
import com.yt.business.neo4j.repository.AlongBeanRepository;
import com.yt.business.neo4j.repository.AlongTuple;
import com.yt.business.neo4j.repository.CommentBeanRepository;
import com.yt.business.neo4j.repository.CommentTuple;
import com.yt.core.utils.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentRepositoryImpl extends CrudAllInOneOperateImpl implements CommentRepository {
	private static final Log LOG = LogFactory.getLog(CommentRepositoryImpl.class);

	@Autowired
	private CommentBeanRepository repository;

	@Override
	public List<CommentBean> getComments(Long subjectId, Long start, int limit) throws Exception {
		List<CommentBean> comments = new ArrayList<>();

		List<CommentTuple> tuples = repository.getComments(subjectId, start, limit);
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
