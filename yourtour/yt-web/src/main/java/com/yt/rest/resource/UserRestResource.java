package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.business.bean.UserBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.vo.AuthenticationVO;
import com.yt.vo.RelationConditionVO;
import com.yt.vo.maintain.UserVO;

@Component
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestResource {
	private static final Log LOG = LogFactory.getLog(UserRestResource.class);
	private static final String LOGIN_USERNAME = "login.username";

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private ICrudOperate crudOperate;

	@SuppressWarnings("unchecked")
	@GET
	public ResponseDataVO<List<UserVO>> getAllUsers() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request fetch all teh UserBean.");
		}
		List<UserVO> list = new ArrayList<UserVO>();
		try {
			List<UserBean> result = (List<UserBean>) crudOperate
					.get(UserBean.class);
			for (UserBean bean : result) {
				if (bean == null) {
					continue;
				}
				UserVO vo = UserVO.transform(bean);
				list.add(vo);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Fetch all the UserBean success, total: %d.",
						list.size()));
			}
			return new ResponseDataVO<List<UserVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the UserBean fail.", ex);
			}
			return new ResponseDataVO<List<UserVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("{id}")
	public ResponseDataVO<UserVO> getUser(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			UserBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, UserBean.class);
			} else {
				// id 是rowkey
				bean = (UserBean) crudOperate.get(UserBean.class, id);
			}
			if (bean == null) {
				return new ResponseDataVO<UserVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			UserVO vo = UserVO.transform(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Get UserBean['%s'] success.", id));
			}
			return new ResponseDataVO<UserVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch UserBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseDataVO<UserVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("import")
	public ResponseVO importData(List<UserVO> vos) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request import UserBean data.");
		}
		for (UserVO vo : vos) {
			ResponseVO response = save(vo);
			if (response.getErrorCode() != 0) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"Import UserBean data fail, error: id = '%s'.",
							vo.getRowKey()));
				}
				return response;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Import UserBean data success, total: %d.",
					vos.size()));
		}
		return new ResponseVO();
	}

	@POST
	public ResponseVO save(UserVO vo) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The UserVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			UserBean bean = UserVO.transform(vo);
			crudOperate.save(bean, true);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save UserBean['%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the UserBean[id='%s'] fail.",
								vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@DELETE
	@Path("{id}")
	public ResponseVO delete(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			UserBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = template.findOne(graphId, UserBean.class);
				id = bean.getRowKey();
			}
			crudOperate.delete(UserBean.class, id);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Delete UserBean['%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Fetch UserBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@POST
	@Path("login")
	public ResponseVO login(AuthenticationVO auth,
			@Context HttpServletRequest request) {
		if (auth == null) {
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			UserBean user = (UserBean) crudOperate.get(UserBean.class,
					"userName", auth.getCode());
			if (user == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"The user[%s] not exist, login fail.",
							auth.getCode()));
				}
				return new ResponseVO(StaticErrorEnum.USER_NOT_EXIST);
			}
			if (!auth.getPassword().equals(user.getPwd())) {
				// 认证成功
				if (LOG.isDebugEnabled()) {
					LOG.debug(String.format("The user[%s] login success.",
							auth.getCode()));
				}
				request.getSession(true).setAttribute(LOGIN_USERNAME,
						auth.getCode());
				return new ResponseVO(StaticErrorEnum.AUTHENTICATE_FAIL);
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(
						String.format("The user[%s] login fail.",
								auth.getCode()), ex);
			}
			return new ResponseVO(StaticErrorEnum.AUTHENTICATE_FAIL);
		}
	}

	@GET
	@Path("logout/{username}")
	public ResponseVO logout(@PathParam("username") String username,
			@Context HttpServletRequest request) {
		if (username == null) {
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			UserBean user = (UserBean) crudOperate.get(UserBean.class,
					"userName", username);
			if (user == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"The user[%s] not exist, logout fail.", username));
				}
				return new ResponseVO(StaticErrorEnum.USER_NOT_EXIST);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("The session is null, logout fail.");
				}
				return new ResponseVO(StaticErrorEnum.AUTHENTICATE_FAIL);
			}
			session.removeAttribute(LOGIN_USERNAME);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("The user[%s] logout success.",
						username));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The user[%s] logout fail.", username),
						ex);
			}
			return new ResponseVO(StaticErrorEnum.AUTHENTICATE_FAIL);
		}
	}

	@POST
	@Path("follow")
	public ResponseVO followUser(RelationConditionVO condition) {
		if (condition == null) {
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		String srcId = condition.getSrcId(), tarId = condition.getTarId();
		boolean isAdd = condition.isAdd();
		try {
			Neo4jUtils.maintainRelateion(template, crudOperate,
					NodeRelationshipEnum.FOLLOW, srcId, UserBean.class, tarId,
					UserBean.class, null, isAdd, true);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("'%s' 'FOLLOW' from UserBean['%s'] to UserBean['%s'] success.",
								isAdd ? "Add" : "Remove", srcId, tarId));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isDebugEnabled()) {
				LOG.error(String.format(
						"Follow from UserBean['%s'] to UserBean['%s'] fail.",
						srcId, tarId), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@POST
	@Path("watch")
	public ResponseVO watchUser(RelationConditionVO condition) {
		if (condition == null) {
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		String srcId = condition.getSrcId(), tarId = condition.getTarId();
		boolean isAdd = condition.isAdd();
		try {
			Neo4jUtils.maintainRelateion(template, crudOperate,
					NodeRelationshipEnum.WATCH, srcId, UserBean.class, tarId,
					UserBean.class, null, isAdd, true);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("'%s' 'WATCH' from UserBean['%s'] to UserBean['%s'] success.",
								isAdd ? "Add" : "Remove", srcId, tarId));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isDebugEnabled()) {
				LOG.error(String.format(
						"Follow from UserBean['%s'] to UserBean['%s'] fail.",
						srcId, tarId), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}
}
