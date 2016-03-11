package com.yt.business.service;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.HomeBean;
import com.yt.business.bean.LaunchBean;

import java.util.List;

/**
 * Created by 林平 on 2016/3/8.
 */
public interface IBaseService {
    /**
     * 客户端启动服务接口
     * @param accessId
     * @param lastAccessTime
     * @param version
     * @return
     * @throws Exception
     */
    public LaunchBean launch(String accessId, long lastAccessTime, String version) throws Exception;

    /**
     * 获取首页信息
     * @param userId
     * @param lastAccessTime
     * @return
     * @throws Exception
     */
    public HomeBean get(Long userId, long lastAccessTime) throws Exception;
}