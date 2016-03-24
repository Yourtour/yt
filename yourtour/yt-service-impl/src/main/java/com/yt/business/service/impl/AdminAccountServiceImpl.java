package com.yt.business.service.impl;

import com.yt.PropertiesReader;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.AdminAccountBean;
import com.yt.business.repository.neo4j.AdminAccountBeanRepository;
import com.yt.business.service.IAdminAccountService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
import com.yt.core.utils.Base64Utils;
import com.yt.core.utils.MessageDigestUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/24.
 */
@Service
public class AdminAccountServiceImpl extends ServiceBase implements IAdminAccountService {
    private static final Log LOG = LogFactory.getLog(AdminAccountServiceImpl.class);

    @Autowired
    private AdminAccountBeanRepository repository;

    @Autowired
    private CrudOperate<AdminAccountBean> accountCrudOperate;

    @Autowired
    private PropertiesReader propertiesReader;

    public AdminAccountServiceImpl(){
    }

    @Override
    public void saveAccountInfo(AdminAccountBean account, Long userId) throws Exception {
        AdminAccountBean existed = repository.getAccountByUserName(account.getUserName());
        if (existed != null) {
            throw new AppException(StaticErrorEnum.USER_EXIST);
        }

        account.setPwd(MessageDigestUtils.digest(
                propertiesReader.getProperty("digest.algorithm", "SHA-1"),
                account.getPwd()));

        super.updateBaseInfo(account, userId);
        this.accountCrudOperate.save(account);
    }

    @Override
    public AdminAccountBean authenticate(String userName, String password) throws Exception {
        AdminAccountBean account = repository.getAccountByUserName(userName);
        if (account == null) {
            throw new AppException(StaticErrorEnum.USER_NOT_EXIST);
        }

        String rPassword = account.getPwd();
        if (!rPassword.equals(MessageDigestUtils.digest(propertiesReader.getProperty("digest.algorithm", "SHA-1"),password))) {
            throw new AppException(StaticErrorEnum.AUTHENTICATE_FAIL);
        }

        return account;
    }

    @Override
    public void deleteAccountInfo(String userName, Long userId) throws Exception {

    }

    public PagingDataBean<List<AdminAccountBean>> getAccountInfoes(Long nextCursor, int limit, int total, Map<String, Object> params) throws Exception{
        PagingDataBean<List<AdminAccountBean>> pagination = new PagingDataBean<>();
        pagination.setData(accountCrudOperate.get());

        return pagination;
    }


}
