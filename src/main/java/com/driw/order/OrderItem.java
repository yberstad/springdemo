package com.driw.order;

import com.driw.base.BaseEntity;
import com.driw.product.Product;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    private int count;

    private double total;

    public OrderItem() {

    }

    public OrderItem(Order order, Product product, int count) {
        this.order = order;
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
