package com.yt.business.service;

import java.util.Map;

/**
 * 获取页面初始化数据
 * Created by 林平 on 2016/3/22.
 */
public interface IViewService {
    /**
     *
     * @param viewname:页面名称
     * @return
     * @throws Exception
     */
    public Map<String, Object> getValue(String viewname) throws Exception;
}
