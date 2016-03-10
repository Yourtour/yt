package com.yt.business.service.impl;

import com.yt.business.FavoriteBaseBean;
import com.yt.business.bean.FavoriteBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.core.common.AppException;
import com.yt.business.common.Constants;
import com.yt.core.common.StaticErrorEnum;
import com.yt.business.repository.neo4j.FavoriteBeanRepository;
import com.yt.business.repository.neo4j.FavoriteTuple;
import com.yt.business.service.IFavoriteService;
import com.yt.core.utils.CollectionUtils;
import com.yt.neo4j.repository.CrudOperate;
import com.yt.neo4j.repository.RelationshipCrudOperate;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林平 on 2016/3/8.
 */
@Service
public class FavoriteServiceImpl extends ServiceBase implements IFavoriteService {
    @Autowired
    private FavoriteBeanRepository favoriteRepository;

    @Autowired
    private CrudOperate<FavoriteBaseBean> entityCrudOperate;

    @Autowired
    private CrudOperate<UserProfileBean> userCrudOperate;

    @Autowired
    private RelationshipCrudOperate<UserProfileBean, FavoriteBaseBean> userFavoriteRelationship;

    public FavoriteServiceImpl(){}

    @Override
    public void saveFavorite(Long subjectId, Long userId) throws Exception {
        FavoriteBaseBean bean = entityCrudOperate.get(subjectId);
        if(bean == null){
            throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
        }

        UserProfileBean user = userCrudOperate.get(userId);
        if(user == null){
            throw new AppException(StaticErrorEnum.USER_NOT_EXIST);
        }

        //保存用户和实体之间的收藏关系
        this.userFavoriteRelationship.createRelation(user, bean, Constants.RELATION_TYPE_FAVORITE, Direction.OUTGOING);
    }

    @Override
    public void deleteFavorite(Long subjectId, Long userId) throws Exception {
        FavoriteBaseBean bean = entityCrudOperate.get(subjectId);
        if(bean == null){
            throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
        }

        UserProfileBean user = userCrudOperate.get(userId);
        if(user == null){
            throw new AppException(StaticErrorEnum.USER_NOT_EXIST);
        }

        //删除用户和实体之间的收藏关系
        this.userFavoriteRelationship.deleteRelation(user, bean, Constants.RELATION_TYPE_FAVORITE);
    }

    @Override
    public List<FavoriteBean> getFavorites(Long userId, Long nextCursor, int limit) throws Exception {
        List<FavoriteBean> favorites = new ArrayList<>();

        /*List<FavoriteTuple> tuples = favoriteRepository.getFavorites(userId, nextCursor, limit);
        if(CollectionUtils.isNotEmpty(tuples)){
            for(FavoriteTuple tuple : tuples){
                favorites.add(tuple.getFavorite());
            }
        }*/

        return favorites;
    }
}
