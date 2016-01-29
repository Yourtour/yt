package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteChargeBean;

import java.util.List;

public interface RouteChargeRepository extends CrudAllInOneOperate {
    /**
     *
     * @param routeId
     * @param userId
     * @return
     */
    public List<RouteChargeBean> getCharges(Long routeId, Long userId) throws Exception;

    /**
     *
     * @param chargeId
     * @return
     * @throws Exception
     */
    public List<RouteChargeBean> getChargeDivisions(Long chargeId) throws Exception;

    /**
     *
     * @param chargeId
     * @param userId
     */
    public void deleteCharge(Long chargeId, Long userId) throws Exception;
}
