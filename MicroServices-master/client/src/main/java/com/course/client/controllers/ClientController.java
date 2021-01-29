package com.course.client.controllers;

import com.course.client.beans.*;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsOrderProxy;
import com.course.client.proxies.MsProductProxy;
import com.course.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private MsOrderProxy msOrderProxy;

    @RequestMapping("/all-products")
    public String index(Model model) {
        List<ProductBean> products =  msProductProxy.list();
        model.addAttribute("products", products);
        return "products";
    }
    @RequestMapping("/")
    public String pageHome(Model model) {
        List<ProductBean> products =  msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/product-detail/{id}")
    public String  productDetail(Model model,@PathVariable Long id){
        Optional<ProductBean> productBean =  msProductProxy.get(id);
        List<ProductBean> products =  msProductProxy.list();
        if (productBean.isPresent()) {
            model.addAttribute("products", products);
            model.addAttribute("product", productBean.get());
            System.out.println(productBean.get());
            return "productdetail";
        }
        return "error/404";
    }



    @RequestMapping("/add-cart/{productId}")
    public String addProductCart(
            @PathVariable Long productId,
            @RequestParam int quantity,
            Model model) {
        Long newId = 1L;
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

    @RequestMapping("/validation-commande")
    public String createOrder(
            Optional<Long> cartId,
            Model model) {
        Long newCartId = 1L;
        Optional<CartBean> optionalCart = msCartProxy.getCart(newCartId);
        if (optionalCart.isPresent()) {
            CartBean cartBean = optionalCart.get();
            try {
                System.out.println("try1");
                OrderBean orderBean = clientService.convertCartToOrder(cartBean);
                //Long orderId = orderBean.getId();
                System.out.println("orderBean : " + orderBean.toString());
                msOrderProxy.createNewOrder(orderBean);
                System.out.println("orderBean2 : " + orderBean.toString());
                for(OrderItemBean orderItemBean: orderBean.getOrders()){
                    Long id = orderItemBean.getId();
                    Long productId = orderItemBean.getId();
                    int quantity = orderItemBean.getQuantity();
                    System.out.println("orderBeanAdd1 : " + orderBean.toString());
                    String illustration = orderItemBean.getIllustration();
                    String description = orderItemBean.getDescription();
                    Double price = orderItemBean.getPrice();
                    OrderItemBean newOrderItemBean = new OrderItemBean(id, productId, quantity, illustration, description, price);
                    System.out.println("orderBeanAdd2 : " + newOrderItemBean.toString());
                    System.out.println("orderBeanAdd3 : " + newOrderItemBean.getProductId());
                    msOrderProxy.addOrderItemToOrder(orderBean.getId(), newOrderItemBean);
                    System.out.println("orderBeanAdd4 : " + newOrderItemBean.toString());
                }
                //msOrderProxy.addOrderItemToOrder()
                model.addAttribute("order", orderBean.getOrders());
            } catch (Exception e) {
                return "error/404";
            }
            return "redirect:/mes-commandes";
        }
        return "error/404";
    }
    @RequestMapping("/mes-commandes")
    public String myOrders(Model model) {
        Long orderId = 1L;
        Optional<OrderBean> order = msOrderProxy.getOrder(orderId);
        System.out.println("orderBeanMescommandes : " + order.toString());
        //msOrderProxy.getOrder(order.get().getId());
        if (order.isPresent()) {
            OrderBean orderBean = order.get();
            try{
                System.out.println("orderBeanMescommandes : " + orderBean.toString());
                List<ProductFinalBean> productFinalBeanList = clientService.convertOrderToProductFinalBean(orderBean.getOrders());
                model.addAttribute("productFinalBeanList", productFinalBeanList);
                model.addAttribute("totalPrice",clientService.totalPrice(productFinalBeanList));
                return "order";
            }catch(Exception e) {
                return "error/404";
            }

        }
        return "je passe";
    }

//    @RequestMapping("/mes-commandes/{orderId}")
//    public String cart(@PathVariable Long orderId, Model model, HttpServletResponse response) {
//        Optional<OrderBean> orderBean =  msOrderProxy.getOrder(orderId);
//        if (orderBean.isPresent()) {
//            OrderBean order = orderBean.get();
//            try {
//                List<ProductFinalBean> productQuantities = clientService.convertOrderToProductFinalBean(order.getOrders());
//                model.addAttribute("productQuantities", productQuantities);
//                model.addAttribute("totalPrice",clientService.totalPrice(productQuantities));
//                model.addAttribute("order",order);
//                return "order";
//            } catch (Exception e) {
//                return "error/404";
//            }
//        }
//        return "error/404";
//    }

    @RequestMapping("/mon-panier")
    public String myCart(Model model) {
        Long newId = 1L;
        Optional<CartBean> cart =  msCartProxy.getCart(newId);
        System.out.println("cart: " + cart.toString());
        if (cart.isPresent()) {
            CartBean cartBean = cart.get();
            try {
                List<ProductFinalBean> productFinalBeanList = clientService.convertCartToProductFinalBean(cartBean.getProducts());
                model.addAttribute("productFinal", productFinalBeanList);
                model.addAttribute("totalPrice",clientService.totalPrice(productFinalBeanList));
                System.out.println("carteBean : "+ cartBean.getProducts());
                return "cart";
            } catch (Exception e) {
                return "error/404";
            }
        }
        return "error/404";
    }

}