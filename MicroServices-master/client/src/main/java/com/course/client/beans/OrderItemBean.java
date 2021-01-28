package com.course.client.beans;

public class OrderItemBean {
    private Long id;

    private Long productId;

    private Integer quantity;
    private String illustration;
    private String description;
    private Double price;

    public OrderItemBean(){}

    public OrderItemBean(Long id, Long productId, Integer quantity, String illustration, String description, Double price) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.illustration = illustration;
        this.description = description;
        this.price = price;
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
}
