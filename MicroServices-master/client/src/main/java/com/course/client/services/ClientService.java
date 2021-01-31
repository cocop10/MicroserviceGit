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


    public List<ProductFinalBean> convertCartToProductQuantity(List<CartItemBean> cartItemBeanList) throws Exception {
        List<ProductFinalBean> productQuantities = new ArrayList<>();
        for (CartItemBean cartItemBean: cartItemBeanList){
            Long id = cartItemBean.getId();
            Long productId = cartItemBean.getProductId();
            ProductBean productBean = this.getProductById(productId);
            int quantity = cartItemBean.getQuantity();
            double totalPrice = productBean.getPrice() * quantity;
            productQuantities.add(new ProductFinalBean(id, productBean, quantity, totalPrice));
        }
        return productQuantities;
    }

    public List<ProductFinalBean> convertOrderToProductQuantity (List<OrderItemBean> cartItemBeanList) throws Exception {
        List<ProductFinalBean> productQuantities = new ArrayList<>();
        for (OrderItemBean cartItemBean: cartItemBeanList){
            Long id = cartItemBean.getId();
            Long productId = cartItemBean.getProductId();
            ProductBean productBean = this.getProductById(productId);
            int quantity = cartItemBean.getQuantity();
            double totalPrice = productBean.getPrice() * quantity;
            productQuantities.add(new ProductFinalBean(id, productBean, quantity, totalPrice));
        }
        return productQuantities;
    }


    public List<OrderItemBean> convertListCartItemToListOrderItem (List<CartItemBean> cartItemBeanList) throws Exception {
        List<OrderItemBean> orderItemBeans = new ArrayList<>();
        List<ProductFinalBean> productQuantities = convertCartToProductQuantity(cartItemBeanList);
        for (ProductFinalBean productQuantity: productQuantities){
            Long productId = productQuantity.getProductBean().getId();
            Integer quantity = productQuantity.getQuantity();
            Double totalPrice = productQuantity.getTotalPrice();
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
        return new OrderBean(
                products,
                totalPrice,
                cartId
        );
    }

}
