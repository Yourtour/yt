package com.yt.business.service.impl;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.RouteChargeBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.ChargeTuple;
import com.yt.business.repository.neo4j.RouteChargeBeanRepository;
import com.yt.business.service.IRouteChargeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林平 on 2016/1/27.
 */
@Component
public class RouteChargeServiceImpl extends CrudAllInOneOperateImpl implements IRouteChargeService {
    private static final Log logger = LogFactory.getLog(RouteChargeServiceImpl.class);

    @Autowired
    private RouteChargeBeanRepository chargeRepository;

    @Override
    public List<RouteChargeBean> getCharges(Long routeId, Long userId) throws Exception{
        return chargeRepository.getCharges(routeId, userId);
    }

    @Override
    public List<RouteChargeBean> getChargeDivisions(Long chargeId) throws Exception {
        List<RouteChargeBean> charges = new ArrayList<>();

        List<ChargeTuple> tuples = chargeRepository.getChargeDivisions(chargeId);
        if(tuples == null) return charges;

        for(ChargeTuple tuple : tuples){
            charges.add(tuple.getCharge());
        }

        return charges;
    }

    @Override
    public void deleteCharge(Long chargeId, Long userId) throws Exception{
        RouteChargeBean charge = (RouteChargeBean) super.get(RouteChargeBean.class, chargeId, false);
        if(charge != null){
            charge.setStatus(Constants.Status.CANCELED);
            this.save(charge, false, userId.toString());
        }else{
            logger.warn(String.format("No charge found for id=%s", chargeId));
        }
    }
}
