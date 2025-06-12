package com.eric.shopmall.service;

import com.eric.shopmall.dto.CreateOrderRequest;
import com.eric.shopmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
