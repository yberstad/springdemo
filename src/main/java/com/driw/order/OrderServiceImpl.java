package com.driw.order;

import com.driw.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Component("orderService")
@Transactional
class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Collection<Order> findByAccountUserName(String username) {
        return this.orderRepository.findByAccountUsername(username);
    }

    @Override
    public Order addOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public OrderItem addOrderItem(Order order, Product product, int count) {
        OrderItem orderItem = new OrderItem(order, product, count);
        return this.orderItemRepository.save(orderItem);
    }
}
