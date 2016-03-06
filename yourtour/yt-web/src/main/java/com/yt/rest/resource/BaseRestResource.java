package com.yt.rest.resource;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.BusinessBeanImpl;
import com.yt.business.bean.CommentBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.CommentRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.core.utils.DateUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.FileUtils;
import com.yt.utils.SessionUtils;
import com.yt.vo.CommentVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/app/base")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaseRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(BaseRestResource.class);

	private @Autowired
	CommentRepository commentRepository;

	/**
	 * 获取点评信息
	 * @param id
	 * @param nextCursor
	 * @param filter
	 * @param step
	 * @return
	 */
	@GET
	@Path("/comment/{id}")
	public ResponseDataVO<List<CommentVO>> getComments(@PathParam("id") String id, @QueryParam("nextCursor") String nextCursor, @QueryParam("filter") String filter, @QueryParam("limit") String step) {
		try{
			List<CommentVO> commentvos = new ArrayList<>();
			
			List<CommentBean> commentBeans = commentRepository.getComments(Neo4jUtils.getGraphIDFromString(id), filter, Long.valueOf(nextCursor), Integer.valueOf(step));
			if(commentBeans != null){
				for(CommentBean commentBean : commentBeans){
					commentvos.add(CommentVO.transform(commentBean));
				}
			}
			return new ResponseDataVO<List<CommentVO>>(commentvos);
		}catch(Exception exc){
			LOG.error("Exception Message.", exc);
			return new ResponseDataVO<List<CommentVO>>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 保存点评
	 * @param id
	 * @param memo
	 * @param form
	 * @return
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/comment/{id}/save")
	public ResponseDataVO<CommentVO> saveResourceComment(@PathParam("id") String id, @FormDataParam("memo") String memo, FormDataMultiPart form) {
		try{
			String userId = SessionUtils.getCurrentLoginUser();

			BusinessBeanImpl subjectBean = (BusinessBeanImpl) this.commentRepository.get(BusinessBeanImpl.class, Neo4jUtils.getGraphIDFromString(id), false);
			if(subjectBean == null){
				LOG.error("No bean found for id = " +id);
				return new ResponseDataVO<CommentVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			CommentBean commentBean = new CommentBean();
			commentBean.setMemo(memo);
			UserProfileBean user = new UserProfileBean(Neo4jUtils.getGraphIDFromString(userId));
			commentBean.setUser(user);

			StringBuffer images = new StringBuffer();
			List<FormDataBodyPart> l= form.getFields("userLogo");
			if(l != null) for (FormDataBodyPart p : l) {
				if (images.length() > 0) images.append(",");

				InputStream is = p.getValueAs(InputStream.class);
				FormDataContentDisposition detail = p.getFormDataContentDisposition();
				images.append(FileUtils.saveFile("images/comment", FileUtils.getType(detail.getFileName()), is));
			}
			commentBean.setImageUrl(images.toString());

			this.commentRepository.save(commentBean, userId);
			this.commentRepository.createRelation(commentBean, subjectBean, Constants.RELATION_TYPE_COMMENTED, Direction.INCOMING);

			commentBean = (CommentBean) this.commentRepository.get(CommentBean.class, commentBean.getGraphId(), false);
			return new ResponseDataVO<CommentVO>(CommentVO.transform(commentBean));
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseDataVO<CommentVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 保存收藏
	 * @param id
	 * @return
	 */
	@POST
	@Path("/favorite/{id}/save")
	public ResponseVO saveUserFavorite(@PathParam("id") String id) {
		try{
			String userId = SessionUtils.getCurrentLoginUser();

			BusinessBeanImpl subjectBean = (BusinessBeanImpl) this.commentRepository.get(BusinessBeanImpl.class, Neo4jUtils.getGraphIDFromString(id), false);
			if(subjectBean == null){
				LOG.error("No bean found for id = " +id);
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			UserProfileBean user = new UserProfileBean(Neo4jUtils.getGraphIDFromString(userId));

			Map<String, Object> props = new HashMap<>();
			props.put("createdDate", DateUtils.getCurrentTimeMillis());

			this.commentRepository.createRelation(user, subjectBean, Constants.RELATION_TYPE_FAVORITE, Direction.OUTGOING, props);

			return new ResponseVO();
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseVO(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 取消收藏
	 * @param id
	 * @return
	 */
	@POST
	@Path("/favorite/{id}/delete")
	public ResponseVO deleteUserFavorite(@PathParam("id") String id) {
		try{
			String userId = SessionUtils.getCurrentLoginUser();

			BusinessBeanImpl subjectBean = (BusinessBeanImpl) this.commentRepository.get(BusinessBeanImpl.class, Neo4jUtils.getGraphIDFromString(id), false);
			if(subjectBean == null){
				LOG.error("No bean found for id = " +id);
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			UserProfileBean user = new UserProfileBean(Neo4jUtils.getGraphIDFromString(userId));

			this.commentRepository.deleteRelation(user, subjectBean, Constants.RELATION_TYPE_FAVORITE);

			return new ResponseVO();
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseVO(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 保存用户点赞
	 * @param id
	 * @return
	 */
	@POST
	@Path("/like/{id}/save")
	public ResponseVO saveUserLike(@PathParam("id") String id) {
		try{
			String userId = SessionUtils.getCurrentLoginUser();

			BusinessBeanImpl subjectBean = (BusinessBeanImpl) this.commentRepository.get(BusinessBeanImpl.class, Neo4jUtils.getGraphIDFromString(id), false);
			if(subjectBean == null){
				LOG.error("No bean found for id = " +id);
				return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			UserProfileBean user = new UserProfileBean(Neo4jUtils.getGraphIDFromString(userId));

			this.commentRepository.deleteRelation(user, subjectBean, Constants.RELATION_TYPE_FAVORITE);

			return new ResponseVO();
		}catch(Exception exc){
			LOG.error("", exc);
			return new ResponseVO(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}

