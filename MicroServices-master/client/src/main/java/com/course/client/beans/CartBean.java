package com.course.client.beans;


import java.util.List;

public class CartBean {
    private Long cartId;

    private List<CartItemBean> products;

    public CartBean(Long cartBeanId) {

        this.cartId = cartBeanId;
    }

    public CartBean() {
    }

    public Long getId() {
        return cartId;
    }

    public void setId(Long id) {
        this.cartId = id;
    }

    public List<CartItemBean> getProducts() {
        return products;
    }

    public void addProduct(CartItemBean product) {
        this.products.add(product);
    }

    public void removeCart(){ this.products.clear();}
}
