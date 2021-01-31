package com.course.client.beans;

public class CartItemBean {

    private Long cartItemId;

    private Long productId;

    private Integer quantity;

    public CartItemBean() {
    }

    public CartItemBean(Long cartItemId, Long productId, Integer quantity) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return cartItemId;
    }

    public void setId(Long id) {
        this.cartItemId = id;
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
        return "Cart Item : id:"+cartItemId+" productId:"+productId+" quantity:" +quantity;
    }
}
