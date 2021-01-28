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

}
