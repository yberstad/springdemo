package com.driw.product;

import com.driw.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    private String name;

    private Double price;

    private int discountPercentage;

    private int discountCountThreshold;

    protected Product() {
    }

    public Product(String name, Double price, int discountPercentage, int discountCountThreshold){
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.discountCountThreshold = discountCountThreshold;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public int getDiscountCountThreshold() {
        return discountCountThreshold;
    }

    public Long getId() {
        return id;
    }

    @Override
    protected void setId(Long id) {
        this.id = id;
    }
}
