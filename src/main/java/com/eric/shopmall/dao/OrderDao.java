package com.eric.shopmall.dao;

import com.eric.shopmall.model.Order;
import com.eric.shopmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

     Order getOrderById(Integer orderId);

     List<OrderItem> getOrderItemsById(Integer orderId);

     Integer createOrder(Integer userId, Integer totalAmount);

     void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
