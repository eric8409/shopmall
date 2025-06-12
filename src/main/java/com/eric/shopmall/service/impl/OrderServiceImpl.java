package com.eric.shopmall.service.impl;

import com.eric.shopmall.dao.OrderDao;
import com.eric.shopmall.dao.ProductDao;
import com.eric.shopmall.dto.BuyItem;
import com.eric.shopmall.dto.CreateOrderRequest;
import com.eric.shopmall.model.Order;
import com.eric.shopmall.model.OrderItem;
import com.eric.shopmall.model.Product;
import com.eric.shopmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;


    @Override
    public Order getOrderById(Integer orderId) {


      Order order = orderDao.getOrderById(orderId);

      List<OrderItem> orderItemList  = orderDao.getOrderItemsById(orderId);

      order.setOrderItemList(orderItemList);

      return order;

    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

       for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
           Product product = productDao.getProductById(buyItem.getProductId());

           //計算總價錢
           int amount = buyItem.getQuantity() * product.getPrice();
           totalAmount = totalAmount + amount;


           //BuyItem 轉換 OrderItem

           OrderItem orderItem = new OrderItem();
           orderItem.setProduct_id(buyItem.getProductId());
           orderItem.setQuantity(buyItem.getQuantity());
           orderItem.setAmount(amount);

           orderItemList.add(orderItem);

       }


        //創建訂單
        Integer  orderId = orderDao.createOrder(userId, totalAmount);

         orderDao.createOrderItems(orderId, orderItemList);

        return orderId;


    }
}
