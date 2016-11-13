package com.driw.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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
    public List<Order> findByAccountUserName(String username) {
        return this.orderRepository.findByAccountUsername(username);
    }

    @Override
    public Order getOrder(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public Order addOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        Assert.notNull(orderItem, "Hotel OrderItem not be null");
        return this.orderItemRepository.save(orderItem);
    }
}
