package com.course.client.services;

import com.course.client.beans.*;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsOrderProxy;
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
    @Autowired
    private MsOrderProxy msOrderProxy;

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
/*
    public List<ProductFinalBean> convertOrderToProductFinalBean(List<OrderItemBean> orderItemBeanList) throws Exception {
        List<ProductFinalBean> productFinalBeanList = new ArrayList<>();
        for (OrderItemBean orderItemBean: orderItemBeanList){
            Long id = orderItemBean.getId();
            Long productId = orderItemBean.getProductId();
            ProductBean productBean = this.getProductById(productId);
            int quantity = orderItemBean.getQuantity();
            double totalPrice = productBean.getPrice() * quantity;
            productFinalBeanList.add(new ProductFinalBean(id, productBean, quantity, totalPrice));
        }
        return productFinalBeanList;
    }*/

    public List<OrderItemBean> convertListCartItemToListOrderItem (List<CartItemBean> cartItemBeanList) throws Exception {
        List<OrderItemBean> orderItemBeanList = new ArrayList<>();

        //List<ProductFinalBean> productFinalBeanList = convertCartToProductFinalBean(cartItemBeanList);
        for (CartItemBean productFinalBean: cartItemBeanList){
            Long idCartItem = productFinalBean.getId();
            System.out.println(idCartItem);
            Long productId = productFinalBean.getProductId()+1;
            Integer quantity = productFinalBean.getQuantity();
            Double totalPrice = productFinalBean.getQuantity() * msProductProxy.get(productId).get().getPrice();
            String illustration = msProductProxy.get(productId).get().getIllustration();
            String description = msProductProxy.get(productId).get().getDescription();

            orderItemBeanList.add(new OrderItemBean(idCartItem,productId, quantity,illustration,description, totalPrice));
        }

        System.out.println(orderItemBeanList);
        return orderItemBeanList;
    }
    public OrderBean convertCartToOrder (CartBean cartBean) throws Exception {
        List<OrderItemBean> orderItemBeanList = convertListCartItemToListOrderItem(cartBean.getProducts());
        int nombreOrder = msOrderProxy.getOrderList().size()+1;
        Long idOrder = Long.valueOf(nombreOrder);
        Double totalPrice = 0.0;
        for (OrderItemBean orderItemBean: orderItemBeanList){
            totalPrice += orderItemBean.getPrice();
            System.out.println("id = "+orderItemBean.getId());
        }

        OrderBean orderBean = new OrderBean(1L,totalPrice);

        return orderBean;
    }

}
