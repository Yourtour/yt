package com.yt.oms.resource;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.service.IActivityService;
import com.yt.business.service.IRouteService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.activity.ActivityContentVO;
import com.yt.oms.vo.activity.ActivityVO;
import com.yt.oms.vo.route.RouteScheduleVO;
import com.yt.oms.vo.route.RouteVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.rest.resource.RestResource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 行程管理Restful接口
 * Created by 林平 on 2016/3/26.
 */
@Component
@Path("/oms/route/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteRestResource extends RestResource {
    private static final Log LOG = LogFactory.getLog(RouteRestResource.class);

    private static final String IMAGE_PATH = "/images/routes/";

    @Autowired
    private IRouteService routeService;

    /**
     * 行程查询
     * @param nextCursor
     * @param limit
     * @param total
     * @param params
     * @return
     * @throws Exception
     */
    @POST
    @Path("/recommend/query")
    public ResponseDataVO<List<RouteVO>> getRouteInfoes(  @DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
                                                          @DefaultValue("20") @QueryParam("limit") int limit,
                                                          @QueryParam("total") int total, Map<String, Object> params)
    throws Exception{
        List<RouteVO> voes = new ArrayList<>();

        PagingDataBean<List<RouteMainBean>> paginationData = routeService.getUserRouteInfoes(this.getCurrentUserId(), Constants.RELATION_TYPE_RECOMMEND, new PagingConditionBean(nextCursor, limit, total), params);
        if(paginationData != null && paginationData.getTotal() > 0){
            List<RouteMainBean> routes = paginationData.getData();
            for(RouteMainBean route : routes){
                voes.add(RouteVO.transform(route));
            }
        }

        return new ResponseDataVO<>(voes);
    }

    /**
     * 保存行程基本信息
     * @param route
     * @param multipart
     * @return
     * @throws Exception
     */
    @POST
    @Path("/recommend/save")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ResponseDataVO<Long> saveRecommendRouteInfo(@FormDataParam("route") String route,  FormDataMultiPart multipart) throws Exception{
        RouteVO routeVO = BeanUtils.deserialize(route, RouteVO.class);

        RouteMainBean routeBean = RouteVO.transform(routeVO);
        routeBean.setStatus(RouteMainBean.Status.DRAFT);

        String imageUrls = super.uploadMediaFile(multipart, "imageUrl", IMAGE_PATH);
        if(StringUtils.isNotNull(imageUrls)){
            routeBean.setImageUrl(imageUrls);
        }

        this.routeService.saveRouteInfo(routeBean, super.getCurrentUserId(), Constants.RELATION_TYPE_RECOMMEND);

        return new ResponseDataVO<>(routeBean.getId());
    }

    /**
     * 行程发布
     * @param routeIds
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{routeIds}/publish")
    public ResponseVO publishRouteInfoes(@PathParam("routeIds") String routeIds) throws Exception{
        String[] arrRouteIds = routeIds.split(",");
        Long[] lRouteIds = new Long[arrRouteIds.length];
        for(int index = 0; index < arrRouteIds.length; index++){
            lRouteIds[index] = Long.valueOf(arrRouteIds[index]);
        }

        this.routeService.publishRouteInfoes(lRouteIds, super.getCurrentUserId());

        return new ResponseVO();
    }

    /**
     * 行程撤回
     * @param routeIds
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{routeIds}/withdraw")
    public ResponseVO withdrawRouteInfoes(@PathParam("routeIds") String routeIds) throws Exception{
        String[] arrRouteIds = routeIds.split(",");
        Long[] lRouteIds = new Long[arrRouteIds.length];
        for(int index = 0; index < arrRouteIds.length; index++){
            lRouteIds[index] = Long.valueOf(arrRouteIds[index]);
        }

        this.routeService.withdrawRouteInfoes(lRouteIds, super.getCurrentUserId());

        return new ResponseVO();
    }

    /**
     *删除行程
     * @param routeIds
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{routeIds}/delete")
    public ResponseVO deleteRouteInfoes(@PathParam("routeIds") String routeIds) throws Exception{
        String[] array = routeIds.split(",");
        Long[] lids = new Long[array.length];
        for(int index = 0; index < array.length; index++){
            lids[index] = Long.valueOf(array[index]);
        }

        this.routeService.deleteRouteInfoes(lids, super.getCurrentUserId());

        return new ResponseVO();
    }

    /**
     * 获取行程基本信息
     * @param routeId
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{routeId}/main")
    public ResponseDataVO<RouteVO> getRouteMainInfo(@PathParam("routeId") Long routeId) throws Exception{
        RouteMainBean route = this.routeService.getRouteInfo(routeId, 2);

        return new ResponseDataVO<>(RouteVO.transform(route));
    }

    /**
     * 获取行程完整信息
     * @param routeId
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{routeId}")
    public ResponseDataVO<RouteVO> getRouteInfo(@PathParam("routeId") Long routeId) throws Exception{
        RouteMainBean route = this.routeService.getRouteInfo(routeId, 2);

        return new ResponseDataVO<>(RouteVO.transform(route));
    }

    /**
     * 保存行程信息
     * @param schedule
     * @return
     * @throws Exception
     */
    @POST
    @Path("/{routeId}/schedule/day/save")
    public ResponseDataVO<Long> saveRouteScheduleDayInfo(@PathParam("routeId") Long routeId, RouteScheduleVO schedule) throws Exception{
        RouteScheduleBean scheduleBean = RouteScheduleVO.transform(schedule);

        this.routeService.saveRouteSchedule(scheduleBean, this.getCurrentUserId());

        return new ResponseDataVO<>(scheduleBean.getId());
    }

    /**
     * 保存行程信息
     * @param schedule
     * @return
     * @throws Exception
     */
    @POST
    @Path("/{routeId}/schedule/activity/save")
    public ResponseDataVO<Long> saveRouteScheduleActivityInfo(@PathParam("routeId") Long routeId, RouteScheduleVO schedule) throws Exception{
        RouteScheduleBean scheduleBean = RouteScheduleVO.transform(schedule);

        this.routeService.saveRouteSchedule(scheduleBean, this.getCurrentUserId());

        return new ResponseDataVO<>(scheduleBean.getId());
    }

    /**
     * 删除行程安排
     * @param routeId
     * @param scheduleId
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{routeId}/schedule/{scheduleId}/delete")
    public ResponseVO deleteRouteScheduleInfo(@PathParam("routeId") Long routeId, @PathParam("scheduleId") Long scheduleId) throws Exception{
        this.routeService.deleteRouteSchedule(routeId, scheduleId, this.getCurrentUserId());

        return new ResponseVO();
    }
}
