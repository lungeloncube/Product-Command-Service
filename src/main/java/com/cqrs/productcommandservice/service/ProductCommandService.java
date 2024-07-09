package com.cqrs.productcommandservice.service;

import com.cqrs.productcommandservice.dto.ProductEvent;
import com.cqrs.productcommandservice.repository.ProductRepository;
import com.cqrs.productcommandservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    @Autowired
     private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    public Product createProduct(ProductEvent productEvent){
        Product productDO= productRepository.save(productEvent.getProduct());
        ProductEvent event= new ProductEvent("CreateProduct",productDO);
        kafkaTemplate.send("product-event-topic",event);
        return productDO;
    }

    public Product updateProduct(Long id, ProductEvent productEvent){
       Product existingProduct= productRepository.findById(id).get();

       Product product= productEvent.getProduct();
       existingProduct.setName(product.getName());
       existingProduct.setDescription(product.getDescription());
       existingProduct.setPrice(product.getPrice());

       Product productDO = productRepository.save(existingProduct);
        ProductEvent event= new ProductEvent("CreateProduct",productDO);
        kafkaTemplate.send("product-event-topic",event);

       return productDO;


    }

}
