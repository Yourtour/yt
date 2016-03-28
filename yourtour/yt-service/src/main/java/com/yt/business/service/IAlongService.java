package com.yt.business.service;

import com.yt.business.bean.AlongBean;

import java.util.List;

/**
 *
 * Created by 林平 on 2016/3/8.
 */
public interface IAlongService {
    /**
     * 保存结伴信息
     * @param along
     * @throws Exception
     */
    public void saveAlongInfo(AlongBean along) throws Exception;

    /**
     * 获取结伴信息
     * @param alongId
     * @return
     * @throws Exception
     */
    public AlongBean getAlongInfo(Long alongId) throws Exception;

    /**
     * 删除结伴信息
     * @param alongId
     * @param userId
     * @throws Exception
     */
    public void deleteAlongInfo(Long alongId, Long userId) throws Exception;

    /**
     * 获取指定目的地的结伴信息
     * @param placeId
     * @param startIndex
     * @param limit
     * @return
     * @throws Exception
     */
    public List<AlongBean> getAlongsByPlace(Long placeId, Long startIndex, int limit) throws Exception;

    /**
     * 获取指定行程的结伴信息
     * @param routeId
     * @return
     * @throws Exception
     */
    public List<AlongBean> getAlongsByRoute(Long routeId) throws Exception;
}
