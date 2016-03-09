package com.yt.business.service;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.ResourceBean;
import com.yt.business.common.Constants;

import java.util.List;

public interface IResourceService {
    /**
     * 保存资源信息
     * @param resource
     * @param userId
     * @throws Exception
     */
    public void saveResource(ResourceBean resource, Constants.ResType resType, Long userId) throws Exception;

    /**
     * 获取资源信息
     * @param resourceId
     * @return
     * @throws Exception
     */
    public ResourceBean getResource(Long resourceId, Constants.ResType resType) throws Exception;

    /**
     * 删除资源信息
     * @param resourceId
     * @param userId
     * @throws Exception
     */
    public void deleteResource(Long resourceId, Constants.ResType resType, Long userId) throws Exception;

    /**
     * 获取目的地资源
     * @param placeId
     * @param nextCursor
     * @param limit
     * @param resType
     * @return
     * @throws Exception
     */
    public List<? extends ResourceBean> getResources(Long placeId, Long nextCursor, int limit, Constants.ResType resType) throws Exception;
}
