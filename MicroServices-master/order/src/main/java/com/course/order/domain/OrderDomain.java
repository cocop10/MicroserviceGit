package com.course.order.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderDomain {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;

    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orders;

    public OrderDomain(Long id, Long cartId, Double totalPrice, List<OrderItem> orders) {
        this.id = id;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.orders = orders;
    }

    public OrderDomain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrders() {
        return this.orders;
    }

    public void addOrderItem(OrderItem order) {
        this.orders.add(order);
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProducts(List<OrderItem> products) {
        this.orders = products;
    }
}
