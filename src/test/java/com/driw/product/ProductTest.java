package com.driw.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    private Product getStubProduct(Double productPrice) {
        return getStubProduct(productPrice, 10);
    }

    private Product getStubProduct(Double productPrice, int discountPercentage) {
        String productNumber = "ABCD123";
        String productName = "product";
        int discountThresholdValue = 100;
        Product product = new Product(productNumber, productName, productPrice, discountPercentage, discountThresholdValue);
        return product;
    }

    @Test
    public void getPrice_ShouldGiveProductPrice() throws Exception {
        Product product = getStubProduct(100.0);
        assertTrue("getPrice did not return expected value", product.getPrice() == 100.0);
    }

    @Test
    public void getDiscountePrice_ShouldGiveDiscountedProductPrice() throws Exception {
        Product product = getStubProduct(100.0, 10);
        assertTrue("getDiscountedPrice did not return expected value",
                product.getDiscountedPrice() == 100.0 * ((100 - 10)/100.0));

    }

}