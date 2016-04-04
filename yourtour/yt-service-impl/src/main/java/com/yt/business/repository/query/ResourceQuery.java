package com.yt.business.repository.query;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.common.Constants;
import com.yt.neo4j.repository.CrudOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceQuery  implements  IResourceQuery {
    @Autowired
    private CrudOperate<RestaurantResourceBean> restaurantOperator;

    @Autowired
    private CrudOperate<SceneResourceBean> sceneOperator;

    @Autowired
    private CrudOperate<HotelResourceBean> hotelOperator;


    @Override
    public PagingDataBean<List<? extends ResourceBean>> getResources(PagingConditionBean pagingCondition, Map<String, Object> params) throws Exception {
        StringBuffer sb = new StringBuffer();

        //按目的地检索

        if(params.containsKey("placeId")){
            sb.append("START n={placeId} MATCH n<-[:AT]-(resource:%s)");
        }else{
            sb.append("MATCH (resource:%s)");
        }

        boolean whereAppended = false;
        //按名字检索
        if(params.containsKey("name")){
            sb.append(" where resource.name like {name}");
        }

        sb.append(" return resource");

        //按类型检索
        List<? extends ResourceBean> resources = null;
        String type = params.get("type").toString().toString().toUpperCase();
        switch(ResourceBean.ResourceType.valueOf(type)){
            case FOOD:
                resources = this.restaurantOperator.query(String.format(sb.toString(),RestaurantResourceBean.class.getSimpleName()), params);
                break;
            case HOTEL:
                resources = this.hotelOperator.query(String.format(sb.toString(), HotelResourceBean.class.getSimpleName()), params);
                break;
            default:
                resources = this.sceneOperator.query(String.format(sb.toString(), SceneResourceBean.class.getSimpleName()), params);
                break;
        }

        return new PagingDataBean<List<? extends ResourceBean>>(10, resources);
    }
}