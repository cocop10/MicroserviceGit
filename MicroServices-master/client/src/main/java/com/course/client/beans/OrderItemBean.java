package com.course.client.beans;

public class OrderItemBean implements Item{

    private Long id;

    private Long productId;
    private Double totalPrice;
    private Integer quantity;

    public OrderItemBean() {
    }

    public OrderItemBean(Long productId, Integer quantity, Double totalPrice) {
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order Item :"+id+":"+productId+":"+quantity;
    }
}
