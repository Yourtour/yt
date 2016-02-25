package com.yt.rest.resource;

import com.yt.business.repository.PlaceRepository;
import com.yt.response.ResponseDataVO;
import com.yt.vo.place.CommunityVO;
import com.yt.vo.place.ThemeVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by 林平 on 2016/2/25.
 */

@Component
@Path("/community")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommunityRestResource extends BaseRestResource {
    private static final Log LOG = LogFactory.getLog(CommunityRestResource.class);

    private @Autowired
    PlaceRepository repository;

    /**
     * 获取目的地社区首页数据
     * @return
     */
    @GET
    @Path("/place/{placeId}")
    public ResponseDataVO<CommunityVO> queryMainData(@PathParam("placeId") Long placeId){
        return null;
    }

    /**
     * 获取目的地社区下某个分类数据
     * @return
     */
    @GET
    @Path("/place/{placeId}/type/{type}")
    public ResponseDataVO<List<ThemeVO>> queryThemesByType(@PathParam("placeId") Long placeId, @PathParam("type") String type, @QueryParam("start") Long nextCursor, @QueryParam("limit") int limit){
        return null;
    }

    /**
     * 获取目的地社区下按照热门程度的分页数据
     * @return
     */
    @GET
    @Path("/hot/place/{placeId}")
    public ResponseDataVO<List<ThemeVO>> queryThemesByHot(@PathParam("placeId") Long placeId, @QueryParam("start") Long nextCursor, @QueryParam("limit") int limit){
        return null;
    }

    /**
     * 获取目的地社区下按照热门程度的分页数据
     * @return
     */
    @GET
    @Path("/time/place/{placeId}")
    public ResponseDataVO<List<ThemeVO>> queryThemesByTime(@PathParam("placeId") Long placeId, @QueryParam("start") Long nextCursor, @QueryParam("limit") int limit){
        return null;
    }

    /**
     * 保存社区主题
     * @return
     */
    @GET
    @Path("/theme/save")
    public ResponseDataVO<Long> saveTheme(ThemeVO vo){
        return null;
    }
}
