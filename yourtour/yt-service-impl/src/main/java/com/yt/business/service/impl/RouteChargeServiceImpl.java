package com.yt.business.service.impl;

import com.yt.business.bean.RouteChargeBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.ChargeTuple;
import com.yt.business.repository.neo4j.RouteRepository;
import com.yt.business.service.IRouteChargeService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
import com.yt.core.utils.CollectionUtils;
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
public class RouteChargeServiceImpl extends ServiceBase implements IRouteChargeService {
    private static final Log logger = LogFactory.getLog(RouteChargeServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private CrudOperate<RouteChargeBean> chargeCrudOperate;

    @Autowired
    private RelationshipCrudOperate<RouteChargeBean, RouteChargeBean> chargeDivisionRelationship;

    @Override
    public List<RouteChargeBean> getCharges(Long routeId, Long operatorId) throws Exception{
        List<RouteChargeBean> charges = routeRepository.getCharges(routeId, operatorId);
        if(CollectionUtils.isNotEmpty(charges)){
            List<ChargeTuple> tuples = null;
            for(RouteChargeBean charge : charges) {
                if(charge.getAmount() == charge.getPayment()) continue;

                tuples = routeRepository.getChargeDivisions(charge.getId());
                if (tuples == null) return charges;

                List<RouteChargeBean> divisions = new ArrayList<>();
                for (ChargeTuple tuple : tuples) {
                    divisions.add(tuple.getCharge());
                }

                charge.setDivision(divisions);
            }
        }

        return charges;
    }

    @Override
    public void deleteCharge(Long routeId, Long chargeId, Long operatorId) throws Exception{
        RouteChargeBean charge = chargeCrudOperate.get(chargeId, false);
        if(charge != null){
            charge.setStatus(Constants.Status.CANCELED);
            chargeCrudOperate.save(charge, false);
        }else{
            logger.warn(String.format("No charge found for id=%s", chargeId));
        }
    }

    public void saveCharge(RouteChargeBean charge, Long operatorId) throws Exception {
        chargeCrudOperate.save(charge);
    }

    @Override
    public void saveChargeDivisions(Long routeId, Long masterChargeId, RouteChargeBean charge, Long operatorId) throws Exception {
        RouteChargeBean master = chargeCrudOperate.get(masterChargeId);
        if(master == null){
            throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
        }
        master.setPayment(master.getPayment() - charge.getAmount());
        chargeCrudOperate.save(master);

        chargeCrudOperate.save(charge, true);

        //平摊费用和被平摊费用之间建立关系
        chargeDivisionRelationship.createRelation(master, charge, Constants.RELATION_TYPE_DIVIDED, Direction.OUTGOING);
    }

    @Override
    public void deleteChargeDivision(Long routeId, Long masterChargeId, Long chargeId, Long operatorId) throws Exception {
        RouteChargeBean master = chargeCrudOperate.get(masterChargeId);
        if(master == null){
            throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
        }

        RouteChargeBean charge = chargeCrudOperate.get(chargeId);
        if(charge == null){
            throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
        }
        chargeCrudOperate.delete(charge);

        master.setPayment(master.getPayment() + charge.getAmount()); //被平摊费用实际支付增加平摊的费用
        chargeCrudOperate.save(master);
    }
}
