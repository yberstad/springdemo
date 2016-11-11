package com.driw.account;

import com.driw.base.BaseEntity;
import com.driw.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import groovy.transform.ToString;
import org.hibernate.annotations.NaturalId;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;


@ToString(excludes = "password")
@Entity
public class Account extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @NaturalId
    private String username;

    @JsonIgnore
    private String password;

    private String[] roles;

    @OneToMany
    List<Order> orderList;

    public void setPassword(String password) {

        this.password = password;//PASSWORD_ENCODER.encode(password);
    }

    public Account(){

    }

    public Account(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String[] getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }
}
