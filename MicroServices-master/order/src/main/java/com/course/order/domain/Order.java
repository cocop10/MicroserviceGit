package com.course.order.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long cartId;
    private Double totalPrice;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orders;


    public Order() {
    }

    public Order(Long id, Long cartId, Double totalPrice, List<OrderItem> orders) {
        this.id = id;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.orders = orders;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }


    @Override
    public String toString() {
        return "Order :" +"id=" + id +", orders=" + orders;
    }
    public void addOrderItem ( OrderItem orderItem ){
        this.orders.add(orderItem);
        this.totalPrice += orderItem.getPrice()* orderItem.getQuantity();    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
