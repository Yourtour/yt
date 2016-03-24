package com.yt.oms.resource;

import com.yt.business.bean.AdminAccountBean;
import com.yt.business.service.IAdminAccountService;
import com.yt.core.utils.CollectionUtils;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.rest.resource.RestResource;
import com.yt.utils.SessionUtils;
import com.yt.vo.PaginationVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by 林平 on 2016/3/24.
 */
@Component
@Path("admin/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserRestResource  extends RestResource {
    private static final Log LOG = LogFactory.getLog(UserRestResource.class);

    @Autowired
    private IAdminAccountService service;

    /**
     * 创建账号
     * @param account
     * @return
     * @throws Exception
     */
    @Path("/save")
    @POST
    public ResponseVO saveAccountInfo(AdminAccountBean account) throws Exception{
        this.service.saveAccountInfo(account, SessionUtils.getCurrentLoginUser());

        return new ResponseVO();
    }

    /**
     * 登录验证
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    @Path("/authenticate")
    @POST
    public ResponseDataVO<AdminAccountBean> authenticateAccountInfo(@QueryParam("userName") String userName, @QueryParam("password") String password) throws Exception{
        AdminAccountBean account = this.service.authenticate(userName, password);
        account.setPwd("");
        return new ResponseDataVO(account);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Path("/query")
    @POST
    public ResponseDataVO<List<AdminAccountBean>> getAccountInfoes(PaginationVO pagination) throws Exception{
        List<AdminAccountBean> accounts = this.service.getAccountInfoes();
        if(CollectionUtils.isNotEmpty(accounts)){
            for(AdminAccountBean account : accounts){
                account.setPwd("");
            }
        }
        return new ResponseDataVO(accounts);
    }
}
