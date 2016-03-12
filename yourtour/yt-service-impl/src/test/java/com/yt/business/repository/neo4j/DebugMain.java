package com.yt.business.repository.neo4j;

import com.yt.business.bean.RouteMainBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 林平 on 2016/3/10.
 */
public class DebugMain {
    public DebugMain(){}

    public static void main(String[] args) throws Exception{
        IText<RouteMainBean> debugMain = new Text();

        Type genType = debugMain.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //System.out.println(params[0].getTypeName());
    }
}
