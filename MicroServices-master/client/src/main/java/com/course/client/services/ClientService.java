package com.course.client.services;

import com.course.client.beans.*;
import com.course.client.proxies.MsOrderProxy;
import com.course.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<ProductFinalBean> productFinalBean = new ArrayList<>();
        for (CartItemBean cartItemBean: cartItemBeanList){
            Long id = cartItemBean.getId();
            Long productId = cartItemBean.getProductId();
            ProductBean productBean = this.getProductById(productId);
            int quantity = cartItemBean.getQuantity();
            double totalPrice = productBean.getPrice() * quantity;
            productFinalBean.add(new ProductFinalBean(id, productBean, quantity, totalPrice));
        }
        return productFinalBean;
    }


    public List<OrderItemBean> convertListCartItemToListOrderItem (List<CartItemBean> cartItemBeanList) throws Exception {
        List<OrderItemBean> orderItemBeans = new ArrayList<>();
        List<ProductFinalBean> productFinalBeanList = convertCartToProductFinalBean(cartItemBeanList);
        for (ProductFinalBean productFinalBean: productFinalBeanList){
            Long productId = productFinalBean.getProductBean().getId();
            Integer quantity = productFinalBean.getQuantity();
            Double totalPrice = productFinalBean.getTotalPrice();
            orderItemBeans.add(new OrderItemBean(productId, quantity, totalPrice));
        }
        return orderItemBeans;
    }


    public OrderBean convertCartToOrder (CartBean cartBean) throws Exception {
        List<OrderItemBean> products = convertListCartItemToListOrderItem(cartBean.getProducts());
        Double totalPrice = 0.0;
        Long cartId = cartBean.getId();
        for (OrderItemBean orderItemBean: products){
            totalPrice += orderItemBean.getTotalPrice();
        }
        return new OrderBean(products, totalPrice, cartId);
    }

}
