package com.example.simpleGroupTask7.dao;

import com.example.simpleGroupTask7.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
