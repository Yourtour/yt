package com.yt.business.service.impl;

import com.yt.business.bean.*;
import com.yt.core.common.AppException;
import com.yt.business.common.Constants;
import com.yt.core.common.StaticErrorEnum;
import com.yt.business.repository.neo4j.ChargeTuple;
import com.yt.business.repository.neo4j.RouteChargeBeanRepository;
import com.yt.business.service.IRouteChargeService;
import com.yt.neo4j.repository.CrudOperate;
import com.yt.neo4j.repository.RelationshipCrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林平 on 2016/1/27.
 */
@Service
public class RouteChargeServiceImpl extends BaseServiceImpl implements IRouteChargeService {
    private static final Log logger = LogFactory.getLog(RouteChargeServiceImpl.class);

    @Autowired
    private RouteChargeBeanRepository chargeRepository;

    @Autowired
    private CrudOperate<RouteChargeBean> chargeCrudOperate;

    @Autowired
    private RelationshipCrudOperate<RouteChargeBean, RouteChargeBean> chargeDivisionRelationship;


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
        RouteChargeBean charge = chargeCrudOperate.get(chargeId, false);
        if(charge != null){
            charge.setStatus(Constants.Status.CANCELED);
            chargeCrudOperate.save(charge, false);
        }else{
            logger.warn(String.format("No charge found for id=%s", chargeId));
        }
    }

    public void saveCharge(RouteChargeBean charge) throws Exception {
        chargeCrudOperate.save(charge);
    }

    @Override
    public RouteChargeBean getCharge(Long chargeId) throws Exception {
        return null;
    }

    @Override
    public void saveChargeDivisions(Long masterChargeId, RouteChargeBean charge) throws Exception {
        RouteChargeBean master = chargeCrudOperate.get(masterChargeId);
        if(master == null){
            throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
        }
        master.setPayment(master.getPayment() - charge.getAmount());
        chargeCrudOperate.save(master);

        chargeCrudOperate.save(charge, true);

        chargeDivisionRelationship.createRelation(master, charge, Constants.RELATION_TYPE_DIVIDED, Direction.OUTGOING);
    }

    @Override
    public void deleteChargeDivisions(Long chargeId) throws Exception {

    }
}
