package com.driw.web.order;

import com.driw.account.Account;
import com.driw.account.AccountService;
import com.driw.order.Order;
import com.driw.order.OrderItem;
import com.driw.order.OrderService;
import com.driw.product.Product;
import com.driw.product.ProductService;
import com.driw.utils.SetIdHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class OrderRestControllerTest {

    private String accountName = "account_with_orders";


    private Account getStubAccount() {
        // Accounts
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        return new Account(accountName, "password1", roles);
    }


    private List<Order> getStubOrderList() {
        // Products
        Product product1 = new Product("ABC123", "Yogurt", 10.0, 5, 10);
        Product product2 = new Product("ABC124", "Ketchup", 50.0, 2, 20);
        Product product3 = new Product("ABC125", "Chocolate", 20.0, 4, 30);
        SetIdHelper.setId(product1, 1L);
        SetIdHelper.setId(product2, 2L);
        SetIdHelper.setId(product3, 3L);

        // Orders
        Order order1 = new Order(getStubAccount());
        SetIdHelper.setId(order1, 1L);

        OrderItem orderItem1 = new OrderItem(order1, product1, 10);
        OrderItem orderItem2 = new OrderItem(order1, product2, 20);
        OrderItem orderItem3 = new OrderItem(order1, product3, 30);
        SetIdHelper.setId(orderItem1, 1L);
        SetIdHelper.setId(orderItem2, 2L);
        SetIdHelper.setId(orderItem3, 3L);
        order1.getOrderItemList().add(orderItem1);
        order1.getOrderItemList().add(orderItem2);
        order1.getOrderItemList().add(orderItem3);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);

        return orderList;
    }

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private ProductService productService;

    @Before
    public void setUp(){

        mockMvc = webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .defaultRequest(get("/").with(testSecurityContext()))
                .build();
    }

    @Test
    @WithMockUser("account_with_orders")
    public void apiOrders_AccountWithOrdersGiven_ShouldReturnOrders() throws Exception {
        Optional<Account> findByUserNameReturnValue = Optional.of(getStubAccount());
        when(this.accountService.findByUsername(accountName)).thenReturn(findByUserNameReturnValue);
        when(this.orderService.findByAccountUserName(accountName)).thenReturn(getStubOrderList());

        this.mockMvc.perform(get("/api/orders").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].orderItemList[1].productNumber").value("ABC124"));

        verify(orderService, times(1)).findByAccountUserName(accountName);
    }

}