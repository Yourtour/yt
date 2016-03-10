package com.yt.business.service.impl;

import org.springframework.stereotype.Service;

import com.yt.business.bean.HomeBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.service.IBaseService;

/**
 * Created by 林平 on 2016/3/10.
 */
@Service
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
