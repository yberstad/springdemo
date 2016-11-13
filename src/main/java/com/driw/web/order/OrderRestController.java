package com.driw.web.order;

import com.driw.account.Account;
import com.driw.account.AccountService;
import com.driw.exceptions.NotFoundException;
import com.driw.exceptions.UserNotFoundException;
import com.driw.order.Order;
import com.driw.order.OrderItem;
import com.driw.order.OrderService;
import com.driw.product.Product;
import com.driw.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
class OrderRestController {

    private OrderService orderService;
    private AccountService accountService;
    private ProductService productService;

    @Autowired
    OrderRestController(OrderService orderService,
                        AccountService accountService,
                        ProductService productService) {
        this.orderService = orderService;
        this.accountService = accountService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Order> retrieveOrders(Principal principal){
        this.validateUser(principal);
        return this.orderService.findByAccountUserName(principal.getName());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> createOrder(Principal principal){
        this.validateUser(principal);
        Optional<Account> account = this.accountService.findByUsername(principal.getName());
        Order order = this.orderService.addOrder(new Order(account.get()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="additem", method = RequestMethod.POST)
    ResponseEntity<?> addOrderItem(Principal principal,
                                   @RequestBody OrderItemRequestModel itemModel){
        this.validateUser(principal);

        Order order = this.orderService.getOrder(itemModel.orderId);
        if(order == null || !order.getAccount().getUsername().equals(principal.getName())) {
            throw new NotFoundException("Order could not be found");
        }

        Product product = this.productService.getProduct(itemModel.productId);
        if(product == null) {
            throw new NotFoundException("Product could not be found");
        }

        OrderItem orderItem = new OrderItem(order, product, itemModel.count);
        orderItem = this.orderService.addOrderItem(orderItem);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderItem.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    private void validateUser(Principal principal) {
        String userId = principal.getName();
        this.accountService
                .findByUsername(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(userId));
    }
}
