package com.yt.business.service;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;
import com.yt.business.common.Constants.GroupRole;

import java.util.List;

public interface IService {
    /**
     * 保存服务预订
     * @param service
     * @param userId
     * @throws Exception
     */
    public void saveService(RouteServiceBean service,  Long userId) throws Exception;

    /**
     * 取消服务预订
     * @param routeId
     * @param serviceId
     * @param userId
     * @throws Exception
     */
    public void deleteService(Long routeId, Long serviceId, Long userId) throws Exception;

    /**
     * 获取行程预订的服务
     * @param routeId
     * @return
     * @throws Exception
     */
    public List<RouteServiceBean> getRouteServices(Long routeId) throws Exception;

    /**
     *
     * @param expertId
     * @return
     * @throws Exception
     */
    public List<ExpertServiceBean> getExpertServices(Long expertId) throws Exception;
}
