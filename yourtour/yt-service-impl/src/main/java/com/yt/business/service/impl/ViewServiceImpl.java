package com.yt.business.service.impl;

import com.yt.business.bean.DictBean;
import com.yt.business.service.IViewService;
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
    @Override
    public Map<String, Object> getValue(String name) throws Exception {
        if(name.equalsIgnoreCase("DictListView")){
            return this.getValue4DictListView();
        }else if(name.equalsIgnoreCase("DictFormView")){
            return getValue4DictFormView();
        }

        return null;
    }

    private Map<String, Object> getValue4DictListView(){
        Map<String, Object> values = new HashMap<>();

        values.put("types", getDictType());

        return values;
    }

    private Map<String, Object> getValue4DictFormView(){
        Map<String, Object> values = new HashMap<>();

        values.put("types", getDictType());

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
}
