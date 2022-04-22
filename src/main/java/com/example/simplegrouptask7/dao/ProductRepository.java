package com.example.simplegrouptask7.dao;

import com.example.simplegrouptask7.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.title = ?1 and p.price = ?2")
    Product findProductByTitleAndPrice(String title, int price);

}
