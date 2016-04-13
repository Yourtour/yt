package com.yt.business.service.impl;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.OrderBeanRepository;
import com.yt.business.repository.neo4j.OrderTuple;
import com.yt.business.service.IOrderService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.neo4j.repository.CrudOperate;
import com.yt.neo4j.repository.RelationshipCrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceBase implements IOrderService {
	private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);

	@Autowired
	private OrderBeanRepository repository;

	@Autowired
	private CrudOperate<OrderBean> orderCrudOperate;

	@Autowired
	private CrudOperate<RouteMainBean> routeCrudOperate;

	@Autowired
	private RelationshipCrudOperate<OrderBean, RouteMainBean> orderRouteRelationship;

	@Autowired
	private RelationshipCrudOperate<UserProfileBean, RouteMainBean> userRouteRelationship;

	public OrderServiceImpl() {
	}

	@Override
	public void submitOrderInfo(OrderBean order, Long userId) throws Exception {
		boolean isNew = order.isNew();
		OrderBean saved;
		if(! isNew){
			saved = orderCrudOperate.get(order.getId(), false);
			if(saved != null){
				if(! saved.getStatus().equals(OrderBean.Status.SUBMITTED)){
					throw new AppException(StaticErrorEnum.BUSINESS_ORDER_NOT_EDITABLE);
				}

				BeanUtils.merge(saved, order);
			}
		}else{
			saved = order;
			saved.setStatus(OrderBean.Status.SUBMITTED);
			saved.setUser(new UserProfileBean(userId));
		}

		this.updateBaseInfo(saved, userId);

		this.orderCrudOperate.save(saved);
	}

	@Override
	public Map<Long, String> cancelOrderInfo(Long[] orderIds, Long userId) throws Exception {
		Map<Long, String> result = new HashMap<>();

		OrderBean order = null;
		for(Long orderId : orderIds){
			order = this.orderCrudOperate.get(orderId, false);
			if(order == null) {
				result.put(orderId, "订单[" + orderId + "]不存在，不能取消。");
				continue;
			}

			if(OrderBean.Status.USED.equals(order.getStatus())){
				result.put(orderId, "订单[" + orderId + "]已完成，不能取消。");
				continue;
			}

			order.setStatus(OrderBean.Status.CANCELLED);
			this.updateBaseInfo(order, userId);
			this.orderCrudOperate.save(order);
		}

		return result;
	}

	@Override
	public Map<Long, String> payOrder(Long[] orderIds, Long userId) throws Exception {
		return null;
	}

	@Override
	public Map<Long, String> deliveryOrder(Long[] orderIds, Long userId) throws Exception {
		Map<Long, String> result = new HashMap<>();

		OrderBean order = null;
		for(Long orderId : orderIds){
			order = this.orderCrudOperate.get(orderId, false);
			if(order == null) {
				result.put(orderId, "订单[" + orderId + "]不存在，不能交付。");
				continue;
			}

			if(OrderBean.Status.USED.equals(order.getStatus())){
				result.put(orderId, "订单[" + orderId + "]已完成，不能取消。");
				continue;
			}

			order.setStatus(OrderBean.Status.USED);
			order.setUsedTime(System.currentTimeMillis());
			this.updateBaseInfo(order, userId);
			this.orderCrudOperate.save(order);
		}

		return result;
	}

	@Override
	public OrderBean getOrderInfo(Long orderId, Long userId) throws Exception {
		return orderCrudOperate.get(orderId);
	}

	@Override
	public void deleteOrderInfoes(Long[] orderIds, Long userId) throws Exception {
		for(Long orderId : orderIds) {
			OrderBean order = this.orderCrudOperate.get(orderId, false);
			if(order == null) continue;

			if(order.getCreatedUserId() != userId){
				throw new AppException(StaticErrorEnum.DB_OPERATE_PERMISSION_FAIL);
			}

			order.setDeleted(true);

			this.orderCrudOperate.save(order);
		}
	}

	@Override
	public PagingDataBean<List<OrderBean>> getBookedOrderInfoes(String type, Long userId, PagingConditionBean pagingCondition) throws Exception {
		int total = pagingCondition.getTotal();
		if (total <= 0) {
			total = 10;
		}

		List<OrderTuple> tuples = null;
		if(StringUtils.isNotNull(type)){
			tuples = this.repository.getBookedOrderInfoes(userId, type);
		}else{
			tuples = this.repository.getBookedOrderInfoes(userId);
		}

		List<OrderBean> orders = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(tuples)){
			for(OrderTuple tuple : tuples){
				tuple.getOrder().setExpert(tuple.getUser());
				orders.add(tuple.getOrder());
			}
		}

		return new PagingDataBean<List<OrderBean>>(total,orders);
	}

	@Override
	public PagingDataBean<List<OrderBean>> getServicedOrderInfoes(String type, Long userId, PagingConditionBean pagingCondition) throws Exception {
		int total = pagingCondition.getTotal();
		if (total <= 0) {
			total = 10;
		}

		List<OrderTuple> tuples = null;
		if(StringUtils.isNotNull(type)){
			tuples = this.repository.getServicedOrderInfoes(userId, type);
		}else{
			tuples = this.repository.getServicedOrderInfoes(userId);
		}

		List<OrderBean> orders = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(tuples)){
			for(OrderTuple tuple : tuples){
				tuple.getOrder().setUser(tuple.getUser());
				orders.add(tuple.getOrder());
			}
		}
		return new PagingDataBean<List<OrderBean>>(total,orders);
	}

	@Override
	public Long createRouteInfo(Long orderId, Long userId) throws Exception {
		OrderTuple tuple = this.repository.getServiceOrderInfo(orderId);
		if(tuple == null){
			throw new AppException(StaticErrorEnum.DATA_NOT_EXIST);
		}

		//创建行程基本信息
		RouteMainBean route = new RouteMainBean(-1l);
		route.setStatus(RouteMainBean.Status.DRAFT);
		this.updateBaseInfo(route, userId);
		this.routeCrudOperate.save(route);

		//通过属性建立订单和行程之间关系
		Long routeId = route.getId();
		tuple.getOrder().setRouteId(route.getId());
		this.orderCrudOperate.save(tuple.getOrder());

		//维护行程和用户之间关系
		userRouteRelationship.createRelation(tuple.getUser(), route, Constants.RELATION_TYPE_DRAWUP, Direction.OUTGOING);

		//维护行程和达人之间关系
		userRouteRelationship.createRelation(new UserProfileBean(userId), route, Constants.RELATION_TYPE_DRAWUP, Direction.OUTGOING);

		//维护行程和订单之间关系
		orderRouteRelationship.createRelation(tuple.getOrder(), route, Constants.RELATION_TYPE_FOR, Direction.INCOMING);

		return routeId;
	}
}
