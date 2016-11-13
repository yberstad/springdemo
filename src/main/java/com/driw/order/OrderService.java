package com.driw.order;

import com.driw.product.Product;

import java.util.List;


public interface OrderService {

    // Collection -> unordered list.
    // List -> order list.
    List<Order> findByAccountUserName(String username);

    Order getOrder(Long id);

    Order addOrder(Order order);

    OrderItem addOrderItem(OrderItem orderItem);

}
