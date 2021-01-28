package com.course.client.controllers;

import com.course.client.beans.*;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsProductProxy;
import com.course.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    @Autowired
    private ClientService clientService;


    @RequestMapping("/")
    public String index(Model model) {

        List<ProductBean> products =  msProductProxy.list();

        model.addAttribute("products", products);

        return "index";
    }

    @RequestMapping("/product-detail/{id}")
    public String  productDetail(Model model,@PathVariable Long id){
        Optional<ProductBean> product =  msProductProxy.get(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            System.out.println(product.get());
            return "product";
        }
        return "error/404";
    }

    @RequestMapping("/add-cart/{productId}")
    public String addProductCart(
            @PathVariable Long productId,
            @RequestParam int quantity,
            Model model) {
        Long newId = 0L;
        System.out.println("productBean Id = " + productId);
        Optional<CartBean> cart =  msCartProxy.getCart(newId);
        if (cart.isPresent()) {
            CartItemBean cartItemBean = new CartItemBean();
            cartItemBean.setProductId(productId);
            cartItemBean.setQuantity(quantity);
            msCartProxy.addProductToCart(newId,cartItemBean);
            System.out.println(cart.toString());
            model.addAttribute("cart", cart.get());
            return "redirect:/mon-panier/";
        }
        return "error/404";
    }

    @RequestMapping("/mon-panier")
    public String myCart(Model model) {
        Long newId = 0L;
        System.out.println("New Id = " + newId);
        Optional<CartBean> cart =  msCartProxy.getCart(newId);
        System.out.println(cart.toString());
        if (cart.isPresent()) {
            CartBean cartBean = cart.get();
            try {
                List<ProductFinalBean> productFinalBeanList = clientService.convertCartToProductFinalBean(cartBean.getProducts());
                model.addAttribute("productFinalBeanList", productFinalBeanList);
                model.addAttribute("totalPrice",clientService.totalPrice(productFinalBeanList));
                System.out.println(cartBean.getProducts());
                return "cart";
            } catch (Exception e) {
                return "error/404";
            }
        }
        return "error/404";
    }

}
