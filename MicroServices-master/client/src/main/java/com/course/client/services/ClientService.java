package com.course.client.services;

import com.course.client.beans.*;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ClientService {

    @Autowired
    private MsProductProxy msProductProxy;


    public Double totalPrice(List<ProductFinalBean> productFinalBeanList){
        Double price = 0.0;
        for(ProductFinalBean product: productFinalBeanList){
            price += product.getTotalPrice();
        }
        return price;
    }

    public ProductBean getProductById(Long id) throws Exception {
        Optional<ProductBean> product =  msProductProxy.get(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NullPointerException();
    }

    public List<ProductFinalBean> convertCartToProductFinalBean(List<CartItemBean> cartItemBeanList) throws Exception {
        List<ProductFinalBean> productFinalBeanList = new ArrayList<>();
        for (CartItemBean cartItemBean: cartItemBeanList){
            Long id = cartItemBean.getId();
            Long productId = cartItemBean.getProductId();
            ProductBean productBean = this.getProductById(productId);
            int quantity = cartItemBean.getQuantity();
            double totalPrice = productBean.getPrice() * quantity;
            productFinalBeanList.add(new ProductFinalBean(id, productBean, quantity, totalPrice));
        }
        return productFinalBeanList;
    }

    public List<ProductFinalBean> convertOrderToProductFinalBean(List<OrderItemBean> orderItemBeanList) throws Exception {
        List<ProductFinalBean> productFinalBeanList = new ArrayList<>();
        for (OrderItemBean orderItemBean: orderItemBeanList){
            Long id = orderItemBean.getId();
            Long productId = orderItemBean.getProductId();
            ProductBean productBean = this.getProductById(productId);
            int quantity = orderItemBean.getQuantity();
            double totalPrice = productBean.getPrice() * quantity;
            System.out.println("orderBeanconvertOrdertoPFB : " + orderItemBean.toString());
            productFinalBeanList.add(new ProductFinalBean(id, productBean, quantity, totalPrice));
        }
        return productFinalBeanList;
    }

    public List<OrderItemBean> convertListCartItemToListOrderItem (List<CartItemBean> cartItemBeanList) throws Exception {
        List<OrderItemBean> orderItemBeans = new ArrayList<>();
        List<ProductFinalBean> productFinalBeanList = convertCartToProductFinalBean(cartItemBeanList);
        productFinalBeanList.forEach(productFinalBean -> {
            Double random = Math.random();
            Long id = random.longValue();
            Long productId = productFinalBean.getProductBean().getId();
            Integer quantity = productFinalBean.getQuantity();
            String illustration = productFinalBean.getProductBean().getIllustration();
            String description = productFinalBean.getProductBean().getDescription();
            Double price = productFinalBean.getTotalPrice();
            orderItemBeans.add(new OrderItemBean(id, productId, quantity, illustration, description, price));
        });
        return orderItemBeans;
    }
    public OrderBean convertCartToOrder (CartBean cartBean) throws Exception {
        List<OrderItemBean> orders = convertListCartItemToListOrderItem(cartBean.getProducts());
        Double totalPrice = 0.0;
        for (OrderItemBean orderItemBean: orders){
            totalPrice += orderItemBean.getPrice();
            System.out.println("orderBeanItemPrice : " + orderItemBean.getPrice());
        }
        OrderBean orderBean = new OrderBean(1L,orders,totalPrice);
        System.out.println("orderBean client service : " + orderBean.toString());
        return orderBean;
    }

}
