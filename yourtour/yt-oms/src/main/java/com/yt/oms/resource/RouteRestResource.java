package com.yt.oms.resource;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ActivityContentBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.service.IActivityService;
import com.yt.business.service.IRouteService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.activity.ActivityContentVO;
import com.yt.oms.vo.activity.ActivityVO;
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
    @Path("/query")
    public ResponseDataVO<List<RouteVO>> queryRouteInfo(  @DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
                                                          @DefaultValue("20") @QueryParam("limit") int limit,
                                                          @QueryParam("total") int total, Map<String, Object> params)
    throws Exception{
        List<RouteVO> voes = new ArrayList<>();

        List<RouteMainBean> routes = routeService.getRoutes(nextCursor, limit, total, params);
        if(CollectionUtils.isNotEmpty(routes)){
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
    @Path("/main/save")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ResponseDataVO<Long> saveRouteMainInfo(@FormDataParam("route") String route,  FormDataMultiPart multipart) throws Exception{
        RouteVO routeVO = BeanUtils.deserialize(route, RouteVO.class);

        RouteMainBean routeBean = RouteVO.transform(routeVO);

        String imageUrls = super.uploadMediaFile(multipart, "imageUrl", IMAGE_PATH);
        if(StringUtils.isNotNull(imageUrls)){
            routeBean.setImageUrl(imageUrls);
        }

        this.routeService.saveRoute(routeBean, super.getCurrentUserId());

        return new ResponseDataVO<>(routeBean.getId());
    }
}
