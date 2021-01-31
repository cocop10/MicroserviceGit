package com.course.client.beans;

public class ProductBean {
    private Long productId;

    private String name;

    private String description;

    private String illustration;

    private Double price;


    public ProductBean(){}

    public ProductBean(Long productId, String name, String description, String illustration, Double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.illustration = illustration;
        this.price = price;
    }

    public Long getId() {
        return this.productId;
    }

    public void setId(Long id) {
        this.productId = id;
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
