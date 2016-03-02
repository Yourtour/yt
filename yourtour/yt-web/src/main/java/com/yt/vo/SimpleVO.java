package com.yt.vo;

import java.io.Serializable;

/**
 * Created by 林平 on 2016/3/2.
 */
public class SimpleVO implements Serializable {
    private Long id;
    private String code;
    private String name;

    public SimpleVO(){}

    public SimpleVO(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
