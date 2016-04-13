package com.yt.business.repository.query;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.ResourceBean;

import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/31.
 */
public interface IResourceQuery {
    /**
     *
     * @param pagingCondition
     * @param params
     * @return
     * @throws Exception
     */
    public PagingDataBean<List<ResourceBean>> getResources(PagingConditionBean pagingCondition, Map<String, Object> params) throws Exception;
}
