package com.yt.business.repository.neo4j;

import com.yt.business.bean.OrderBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface OrderBeanRepository extends GraphRepository<OrderBean> {


    /**
     * 获取用户订购的服务
     * @param userId
     * @return
     */
    @Query("START n=node({0}) MATCH n-[:BOOK]->(order:OrderBean)<-[:SERVICE]-(user:UserProfileBean) RETURN order, user")
    public List<OrderTuple> getBookedOrderInfoes(Long userId);

    /**
     * 获取用户订购的服务
     * @param userId
     * @return
     */
    @Query("START n=node({0}) MATCH n-[:BOOK]->(order:OrderBean)<-[:SERVICE]-(user:UserProfileBean) WHERE order.type={1} RETURN order, user")
    public List<OrderTuple> getBookedOrderInfoes(Long userId, String type);

    /**
     * 获取达人被订购的服务
     * @param userId
     * @return
     */
    @Query("START n=node({0}) MATCH n-[:SERVICE]->(order:OrderBean)<-[:BOOK]-(user:UserProfileBean) RETURN order, user")
    public List<OrderTuple> getServicedOrderInfoes(Long userId);

    /**
     * 获取达人被订购的服务
     * @param userId
     * @return
     */
    @Query("START n=node({0}) MATCH n-[:SERVICE]->(order:OrderBean)<-[:BOOK]-(user:UserProfileBean) WHERE order.type={1} RETURN order, user")
    public List<OrderTuple> getServicedOrderInfoes(Long userId, String type);

    /**
     * 获取达人服务订单的订购信息
     * @param orderId
     * @return
     */
    @Query("START order=node({0}) MATCH order<-[:BOOK]-(user:UserProfileBean) RETURN order, user")
    public OrderTuple getServiceOrderInfo(Long orderId);
}
