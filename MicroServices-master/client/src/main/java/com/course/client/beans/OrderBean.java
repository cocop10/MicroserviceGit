package com.course.client.beans;

import java.util.List;

public class OrderBean {
    private Long orderId;

    private Long cartId;

    private Double totalPrice;

    private List<OrderItemBean> orderItemList;

    public OrderBean(){}

    public OrderBean(Long cartId, Double totalPrice) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
    }
/*
    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", products=" + products +
                ", total=" + total +
                '}';
    }*/

    public OrderBean(Long orderId, Long cartId, Double totalPrice, List<OrderItemBean> orderItemList) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }


    public Long getId() {
        return orderId;
    }

    public void setId(Long id) {
        this.orderId = id;
    }

    public List<OrderItemBean> getOrderItemBeanList() {
        return this.orderItemList;
    }

    public String getProductsByIdToString(Long id) {
        int intId = Math.toIntExact(id);
        return "id = "+ this.orderItemList.get(intId);
    }

    public void setOrderItemBeanList(List<OrderItemBean> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Double getTotal() {
        return totalPrice;
    }

    public void setTotal(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


}
