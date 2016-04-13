package com.yt.oms.resource;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.OrderBean;
import com.yt.business.service.IOrderService;
import com.yt.core.utils.CollectionUtils;
import com.yt.oms.vo.order.OrderVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponsePagingDataVO;
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


/**
 * 行程管理Restful接口
 * Created by 林平 on 2016/3/26.
 */
@Component
@Path("/oms/order/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderRestResource extends RestResource {
    private static final Log LOG = LogFactory.getLog(OrderRestResource.class);

    @Autowired
    private IOrderService orderService;

    /**
     * 达人获取用户提交的订单
     * @param nextCursor
     * @param limit
     * @param total
     * @param type
     * @return
     * @throws Exception
     */
    @GET
    @Path("/service/{type}")
    public ResponsePagingDataVO<List<OrderVO>> getServicedOrderInfoes(@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
                                                                    @DefaultValue("20") @QueryParam("limit") int limit,
                                                                    @QueryParam("total") int total,
                                                                    @PathParam("type") String type) throws Exception{
        PagingConditionBean pagingCondition = new PagingConditionBean(nextCursor, limit, total);
        PagingDataBean<List<OrderBean>> pagingData = this.orderService.getServicedOrderInfoes(type, this.getCurrentUserId(), pagingCondition);
        List<OrderBean> orders = pagingData.getData();
        List<OrderVO> voes = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(orders)){
            for(OrderBean order : orders){
                voes.add(OrderVO.transform(order));
            }
        }

        return new ResponsePagingDataVO<List<OrderVO>>(pagingData.getTotal(), voes.size(), voes);
    }

    /**
     * 订单提交
     * @param vo
     * @return
     * @throws Exception
     */
    @POST
    @Path("/submit")
    public ResponseVO submitOrderInfo(OrderVO vo) throws Exception{
        OrderBean bean = OrderVO.transform(vo);

        this.orderService.submitOrderInfo(bean, this.getCurrentUserId());

        return new ResponseVO();
    }

    /**
     * 订单确认使用
     * @param orderIds
     * @return
     * @throws Exception
     */
    @POST
    @Path("{orderIds}/use")
    public ResponseVO useOrder(@PathParam("orderIds") String orderIds) throws Exception{
        String[] aIds = orderIds.split(",");
        Long[] lIds = new Long[aIds.length];

        for(int index = 0; index < aIds.length; index++){
            lIds[index] = Long.valueOf(aIds[index]);
        }
        this.orderService.deliveryOrder(lIds, this.getCurrentUserId());

        return new ResponseVO();
    }

    /**
     * 订单提交
     * @param orderId
     * @return
     * @throws Exception
     */
    @POST
    @Path("/{orderId}/route/save")
    public ResponseDataVO<Long> createRouteInfo(@PathParam("orderId") Long orderId) throws Exception{
        Long routeId = this.orderService.createRouteInfo(orderId, this.getCurrentUserId());

        return new ResponseDataVO<>(routeId);
    }
}