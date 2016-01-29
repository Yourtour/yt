package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.RouteChargeBean;
import com.yt.business.common.Constants;
import com.yt.business.neo4j.repository.ChargeTuple;
import com.yt.business.neo4j.repository.RouteChargeBeanRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/1/27.
 */
@Component
public class RouteChargeRepositoryImpl extends CrudAllInOneOperateImpl implements RouteChargeRepository {
    private static final Log logger = LogFactory.getLog(RouteChargeRepositoryImpl.class);

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
