package com.driw.order;


import com.driw.account.Account;
import com.driw.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity(name="ORDERS")
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;

    private Double total;

    public Order() {

    }

    public Order(Account account) {
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
}
