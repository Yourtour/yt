package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by 林平 on 2016/2/24.
 */

@HbaseTable(name = "T_ROUTE_RESERVATION")
@NodeEntity
public class RouteReservationBean extends BaseBeanImpl {
    public static final String RELATION_TYPE_RESERVING = "RESERVING";
    public static final String RELATION_TYPE_FOR = "FOR";

    private String generalRequirement; //总体要求
    private String sceneRequirement; //景点要求
    private String hotelRequirement; //住宿要求
    private String foodRequirement;  //餐饮要求
    private String trafficRequirement;  //交通要求
    private String otherRequirement;  //其他要求

    @Neo4jRelationship(relationship = RELATION_TYPE_RESERVING, type = UserProfileBean.class, direction = Direction.OUTGOING, isList = true)
    private UserProfileBean user;  //预约达人

    @Neo4jRelationship(relationship = RELATION_TYPE_FOR, type = RouteMainBean.class, direction = Direction.OUTGOING)
    private RouteMainBean  route;

    public RouteReservationBean(){

    }

    public String getGeneralRequirement() {
        return generalRequirement;
    }

    public void setGeneralRequirement(String generalRequirement) {
        this.generalRequirement = generalRequirement;
    }

    public String getSceneRequirement() {
        return sceneRequirement;
    }

    public void setSceneRequirement(String sceneRequirement) {
        this.sceneRequirement = sceneRequirement;
    }

    public String getHotelRequirement() {
        return hotelRequirement;
    }

    public void setHotelRequirement(String hotelRequirement) {
        this.hotelRequirement = hotelRequirement;
    }

    public String getFoodRequirement() {
        return foodRequirement;
    }

    public void setFoodRequirement(String foodRequirement) {
        this.foodRequirement = foodRequirement;
    }

    public String getTrafficRequirement() {
        return trafficRequirement;
    }

    public void setTrafficRequirement(String trafficRequirement) {
        this.trafficRequirement = trafficRequirement;
    }

    public String getOtherRequirement() {
        return otherRequirement;
    }

    public void setOtherRequirement(String otherRequirement) {
        this.otherRequirement = otherRequirement;
    }

    public UserProfileBean getUser() {
        return user;
    }

    public void setUser(UserProfileBean user) {
        this.user = user;
    }

    public RouteMainBean getRoute() {
        return route;
    }

    public void setRoute(RouteMainBean route) {
        this.route = route;
    }
}
