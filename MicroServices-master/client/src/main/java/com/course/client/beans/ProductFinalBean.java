package com.course.client.beans;

public class ProductFinalBean {

    private long cartId;
    private ProductBean productBean;
    private int quantity;
    private Double totalPrice;


    public ProductFinalBean(long cartId, ProductBean productBean, int quantity, Double totalPrice) {
        this.cartId = cartId;
        this.productBean = productBean;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
