package com.yt.business.service;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteChargeBean;

import java.util.List;

public interface IRouteChargeService {
    /**
     * 保存下行程费用
     * @param charge
     * @throws Exception
     */
    public void saveCharge(RouteChargeBean charge) throws Exception;

    /**
     * 删除行程费用
     * @param chargeId
     * @param userId
     */
    public void deleteCharge(Long chargeId, Long userId) throws Exception;

    /**
     * 获取行程费用
     * @param chargeId
     * @return
     * @throws Exception
     */
    public RouteChargeBean getCharge(Long chargeId) throws Exception;

    /**
     * 获取用户某条行程费用清单
     * @param routeId
     * @param userId
     * @return
     */
    public List<RouteChargeBean> getCharges(Long routeId, Long userId) throws Exception;

    /**
     * 获取费用平摊
     * @param chargeId
     * @return
     * @throws Exception
     */
    public List<RouteChargeBean> getChargeDivisions(Long chargeId) throws Exception;

    /**
     * 保存费用平摊
     * @param masterChargeId
     * @param charge
     * @return
     * @throws Exception
     */
    public void saveChargeDivisions(Long masterChargeId, RouteChargeBean charge) throws Exception;

    /**
     * 删除费用平台
     * @param chargeId
     * @return
     * @throws Exception
     */
    public void deleteChargeDivisions(Long chargeId) throws Exception;


}
