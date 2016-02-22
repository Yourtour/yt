package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.ResourceBean;

import java.util.List;

public interface ResourceRepository extends CrudAllInOneOperate {
    /**
     * 根据目的地检索附近资源
     * @param placeId
     * @param nextCursor
     * @param limit
     * @param distance
     * @return
     * @throws Exception
     */
    public List<ResourceBean> getResources(Long placeId, Long nextCursor, int limit, int distance) throws Exception;
}
