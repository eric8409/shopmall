package com.eric.shopmall.dao;

import com.eric.shopmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

     Integer createOrder(Integer userId, Integer totalAmount);

     void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
