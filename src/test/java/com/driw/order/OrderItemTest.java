package com.driw.order;

import com.driw.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemTest {

    @Test
    public void constructor_WhenCountIsAboveDiscountThreshold_ShouldSetDiscountPrice() throws Exception {
        Order order = new Order();
        String productNumber = "productNumber";
        String productName = "productName";
        Double productPrice = 100.0;
        int discountPercentage = 10;
        int discountThreshold = 5;
        int productCount = 10;
        Product product = new Product(productNumber, productName, productPrice, discountPercentage, discountThreshold);
        OrderItem orderItem = new OrderItem(order, product, productCount);

        Double calculatedDiscountPrice = productPrice * ((100 - discountPercentage)/100.0);

        assertEquals("getTotal does not return discounted price",
                orderItem.getTotal(), calculatedDiscountPrice * productCount);
        assertEquals("getProductNumber should return specified product number",
                orderItem.getProductNumber(), productNumber);
    }
}