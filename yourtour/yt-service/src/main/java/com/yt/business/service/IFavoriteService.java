package com.yt.business.service;

import com.yt.business.FavoriteBaseBean;
import com.yt.business.bean.CommentBean;
import com.yt.business.bean.FavoriteBean;

import java.util.List;

/**
 * Created by 林平 on 2016/3/7.
 */
public interface IFavoriteService {
    /**
     * 保存用户收藏
     * @param subjectId
     * @param userId
     * @throws Exception
     */
    public void saveFavorite(Long subjectId, Long userId) throws Exception;

    /**
     * 取消用户收藏
     * @param subjectId
     * @param userId
     * @throws Exception
     */
    public void deleteFavorite(Long subjectId, Long userId) throws Exception;

    /**
     * 获取用户收藏
     * @param userId
     * @return
     * @throws Exception
     */
    public List<FavoriteBean> getFavorites(Long userId, Long nextCursor, int limit) throws Exception;
}
