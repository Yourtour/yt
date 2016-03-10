package com.yt.business.service.impl;

import com.yt.business.bean.HomeBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.service.IBaseService;

/**
 * Created by 林平 on 2016/3/10.
 */
public class BaseServiceImpl extends ServiceBase implements IBaseService {

    public BaseServiceImpl(){}

    @Override
    public LaunchBean launch(String accessId, long lastAccessTime, String version) throws Exception {
        return null;
    }

    @Override
    public HomeBean get(Long userId, long lastAccessTime) throws Exception {
        return null;
    }
}
