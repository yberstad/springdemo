package com.driw.order;

import com.driw.base.BaseEntity;
import com.driw.product.Product;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "order_item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_fk")
    private Order order;

//    @OneToOne
//    @JoinColumn(name="product_fk")
//    private Product product;

    private String productNumber;

    private String productName;

    private int count;

    private double total;

    public OrderItem() {

    }

    public OrderItem(Order order, Product product, int count) {
        this.order = order;
        this.count = count;
        this.productNumber = product.getNumber();
        this.productName = product.getName();

        // Calculate the total cost based on current discount.
        double productPrice = (count >= product.getDiscountThresholdValue()) ?
                product.getDiscountedPrice() :
                product.getPrice();
        total = productPrice * count;
    }

    public String getProductNumber() { return productNumber; }

    public String getProductName() { return productName; }

    public int getCount() {
        return count;
    }

    public double getTotal() {
        return total;
    }

    public Long getId() {
        return id;
    }

    @Override
    protected void setId(Long id) {
        this.id = id;
    }

}
