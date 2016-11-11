package com.driw.product;

import com.driw.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
