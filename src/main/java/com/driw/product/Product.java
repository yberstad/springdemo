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
    private String number;

    @Column(nullable = false)
    private String name;

    private Double price;

    private int discountPercentage;

    private int discountThresholdValue;

    protected Product() {
    }

    public Product(String number, String name, Double price, int discountPercentage, int discountThresholdValue){
        this.number = number;
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.discountThresholdValue = discountThresholdValue;
    }

    public String getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getDiscountedPrice() {
        return price * ((100 - discountPercentage)/100.0);
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public int getDiscountThresholdValue() {
        return discountThresholdValue;
    }

    public Long getId() {
        return id;
    }

    @Override
    protected void setId(Long id) {
        this.id = id;
    }

}
