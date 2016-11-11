package com.driw.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

interface OrderRepository extends JpaRepository<Order, Long> {
    Collection<Order> findByAccountUsername(String username);
}
