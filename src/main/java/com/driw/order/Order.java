package com.driw.order;


import com.driw.account.Account;
import com.driw.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="orders")
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_fk")
    private Account account;

    @OneToMany
    @JoinColumn(name="order_fk")
    private List<OrderItem> orderItemList;

    private Double total;

    public Order() {
        this.orderItemList = new ArrayList();
    }

    public Order(Account account) {
        this.orderItemList = new ArrayList();
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public Double getTotal() {
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
