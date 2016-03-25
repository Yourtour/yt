package com.yt.oms.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.PagingDataBean;
import com.yt.business.bean.AdminAccountBean;
import com.yt.business.service.IAdminAccountService;
import com.yt.core.utils.CollectionUtils;
import com.yt.response.ResponsePagingDataVO;
import com.yt.response.ResponseVO;
import com.yt.rest.resource.RestResource;
import com.yt.utils.SessionUtils;

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
	private IAdminAccountService service;

	/**
	 * 创建账号
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Path("/save")
	@POST
	public ResponseVO saveAccountInfo(AdminAccountBean account)
			throws Exception {
		this.service.saveAccountInfo(account,
				SessionUtils.getCurrentLoginUser());

		return new ResponseVO();
	}

	/**
	 * 登录验证
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Path("/authenticate")
	@POST
	public ResponseVO authenticateAccountInfo(AdminAccountBean account)
			throws Exception {
		account = this.service.authenticate(account.getUserName(),
				account.getPwd());
		return new ResponseVO();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Path("/query")
	@POST
	public ResponsePagingDataVO<List<AdminAccountBean>> getAccountInfoes(
			@QueryParam("nextCursor") Long nextCursor,
			@QueryParam("limit") int limit, @QueryParam("total") int total,
			Map<String, Object> params) throws Exception {
		PagingDataBean<List<AdminAccountBean>> pagingData = this.service
				.getAccountInfoes(nextCursor, limit, total, params);

		if (CollectionUtils.isNotEmpty(pagingData.getData())) {
			List<AdminAccountBean> accounts = pagingData.getData();
			for (AdminAccountBean account : accounts) {
				account.setPwd("");
			}
		}
		return new ResponsePagingDataVO(pagingData.getTotal(), pagingData
				.getData().size(), pagingData.getData());
	}
}
