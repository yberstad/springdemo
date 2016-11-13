package com.driw.product;

/**
 * Created by oyvindhabberstad on 10/11/2016.
 */
public interface ProductService {
    Product save (Product product);
    Product getProduct(String name);
    Product getProduct(Long id);
}
