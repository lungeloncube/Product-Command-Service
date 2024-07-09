package com.cqrs.productcommandservice.controller;

import com.cqrs.productcommandservice.dto.ProductEvent;
import com.cqrs.productcommandservice.entity.Product;
import com.cqrs.productcommandservice.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {


    @Autowired
    ProductCommandService service;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent product){
        return service.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable  Long id, @RequestBody ProductEvent product){
        return  service.updateProduct(id,product);


    }
}
