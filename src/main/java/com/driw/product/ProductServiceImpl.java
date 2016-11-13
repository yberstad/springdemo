package com.driw.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("productService")
@Transactional
class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product getProduct(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Product getProduct(Long id) {
        return this.productRepository.findById(id);
    }
}
