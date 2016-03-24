package com.yt.business.service;

import com.yt.business.PagingDataBean;
import com.yt.business.bean.AdminAccountBean;
import com.yt.business.bean.BannerBean;

import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/24.
 */
public interface IAdminAccountService {
    /**
     * 账号注册
     * @param account
     * @throws Exception
     */
    public void saveAccountInfo(AdminAccountBean account, Long userId) throws Exception;

    /**
     * 登录鉴权
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public AdminAccountBean authenticate(String userName, String password) throws Exception;

    /**
     * 删除账户
     * @param userName
     * @param userId
     * @throws Exception
     */
    public void deleteAccountInfo(String userName, Long userId) throws Exception;


    public PagingDataBean<List<AdminAccountBean>> getAccountInfoes(Long nextCursor, int limit, int total, Map<String, Object> params) throws Exception;
}
