package com.yt.business.service.impl;

import com.yt.business.bean.DictBean;
import com.yt.business.repository.neo4j.DictBeanRepository;
import com.yt.business.service.IViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/22.
 */
@Service
public class ViewServiceImpl  extends ServiceBase implements IViewService {
    @Autowired
    private DictBeanRepository dictRepository;

    @Override
    public Map<String, Object> getValue(String viewname) throws Exception {
        Map<String, Object> values = new HashMap<>();

        if(viewname.equalsIgnoreCase("Dictionary")){
            values.put("types", getDictType());
        }else if(viewname.equalsIgnoreCase("schedule")){
            values.put("routeTags", this.getDictInfoes(DictBean.Type.ROUTE_TAGS.toString()));
        }

        return values;
    }

    private List<DictBean> getDictType(){
        List<DictBean> beans = new ArrayList();

        for(DictBean.Type type : DictBean.Type.values()){
            DictBean bean = new DictBean();
            bean.setCode(type.code);
            bean.setName(type.name);

            beans.add(bean);
        }

        return beans;
    }

    private List<DictBean> getDictInfoes(String type){
        return dictRepository.getDictInfoes(type);
    }
}
