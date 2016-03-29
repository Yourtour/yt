package com.yt.oms.resource;

import com.yt.business.PagingDataBean;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.service.IUserService;
import com.yt.core.utils.CollectionUtils;
import com.yt.oms.vo.UserVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.rest.resource.RestResource;
import com.yt.utils.SessionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/24.
 */
@Component
@Path("oms/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(UserRestResource.class);

	@Autowired
	private IUserService service;

	/**
	 * 创建账号
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Path("/save")
	@POST
	public ResponseVO saveAccountInfo(UserVO vo) throws Exception {
		UserAccountBean account = new UserAccountBean();
		account.setPwd(vo.getPassword());
		account.setUserName(vo.getUserName());
		account.setType(UserAccountBean.Type.Admin);

		UserProfileBean profile = new UserProfileBean();
		profile.setRealName(vo.getRealName());

		this.service.register(account, profile);

		return new ResponseVO();
	}

	/**
	 * 登录验证
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Path("/authenticate")
	@POST
	public ResponseVO authenticateAccountInfo(UserVO vo) throws Exception {
		UserProfileBean profile = this.service.login(vo.getUserName(), vo.getPassword());

		SessionUtils.setCurrentLoginUser(profile);

		return new ResponseVO();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Path("/query")
	@POST
	public ResponsePagingDataVO<List<UserVO>> getAccountInfoes(
			@QueryParam("nextCursor") Long nextCursor,
			@QueryParam("limit") int limit, @QueryParam("total") int total,
			Map<String, Object> params) throws Exception {
		PagingDataBean<List<UserAccountBean>> pagingData = this.service.getUserProfileInfoes(nextCursor, limit, total, params);

		List<UserVO> voes = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(pagingData.getData())) {
			List<UserAccountBean> accounts = pagingData.getData();
			for (UserAccountBean account : accounts) {
				UserVO uservo = new UserVO();

				uservo.setId(account.getProfile().getId());
				uservo.setRealName(account.getProfile().getRealName());
				uservo.setUserName(account.getUserName());

				voes.add(uservo);
			}
		}
		return new ResponsePagingDataVO(pagingData.getTotal(), pagingData.getData().size(), voes);
	}
}
