package com.course.cart.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long cartId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> products;

    public Cart(Long id) {
        this.cartId = id;
    }

    public Cart() {
    }

    public Long getId() {
        return cartId;
    }

    public void setId(Long id) {
        this.cartId = cartId;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void addProduct(CartItem product) {
        this.products.add(product);
    }
}
