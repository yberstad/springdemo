package com.driw.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void addOrderItem_WithOrderItem_ShouldCallRepository() {
        OrderItem orderItem = new OrderItem();
        when(this.orderItemRepository.save(orderItem)).thenReturn(orderItem);
        this.orderService.addOrderItem(orderItem);
        verify(this.orderItemRepository, times(1)).save(orderItem);
    }

}