package com.course.client.beans;


import java.util.List;

public class CartBean {
    private Long id;

    private List<CartItemBean> products;

    public CartBean(Long id) {
        this.id = id;
    }

    public CartBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemBean> getProducts() {
        return products;
    }

    public void addProduct(CartItemBean product) {
        this.products.add(product);
    }

    public void clearCart() {
        this.products.clear();
    }
    public void removeProduct(int index) {
        this.products.remove(index);
    }
}
