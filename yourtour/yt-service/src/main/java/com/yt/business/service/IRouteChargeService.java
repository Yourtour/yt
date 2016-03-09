package com.yt.business.service;

import com.yt.business.bean.RouteChargeBean;

import java.util.List;

public interface IRouteChargeService {
    /**
     * 保存下行程费用
     * @param charge
     * @throws Exception
     */
    public void saveCharge(RouteChargeBean charge, Long operatorId) throws Exception;

    /**
     * 删除行程费用
     * @param routeId
     * @param chargeId
     * @param operatorId
     * @throws Exception
     */
    public void deleteCharge(Long routeId, Long chargeId, Long operatorId) throws Exception;

    /**
     * 获取用户某条行程费用清单
     * @param routeId
     * @param operatorId
     * @return
     * @throws Exception
     */
    public List<RouteChargeBean> getCharges(Long routeId, Long operatorId) throws Exception;

    /**
     * 获取费用平摊
     * @param chargeId
     * @return
     * @throws Exception
     */
    public List<RouteChargeBean> getChargeDivisions(Long routeId, Long chargeId) throws Exception;

    /**
     * 保存费用平摊
     * @param masterChargeId
     * @param charge
     * @return
     * @throws Exception
     */
    public void saveChargeDivisions(Long routeId, Long masterChargeId, RouteChargeBean charge, Long operatorId) throws Exception;

    /**
     * 删除费用平台
     * @param chargeId
     * @return
     * @throws Exception
     */
    public void deleteChargeDivisions(Long routeId, Long chargeId, Long operatorId) throws Exception;


}
