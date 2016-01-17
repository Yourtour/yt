package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.AlongBean;
import com.yt.business.bean.CommentBean;

import java.util.List;

public interface CommentRepository extends CrudAllInOneOperate {
	/**
	 *
	 * @param subjectId
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommentBean> getComments(Long subjectId, Long start, int limit) throws Exception;
}
