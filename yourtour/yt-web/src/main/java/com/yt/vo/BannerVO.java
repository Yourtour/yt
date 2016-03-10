package com.yt.vo;

import com.yt.business.bean.BannerBean;
import com.yt.core.utils.CollectionUtils;
import com.yt.vo.member.UserVO;
import com.yt.vo.route.RouteVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林平 on 2016/3/2.
 */
public class BannerVO implements Serializable {
    private Long id;
    private String title;
    private String imageUrl;
    private String content;
    private List<RouteVO> routes;
    private List<UserVO> users;

    public static BannerVO transform(BannerBean bean){
        BannerVO vo = new BannerVO();

        return vo;
    }

    public static List<BannerVO> transform(List<BannerBean> banners){
        if(CollectionUtils.isEmpty(banners)) return null;

        List<BannerVO> voes = new ArrayList<>();
        for(BannerBean banner : banners){
            voes.add(transform(banner));
        }

        return voes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<RouteVO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteVO> routes) {
        this.routes = routes;
    }

    public List<UserVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }
}
