package com.driw.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryIntegrationTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void findByAccountUsername_AccountWithOrdersGiven_ShouldReturnOrders() {
        List<Order> orderList = this.orderRepository.findByAccountUsername("account_with_orders");
        assertThat(orderList.size()).isEqualTo(2);

        Order order = orderList.get(0);
        assertThat(order.getTotal()).isEqualTo(11100.0);
    }

    @Test
    public void findByAccountUsername_AccountWithoutOrdersGiven_ShouldReturnEmptyOrderList() {
        List<Order> orderList = this.orderRepository.findByAccountUsername("account_without_orders");
        assertThat(orderList.size()).isEqualTo(0);
    }

    @Test
    public void findByAccountUsername_NonExistingAccountGiven_ShouldReturnEmptyOrderList() {
        List<Order> orderList = this.orderRepository.findByAccountUsername("account_without_orders");
        assertThat(orderList.size()).isEqualTo(0);
    }
}