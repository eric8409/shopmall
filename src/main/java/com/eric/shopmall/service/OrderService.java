package com.eric.shopmall.service;

import com.eric.shopmall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
