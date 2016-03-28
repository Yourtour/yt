package com.yt.vo;

import java.io.Serializable;

/**
 * Created by 林平 on 2016/3/26.
 */
public class UserVO implements Serializable {
    private Long userId;
    private String name;

    public UserVO(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
