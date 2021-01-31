package com.course.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem{
    @Id
    @GeneratedValue
    private Long orderItemId;
    private Long productId;
    private String illustration;
    private String description;
    private Double price;
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Long productId, String illustration, String description, Double price, Integer quantity) {
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.illustration = illustration;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return orderItemId;
    }

    public void setId(Long id) {
        this.orderItemId = id;
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

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem :" +
                "id=" + orderItemId +
                ", productId=" + productId +
                ", quantity=" + quantity;
    }
}
