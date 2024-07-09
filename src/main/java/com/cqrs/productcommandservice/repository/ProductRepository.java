package com.cqrs.productcommandservice.repository;

import com.cqrs.productcommandservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
