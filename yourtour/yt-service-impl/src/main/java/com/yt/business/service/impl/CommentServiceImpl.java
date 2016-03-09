package com.yt.business.service.impl;

import com.yt.business.CommentBaseBean;
import com.yt.business.bean.CommentBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.core.common.AppException;
import com.yt.business.common.Constants;
import com.yt.core.common.StaticErrorEnum;
import com.yt.business.service.ICommentService;
import com.yt.core.utils.DateUtils;
import com.yt.neo4j.repository.CrudOperate;
import com.yt.neo4j.repository.RelationshipCrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends BaseServiceImpl implements ICommentService {
	private static final Log LOG = LogFactory.getLog(CommentServiceImpl.class);

	@Autowired
	private CrudOperate<CommentBean> crudOperate;

	@Autowired
	private CrudOperate<UserProfileBean> userCrudOperate;

	@Autowired
	private CrudOperate<CommentBaseBean> entityCrudOperate;

	@Autowired
	private RelationshipCrudOperate<UserProfileBean, CommentBean> userCommentRelationship;

	@Autowired
	private RelationshipCrudOperate<CommentBaseBean, CommentBean> entityCommentRelationship;

	@Autowired
	private RelationshipCrudOperate<CommentBean, CommentBean> commentFollowRelationship;

	public CommentServiceImpl(){}

	@Override
	public void saveComment(CommentBean comment) throws Exception {
		//保存实体信息
		crudOperate.save(comment);

		//保存点评和被点评实体之间关系
		UserProfileBean user = userCrudOperate.get(comment.getUser().getId());
		if(user == null){
			throw new AppException(StaticErrorEnum.USER_NOT_EXIST);
		}
		this.userCommentRelationship.createRelation(user, comment, Constants.RELATION_TYPE_COMMENTED, Direction.OUTGOING);

		//保存点评和点评者之间关系
		CommentBaseBean entity = entityCrudOperate.get(comment.getEntity().getId());
		if(entity == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}
		this.entityCommentRelationship.createRelation(entity, comment, Constants.RELATION_TYPE_COMMENTED, Direction.OUTGOING);

		Long parentId = comment.getParentId();
		if (parentId != null) {
			//保存点评回复关系
			CommentBean parent = crudOperate.get(parentId, false);
			if (parent != null) {
				commentFollowRelationship.createRelation(comment, parent, Constants.RELATION_TYPE_FOLLOW, Direction.OUTGOING);
			}
		}

		//维护被点评实体的点评次数
		entity.setCommentNum(entity.getCommentNum() + 1);
		entityCrudOperate.save(entity);
	}

	@Override
	public void deleteComment(CommentBean comment) throws Exception {
		comment = crudOperate.get(comment.getId());
		if (comment == null) {
			LOG.warn(String.format("No CommentBean found for id=%d", comment.getId()));
			return;
		}

		UserProfileBean user = userCrudOperate.get(comment.getUser().getId());
		if(user == null){
			throw new AppException(StaticErrorEnum.USER_NOT_EXIST);
		}
		if (comment.getCreatedUserId() != user.getId()) { //权限检查，防止非法用户操作
			throw new AppException(StaticErrorEnum.DB_OPERATE_PERMISSION_FAIL);
		}

		//保存点评者和被点评实体之间关系
		this.userCommentRelationship.deleteRelation(user, comment, Constants.RELATION_TYPE_COMMENTED);

		//保存点评和被点评实体之间关系
		CommentBaseBean entity = entityCrudOperate.get(comment.getEntity().getId());
		if(entity == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}
		this.entityCommentRelationship.deleteRelation(entity, comment, Constants.RELATION_TYPE_COMMENTED);

		comment.setUpdatedUserId(user.getId());
		comment.setUpdatedTime(DateUtils.getCurrentTimeMillis());
		crudOperate.delete(comment);
	}

	@Override
	public CommentBean getComment(Long commentId) throws Exception {
		CommentBean comment = crudOperate.get(commentId);

		return comment;
	}

	@Override
	public List<CommentBean> getComments(Long subjectId, String filter, Long nextCursor, int limit) throws Exception {
		List<CommentBean> comments = new ArrayList<>();

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

		return crudOperate.query(String.format(queryStr, where), null);
	}
}
