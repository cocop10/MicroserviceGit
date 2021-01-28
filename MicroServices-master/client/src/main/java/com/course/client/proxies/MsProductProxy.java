package com.course.client.proxies;

import com.course.client.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-product", url = "localhost:8089")
public interface MsProductProxy {

    @GetMapping(value = "/products")
    public List<ProductBean> list();

    @GetMapping(value = "/product/{id}")
    public Optional<ProductBean> get(@PathVariable Long id);
}
