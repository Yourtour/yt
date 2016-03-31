package com.yt.business.repository.query;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.ResourceBean;
import com.yt.neo4j.repository.CrudOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceQuery  implements  IResourceQuery {
    @Autowired
    private CrudOperate<ResourceBean> operator;

    @Override
    public PagingDataBean<List<ResourceBean>> getResources(PagingConditionBean pagingCondition, Map<String, Object> params) throws Exception {
        StringBuffer sb = new StringBuffer();

        Map<String, Object> queryParams = new HashMap<>();

        //按目的地检索
        if(params.containsKey("placeId")){
            sb.append("START n=:placeId MATCH n<-[:AT]-(resource:ResourceBean)");
        }else{
            sb.append("MATCH (resource:ResourceBean)");
        }

        boolean whereAppended = false;
        //按名字检索
        if(params.containsKey("name")){
            sb.append(" where resource.name like ':name'");

            whereAppended = true;
        }

        //按类型检索
        if(params.containsKey("type")){
            if(! whereAppended){
                sb.append(" where ");
            }else{
                sb.append(" and resource.type = :type");
            }
        }

        List<ResourceBean> resources = operator.query(sb.toString(), queryParams);

        return new PagingDataBean<List<ResourceBean>>(10, resources);
    }
}