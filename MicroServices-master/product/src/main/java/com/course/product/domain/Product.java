package com.course.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long productId;

    private String name;

    private String description;

    private String illustration;

    private Double price;


    public Product(){}

    public Product(Long id, String name, String description, String illustration, Double price) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.illustration = illustration;
        this.price = price;
    }

    public Long getId() {
        return productId;
    }

    public void setId(Long id) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return productId+":"+name;
    }
}
