package com.yt.business.service.impl;

import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.AppException;
import com.yt.business.common.StaticErrorEnum;
import com.yt.business.service.IUserService;
import com.yt.business.repository.neo4j.UserTuple;
import com.yt.business.service.IUserServiceFacade;
import com.yt.core.utils.Base64Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 和用户相关的服务接口
 * Created by 林平 on 2016/3/7.
 */
@Service
public class UserServiceImpl implements IUserServiceFacade {
    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private IUserService IUserService;

    @Override
    public UserProfileBean getUserProfileInfo(Long userId) throws Exception {
        UserProfileBean user = (UserProfileBean) IUserService.get(UserProfileBean.class, userId);
        if(user == null){
            LOG.warn(String.format("No UserProfileBean found for ID=%d", userId));
        }

        return user;
    }

    @Override
    public UserProfileBean login(String userName, String password) throws Exception {
        UserTuple tuple = IUserService.getUserByUserName(userName);
        if(tuple == null){
            throw new AppException(StaticErrorEnum.USER_NOT_EXIST);
        }

        String rPassword = tuple.getAccount().getPwd();
        if(! rPassword.equals(Base64Utils.MD5(password.trim()))){
            throw new AppException(StaticErrorEnum.AUTHENTICATE_FAIL);
        }

        return tuple.getProfile();
    }

    @Override
    public void logout(Long userId) throws Exception {

    }

    @Override
    public UserProfileBean saveUseAccount(UserAccountBean account) throws Exception {
        UserTuple tuple = IUserService.getUserByUserName(account.getUserName());
        if(tuple == null){
            throw new AppException(StaticErrorEnum.USER_MOBILE_EXIST);
        }
        account.setPwd(Base64Utils.MD5(account.getPwd()));

        UserProfileBean profile = new UserProfileBean();
        profile.setMobileNo(account.getUserName());
        account.setProfile(profile);

        this.IUserService.save(account);

        return account.getProfile();
    }

    @Override
    public UserProfileBean saveUseProfile(UserProfileBean profile) throws Exception {
        UserProfileBean existed = IUserService.getUserByNickName(profile.getNickName());
        if(existed != null){
            throw new AppException(StaticErrorEnum.NICKNAME_EXIST);
        }

        this.IUserService.save(profile, false);

        return (UserProfileBean) IUserService.get(UserProfileBean.class, profile.getId(), false);
    }
}
