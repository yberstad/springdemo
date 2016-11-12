package com.driw.web;

import com.driw.account.AccountService;
import com.driw.exceptions.UserNotFoundException;
import com.driw.order.Order;
import com.driw.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{userId}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
class OrderRestController {

    private OrderService orderService;
    private AccountService accountService;

    @Autowired
    OrderRestController(OrderService orderService,
                        AccountService accountService) {
        this.orderService = orderService;
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Order> retrieveOrders(@PathVariable String userId){
        this.validateUser(userId);
        return this.orderService.findByAccountUserName(userId);
    }

    private void validateUser(String userId) {
        this.accountService
                .findByUsername(userId)
                .orElseThrow(
                    () -> new UserNotFoundException(userId)
                );
    }
}
