package com.yt.oms.vo;

import com.yt.business.bean.DictBean;

/**
 * Created by 林平 on 2016/3/22.
 */
public class DictVO {
    private Long id;
    private String type;
    private String name;
    private String code;
    private String value;

    public DictVO() {
    }

    public static DictBean transform(DictVO vo){
        if(vo == null) return null;

        DictBean bean = new DictBean();
        bean.setId(vo.getId());
        bean.setCode(vo.getCode());
        bean.setName(vo.getName());
        bean.setValue(vo.getValue());
        bean.setType(DictBean.Type.valueOf(vo.getType()));

        return bean;
    }

    public static DictVO transform(DictBean bean){
        if(bean == null) return null;

        DictVO vo = new DictVO();
        vo.setId(bean.getId());
        vo.setCode(bean.getCode());
        vo.setName(bean.getName());
        vo.setValue(bean.getValue());
        vo.setType(bean.getType().name);

        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
