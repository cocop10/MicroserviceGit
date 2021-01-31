package com.course.client.beans;


import java.util.List;

public class OrderBean {
    private Long id;

    private List<OrderItemBean> products;
    private Double totalPrice;

    private Long cartId;

    public OrderBean(Long id, Double totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public OrderBean(List<OrderItemBean> products, Double totalPrice, Long cartId) {
        this.products = products;
        this.totalPrice = totalPrice;
        this.cartId = cartId;
    }

    public OrderBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemBean> getProducts() {
        return products;
    }

    public void addProduct(OrderItemBean product) {
        this.products.add(product);
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProducts(List<OrderItemBean> products) {
        this.products = products;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
