package com.yt.business.service;

import com.yt.business.bean.CommentBean;

import java.util.List;

public interface ICommentService {
	/**
	 * 点评保存
	 * @param comment
	 * @throws Exception
	 */
	public void saveComment(CommentBean comment) throws Exception;

	/**
	 * 删除点评
	 * @param comment
	 * @throws Exception
	 */
	public void deleteComment(CommentBean comment) throws Exception;

	/**
	 * 获取点评信息
	 * @param commentId
	 * @return
	 * @throws Exception
	 */
	public CommentBean getComment(Long commentId) throws Exception;

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
