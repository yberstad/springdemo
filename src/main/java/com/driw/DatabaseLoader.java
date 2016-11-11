package com.driw;


import com.driw.account.Account;
import com.driw.account.AccountService;
import com.driw.order.Order;
import com.driw.order.OrderService;
import com.driw.product.Product;
import com.driw.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DatabaseLoader implements CommandLineRunner{

    private AccountService accountService;
    private ProductService productService;
    private OrderService orderService;

    @Autowired
    DatabaseLoader(AccountService accountService,
                   ProductService productService,
                   OrderService orderService) {
        this.accountService = accountService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Accounts
        Account account1 = this.accountService.save(new Account("hebbe", "jaggu", "ROLE_ADMIN"));

        // Products
        Product product1 = this.productService.save(new Product("Yogurt", 10.0, 5, 10));
        Product product2 = this.productService.save(new Product("Ketchup", 50.0, 2, 20));
        Product product3 = this.productService.save(new Product("Chocolate", 20.0, 4, 30));


        // Orders
        Order order1 = this.orderService.addOrder(new Order(account1));
        this.orderService.addOrderItem(order1, product1, 10);
        this.orderService.addOrderItem(order1, product2, 10);
        this.orderService.addOrderItem(order1, product3, 10);
        this.orderService.addOrderItem(order1, product3, 50);

        Order order2 = this.orderService.addOrder(new Order(account1));
        this.orderService.addOrderItem(order2, product1, 20);
        this.orderService.addOrderItem(order2, product2, 20);

    }
}
