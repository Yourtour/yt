package com.yt.business.service;

import com.yt.business.FavoriteBaseBean;
import com.yt.business.bean.CommentBean;

import java.util.List;

/**
 * Created by 林平 on 2016/3/7.
 */
public interface IFavoriteService {
    /**
     * 获取分页点评数据
     * @param id
     * @param nextCursor
     * @param step
     * @param filter
     * @return
     * @throws Exception
     */
    public List<CommentBean> getComments(Long id, Long nextCursor, int step, String filter) throws Exception;

    /**
     * 保存点评数据
     * @param subjectId
     * @param parentId
     * @param comment
     * @return
     * @throws Exception
     */
    public CommentBean saveComment(Long subjectId, Long parentId, CommentBean comment) throws Exception;

    /**
     * 删除点评数据
     * @param commentId
     * @throws Exception
     */
    public void deleteComment(Long commentId, Long userId) throws Exception;

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
    public List<FavoriteBaseBean> getFavorites(Long userId, Long nextCursor, int limit) throws Exception;

    /**
     * 保存用户点赞
     * @param subjectId
     * @param userId
     * @throws Exception
     */
    public void saveLike(Long subjectId, Long userId) throws Exception;

    /**
     * 将某一资源、达人或者服务保存到旅行车
     * @param subjectId
     * @param userId
     * @throws Exception
     */
    public void saveTravelCart(Long subjectId, Long userId) throws Exception;

    /**
     * 将某一资源、达人或者服务从旅行车取消
     * @param subjectId
     * @param userId
     * @throws Exception
     */
    public void deleteTravelCart(Long subjectId, Long userId) throws Exception;

    /**
     * 清除旅行车
     * @param userId
     * @throws Exception
     */
    public void clearTravelCart(Long userId) throws Exception;

}
