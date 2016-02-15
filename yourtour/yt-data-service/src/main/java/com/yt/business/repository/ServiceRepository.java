package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;
import com.yt.business.common.Constants.GroupRole;

import java.util.List;

public interface ServiceRepository extends CrudAllInOneOperate {
    /**
     *
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
