package com.course.client.beans;

import java.util.List;

public class OrderBean {
    private Long id;

    private List<OrderItemBean> orders;

    private Double total;

    public OrderBean(){}

    public OrderBean(Long id, List<OrderItemBean> orders, Double total) {
        this.id = id;
        this.orders = orders;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemBean> orders) {
        this.orders = orders;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void totalsOrder(){
        total = 0.0;
        for (OrderItemBean order: orders){
        total += order.getPrice()*order.getQuantity();
    }};

    public void addOrderItem(OrderItemBean order) {
        this.orders.add(order);
        this.total += order.getPrice()* order.getQuantity();
    }
}
