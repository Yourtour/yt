package com.yt.business.service;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;
import com.yt.business.common.Constants.GroupRole;

import java.util.List;

public interface IService {
    /**
     * 保存达人提供的服务
     * @param service
     * @param userId
     * @throws Exception
     */
    public void saveExpertService(ExpertServiceBean service, Long userId) throws Exception;

    /**
     * 删除达人服务
     * @param serviceId
     * @param userId
     * @throws Exception
     */
    public void deleteExpertService(Long serviceId, Long userId) throws Exception;

    /**
     * 获取达人服务
     * @param serviceId
     * @throws Exception
     */
    public ExpertServiceBean getExpertService(Long serviceId) throws Exception;

    /**
     * 查询达人提供的服务
     * @param uid
     * @return
     * @throws Exception
     */
    public List<ExpertServiceBean> getExpertServices(Long uid) throws Exception;

    /**
     * 保存服务预订
     * @param service
     * @param userId
     * @throws Exception
     */
    public void saveRouteService(RouteServiceBean service,  Long userId) throws Exception;

    /**
     * 取消服务预订
     * @param routeId
     * @param serviceId
     * @param userId
     * @throws Exception
     */
    public void deleteRouteService(Long routeId, Long serviceId, Long userId) throws Exception;

    /**
     * 获取行程预订的服务
     * @param routeId
     * @return
     * @throws Exception
     */
    public List<RouteServiceBean> getRouteServices(Long routeId) throws Exception;
}
