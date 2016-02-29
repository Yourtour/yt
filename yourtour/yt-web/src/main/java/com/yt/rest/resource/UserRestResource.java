package com.yt.rest.resource;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.AppException;
import com.yt.business.common.Constants;
import com.yt.business.repository.UserRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.core.utils.Base64Utils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.FileUtils;
import com.yt.utils.WebUtils;
import com.yt.vo.member.LoginVO;
import com.yt.vo.member.RegisterVO;
import com.yt.vo.member.UserVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.InputStream;
import java.util.List;

@Component
@Path("app/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestResource extends BaseRestResource{
	private static final Log LOG = LogFactory.getLog(UserRestResource.class);

	@Autowired
	private UserRepository userRepository;

	@GET
	@Path("{id}")
	public ResponseDataVO<UserVO> getUser(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			UserProfileBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (UserProfileBean) userRepository.get(UserProfileBean.class, graphId);
			} else {
				// id 是rowkey
				bean = (UserProfileBean) userRepository.get(UserProfileBean.class, "rowKey",
						id);
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

	@GET
	@Path("logout/{username}")
	public ResponseVO logout(@PathParam("username") String username,
			@Context HttpServletRequest request) {
		if (username == null) {
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			UserProfileBean user = (UserProfileBean) userRepository.get(UserProfileBean.class,
					"code", username);
			if (user == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"The user[%s] not exist, logout fail.", username));
				}
				return new ResponseVO(StaticErrorEnum.USER_NOT_EXIST);
			}
			// 清除当前session登录信息
			WebUtils.setCurrentLoginUser(request, null);
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
	/**
	 * APP用户登录接口
	 * @param loginVO
	 * @return
	 */
	@POST
	@Path("/login")
	public ResponseDataVO<UserVO> login(LoginVO loginVO){
		try{
			UserProfileBean user = userRepository.getUser(loginVO.getMobile(), loginVO.getPassword());

			UserVO profile = UserVO.transform(user);
			return new ResponseDataVO<UserVO>(profile);
		} catch (AppException ex) {
			return new ResponseDataVO<UserVO>(StaticErrorEnum.AUTHENTICATE_FAIL);
		} catch (Exception ex) {
			LOG.error("Exception raised when registering user account.", ex);
			return new ResponseDataVO<UserVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * APP 用户账户注册接口
	 * @param registervo
	 * @return
	 */
	@POST
	@Path("/account/register")
	public ResponseDataVO<UserVO> registerUserAccount(RegisterVO registervo){
		try{
			UserProfileBean profile = userRepository.getUserByUserName(registervo.getMobile());
			if(profile != null){
				return new ResponseDataVO<UserVO>(StaticErrorEnum.USER_EXIST);
			}
			
			UserAccountBean account = new UserAccountBean();
			account.setUserName(registervo.getMobile());
			account.setPwd(Base64Utils.MD5(registervo.getPassword()));
			
			profile = new UserProfileBean();
			profile.setMobileNo(registervo.getMobile());
			account.setProfile(profile);
			this.userRepository.save(account, String.valueOf(account.getGraphId()));

			profile = (UserProfileBean) userRepository.getUserByUserName(account.getUserName());
			return new ResponseDataVO<UserVO>(UserVO.transform(profile));
		} catch (Exception ex) {
			LOG.error("Exception raised when registering user account.", ex);
			return new ResponseDataVO<UserVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 *
	 * @param form
	 * @return
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/profile/save")
	public ResponseDataVO<UserVO> registerProfile(@FormDataParam("id") Long profileId,
													  @FormDataParam("nickName") String nickName,
													  @FormDataParam("gender") String gender,
													  FormDataMultiPart form){
		try{
			UserProfileBean profile = userRepository.getUserByNickName(nickName);
			if(profile != null){
				return new ResponseDataVO<UserVO>(StaticErrorEnum.NICKNAME_EXIST);
			}

			profile = (UserProfileBean)userRepository.get(UserProfileBean.class, profileId, false);
			if(profile == null){
				return new ResponseDataVO<UserVO>(StaticErrorEnum.USER_NOT_EXIST);
			}

			List<FormDataBodyPart> l= form.getFields("userLogo");
			if(l != null) {
				for (FormDataBodyPart p : l) {
					InputStream is = p.getValueAs(InputStream.class);
					FormDataContentDisposition detail = p.getFormDataContentDisposition();
					profile.setImageUrl(FileUtils.saveFile("images/user", FileUtils.getType(detail.getFileName()), is));
				}
			}

			profile.setNickName(nickName);
			profile.setGender(Constants.GenderType.getEnum(gender));
			this.userRepository.save(profile, false, String.valueOf(profileId));

			profile = (UserProfileBean) userRepository.get(UserProfileBean.class, profileId, false);
			return new ResponseDataVO<UserVO>(UserVO.transform(profile));
		} catch (Exception ex) {
			LOG.error("Exception raised when registering user profile.", ex);
			return new ResponseDataVO<UserVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}