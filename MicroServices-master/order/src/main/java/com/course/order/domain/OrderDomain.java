package com.course.order.domain;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderDomain {
    @Id
    @GeneratedValue
    private Long orderId;

    private Long cartId;

    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    public OrderDomain() {
    }

    public OrderDomain(Long cartId, Double totalPrice) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
    }

    public OrderDomain(Long orderId, Long cartId, Double totalPrice, List<OrderItem> orderItemList) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }

    public Long getId() {
        return orderId;
    }

    public void setId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
        System.out.print("OrderItemadd = "+orderItemList.get(0));
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

    public void setOrderItemList(List<OrderItem> products) {
        this.orderItemList = products;
    }
}
