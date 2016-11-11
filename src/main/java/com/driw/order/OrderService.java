package com.driw.order;

import com.driw.product.Product;

import java.util.Collection;


public interface OrderService {

    // Collection -> unordered list...
    Collection<Order> findByAccountUserName(String username);

    Order addOrder(Order order);

    OrderItem addOrderItem(Order order, Product product, int count);

}
