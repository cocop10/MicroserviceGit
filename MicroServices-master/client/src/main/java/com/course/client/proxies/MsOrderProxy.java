package com.course.client.proxies;

import com.course.client.beans.CartBean;
import com.course.client.beans.CartItemBean;
import com.course.client.beans.OrderBean;
import com.course.client.beans.OrderItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-order", url = "localhost:8086")
public interface MsOrderProxy {


    @PostMapping(value = "/order")
    ResponseEntity<OrderBean> createNewOrder(@RequestBody OrderBean orderDomain);


    @GetMapping(value = "/orders")
    public List<OrderBean> getOrderList();

    @GetMapping(value = "/order/{id}")
    public Optional<OrderBean> getOrder(@PathVariable Long id);

    @GetMapping(value = "/ordersByIdCart/{idCart}")
    public List<OrderBean> getOrderByCartIdList(@PathVariable Long idCart);

    @PostMapping(value = "/order/{orderItemId}")
    ResponseEntity<OrderItemBean> addOrderItemToOrder(@PathVariable Long orderItemId,@RequestBody OrderItemBean orderItemBean);



}


