package com.yt.business.service.impl;

import com.yt.business.BaseBeanImpl;
import com.yt.core.utils.DateUtils;

/**
 * Created by 林平 on 2016/3/8.
 */
public class ServiceBase {
    protected void updateBaseInfo(BaseBeanImpl bean, Long operatorId){
        if(bean.isNew()){
            bean.setCreatedTime(DateUtils.getCurrentTimeMillis());
            bean.setCreatedUserId(operatorId);
        }

        bean.setUpdatedTime(DateUtils.getCurrentTimeMillis());
        bean.setUpdatedUserId(operatorId);
    }
}
