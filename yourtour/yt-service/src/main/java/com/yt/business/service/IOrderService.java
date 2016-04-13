package com.yt.business.service;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.OrderBean;
import com.yt.business.bean.ResourceBean;

import java.util.List;
import java.util.Map;

/**
 * 本接口类定义了平台的订单接口
 */
public interface IOrderService {
    /**
     * 提交订单
     * @param order
     * @param userId
     * @throws Exception
     */
    public void submitOrderInfo(OrderBean order, Long userId) throws Exception;

    /**
     * 订单取消
     * @param orderIds
     * @param userId
     * @throws Exception
     */
    public Map<Long, String> cancelOrderInfo(Long[] orderIds, Long userId) throws Exception;

    /**
     * 订单支付
     * @param orderId
     * @param userId
     * @throws Exception
     */
    public Map<Long, String> payOrder(Long[] orderIds, Long userId) throws Exception;

    /**
     * 订单交付
     * @param orderId
     * @param userId
     * @throws Exception
     */
    public Map<Long, String> deliveryOrder(Long[] orderIds, Long userId) throws Exception;

    /**
     * 订单信息获取
     * @param orderId
     * @param userId
     * @return
     * @throws Exception
     */
    public OrderBean getOrderInfo(Long orderId, Long userId) throws Exception;

    /**
     * 订单取消
     * @param orderIds
     * @param userId
     * @throws Exception
     */
    public void deleteOrderInfoes(Long[] orderIds, Long userId) throws Exception;

    /**
     * 用户获取订购的服务信息
     * @param userId
     * @param pagingCondition
     * @return
     * @throws Exception
     */
    public PagingDataBean<List<OrderBean>> getBookedOrderInfoes(String type, Long userId, PagingConditionBean pagingCondition) throws Exception;

    /**
     * 达人获取被订购服务信息
     * @param userId
     * @param pagingCondition
     * @return
     * @throws Exception
     */
    public PagingDataBean<List<OrderBean>> getServicedOrderInfoes(String type, Long userId, PagingConditionBean pagingCondition) throws Exception;

    /**
     * 为用户订购的定制服务创建行程
     * @param orderId
     * @param userId
     * @throws Exception
     */
    public Long createRouteInfo(Long orderId, Long userId) throws Exception;
}
