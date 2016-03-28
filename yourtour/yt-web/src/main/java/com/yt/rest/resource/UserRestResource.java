package com.yt.rest.resource;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.service.IUserService;
import com.yt.core.common.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.FileUtils;
import com.yt.utils.SessionUtils;
import com.yt.vo.member.LoginVO;
import com.yt.vo.member.RegisterVO;
import com.yt.vo.member.UserBasicVO;
import com.yt.vo.member.UserVO;

@Component
@Path("/app/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(UserRestResource.class);

	@Autowired
	private IUserService userService;

	/**
	 * 根据用户ID获取注册信息接口
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	public ResponseDataVO<UserVO> getUser(@PathParam("id") Long id)
			throws Exception {
		UserProfileBean bean = userService.getUserProfileInfo(id);
		if (bean == null) {
			return new ResponseDataVO<UserVO>(StaticErrorEnum.DATA_NOT_EXIST);
		}

		UserVO vo = UserVO.transform(bean);
		return new ResponseDataVO<UserVO>(vo);
	}

	/**
	 * 用户登录注销接口
	 * 
	 * @param id
	 *            用户Profile的ID
	 * @return
	 */
	@GET
	@Path("/{id}/logout")
	public ResponseVO logout(@PathParam("id") Long id) throws Exception {
		userService.logout(id);

		// 清除当前session登录信息
		SessionUtils.clear();
		return new ResponseVO();
	}
	/**
	 * APP用户登录接口
	 * 
	 * @param loginVO
	 * @return
	 */
	@POST
	@Path("/login")
	public ResponseDataVO<UserVO> login(LoginVO loginVO) throws Exception {
		UserProfileBean user = userService.login(loginVO.getMobile(), loginVO.getPassword());
		UserVO profile = UserVO.transform(user);
		return new ResponseDataVO<UserVO>(profile);
	}

	@Path("/{mobileNO}/sendAuthcode")
	@GET
	public ResponseDataVO<String> sendAuthcode(
			@PathParam("mobileNO") String mobileNO) throws Exception {
		String authcode = userService.getAuthcode(mobileNO);
		return new ResponseDataVO<String>(authcode);
	}

	/**
	 * APP 用户账户注册接口
	 * 
	 * @param registervo
	 * @return
	 */
	@POST
	@Path("/register")
	public ResponseDataVO<UserVO> registerUserAccount(RegisterVO registervo)
			throws Exception {
		// 验证动态验证码
		String mobileNO = registervo.getMobile();
		String authcode = registervo.getAuthcode();
		if (!authcode.equals(userService.getAuthcode(mobileNO))) {
			// 验证码不匹配
			return new ResponseDataVO<UserVO>(StaticErrorEnum.AUTHENTICATE_FAIL);
		}
		UserProfileBean profile = userService.regist(registervo.getMobile(),
				registervo.getPassword());
		return new ResponseDataVO<UserVO>(UserVO.transform(profile));
	}

	/**
	 * 用户信息注册保存接口
	 * 
	 * @param profileId
	 * @param nickName
	 * @param gender
	 * @param tags
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/{id}/register")
	public ResponseDataVO<UserVO> registerProfile(
			@FormDataParam("id") Long profileId,
			@FormDataParam("nickName") String nickName,
			@FormDataParam("gender") String gender,
			@FormDataParam("tags") String tags, FormDataMultiPart form)
			throws Exception {
		UserProfileBean profile = userService.getUserProfileInfo(profileId);
		if (profile == null) {
			return new ResponseDataVO<UserVO>(StaticErrorEnum.USER_NOT_EXIST);
		}

		List<FormDataBodyPart> l = form.getFields("userLogo");
		if (l != null) {
			for (FormDataBodyPart p : l) {
				InputStream is = p.getValueAs(InputStream.class);
				FormDataContentDisposition detail = p
						.getFormDataContentDisposition();
				profile.setImageUrl(FileUtils.saveFile("images/user",
						FileUtils.getType(detail.getFileName()), is));
			}
		}

		profile.setNickName(nickName);
		profile.setGender(Constants.GenderType.getEnum(gender));
		profile.setTags(tags);
		profile = this.userService.saveUseProfile(profile);
		return new ResponseDataVO<UserVO>(UserVO.transform(profile));
	}

	/**
	 * 用户信息修改保存接口
	 * 
	 * @param id
	 * @param nickName
	 * @param birthday
	 * @param slogan
	 * @param residence
	 * @param gender
	 * @param nativePlace
	 * @param tags
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/{id}/save")
	public ResponseDataVO<UserVO> saveUserProfile(@PathParam("id") Long id,
			@FormDataParam("nickName") String nickName,
			@FormDataParam("birthday") Long birthday,
			@FormDataParam("slogan") String slogan,
			@FormDataParam("residence") String residence,
			@FormDataParam("gender") String gender,
			@FormDataParam("nativePlace") String nativePlace,
			@FormDataParam("tags") String tags, FormDataMultiPart form)
			throws Exception {
		UserProfileBean profile = userService.getUserProfileInfo(id);
		if (profile == null) {
			return new ResponseDataVO<UserVO>(StaticErrorEnum.USER_NOT_EXIST);
		}

		List<FormDataBodyPart> l = form.getFields("userLogo");
		if (l != null) {
			for (FormDataBodyPart p : l) {
				InputStream is = p.getValueAs(InputStream.class);
				FormDataContentDisposition detail = p
						.getFormDataContentDisposition();
				profile.setImageUrl(FileUtils.saveFile("images/user",
						FileUtils.getType(detail.getFileName()), is));
			}
		}

		if (nickName != null)
			profile.setNickName(nickName);
		if (birthday != null)
			profile.setBirthday(birthday);
		if (gender != null)
			profile.setGender(Constants.GenderType.getEnum(gender));
		if (slogan != null)
			profile.setSlogan(slogan);
		if (nativePlace != null)
			profile.setNativePlace(nativePlace);
		if (residence != null)
			profile.setResidence(residence);
		if (tags != null)
			profile.setTags(tags);

		profile = this.userService.saveUseProfile(profile);
		return new ResponseDataVO<UserVO>(UserVO.transform(profile));
	}

	@GET
	@Path("/{userId}/basic")
	public ResponseDataVO<UserBasicVO> getUserBasicInfor(
			@PathParam("userId") long userId) throws Exception {
		UserProfileBean user = userService.getUserProfileInfo(userId, false);
		return new ResponseDataVO<UserBasicVO>(UserBasicVO.transform(user));
	}
}