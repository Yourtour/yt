package com.yt.business.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.business.bean.UserBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.utils.Neo4jUtils;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;

@Component
public class UserRepositoryImpl extends CrudGeneralOperate implements
		UserRepository {
	private static final Log LOG = LogFactory.getLog(UserRepositoryImpl.class);

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private UserRepository repository;

	public UserRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.UserRepository#getUserByGraphId(java.lang.
	 * Long)
	 */
	@Override
	public UserBean getUserByGraphId(Long id) throws Exception {
		return template.findOne(id, UserBean.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.UserRepository#getUserByRowkey(java.lang.String
	 * )
	 */
	@Override
	public UserBean getUserByRowkey(String rowKey) throws Exception {
		return (UserBean) super.get(UserBean.class, rowKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.UserRepository#getUserByField(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public UserBean getUserByField(String fieldName, String value)
			throws Exception {
		return (UserBean) super.get(UserBean.class, fieldName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.repository.UserRepository#getUsersByPage(long, long)
	 */
	@Override
	public List<UserBean> getUsersByPage(long start, long limit)
			throws Exception {
		return repository.getUsersByPage(start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.UserRepository#followUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void followUser(String srcUserId, String tarUserId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.FOLLOW;
		Neo4jUtils.maintainRelateion(template, this, relationship, srcUserId,
				UserBean.class, tarUserId, UserBean.class, null, true, true);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Create a relationship: UserBean[%s] <=%s=> UserBean[%s].",
					srcUserId, relationship.name(), tarUserId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.UserRepository#unfollowUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void unfollowUser(String srcUserId, String tarUserId)
			throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.FOLLOW;
		Neo4jUtils.maintainRelateion(template, this, relationship, srcUserId,
				UserBean.class, tarUserId, UserBean.class, null, false, true);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Remove a relationship: UserBean[%s] <=%s=> UserBean[%s].",
					srcUserId, relationship.name(), tarUserId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.UserRepository#watchUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void watchUser(String srcUserId, String tarUserId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.WATCH;
		Neo4jUtils.maintainRelateion(template, this, relationship, srcUserId,
				UserBean.class, tarUserId, UserBean.class, null, true, false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Create a relationship: UserBean[%s] =%s=> UserBean[%s].",
					srcUserId, relationship.name(), tarUserId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.UserRepository#unwatchUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void unwatchUser(String srcUserId, String tarUserId)
			throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.WATCH;
		Neo4jUtils.maintainRelateion(template, this, relationship, srcUserId,
				UserBean.class, tarUserId, UserBean.class, null, false, false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Remove a relationship: UserBean[%s] =%s=> UserBean[%s].",
					srcUserId, relationship.name(), tarUserId));
		}
	}
}
