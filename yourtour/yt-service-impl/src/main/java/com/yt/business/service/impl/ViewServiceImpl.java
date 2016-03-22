package com.yt.business.service.impl;

import com.yt.business.service.IViewService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/22.
 */
@Service
public class ViewServiceImpl  extends ServiceBase implements IViewService {
    @Override
    public Map<String, Object> getValue(String name) throws Exception {
        Map<String, Object> values = new HashMap();

        return values;
    }
}
