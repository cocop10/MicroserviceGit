package com.course.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long productId;
    private Double totalPrice;
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Long id, Long productId, Integer quantity, Double totalPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart Item :"+id+":"+productId+":"+quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
