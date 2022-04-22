package com.example.simpleGroupTask7.dao;

import com.example.simpleGroupTask7.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.title = ?1 and p.price = ?2")
    Product findProductByTitleAndPrice(String title, int price);

}
