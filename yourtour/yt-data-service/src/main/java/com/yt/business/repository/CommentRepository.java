package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.AlongBean;
import com.yt.business.bean.CommentBean;

import java.util.List;

public interface CommentRepository extends CrudAllInOneOperate {
	/**
	 *
	 * @param subjectId
	 * @param startId
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommentBean> getComments(Long subjectId, String filter, Long startId, int limit) throws Exception;
}
