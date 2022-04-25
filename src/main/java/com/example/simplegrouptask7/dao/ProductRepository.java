package com.example.simplegrouptask7.dao;

import com.example.simplegrouptask7.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //todo JpaRepository умный. В данном случае можно не формировать метод из запроса.
    // Спринг понимает, что в данном случае должен возвращать и как формировать запрос,
    // если следовать правилам формирования названий методов репозитория.
    // Например, можно было сформировать следующий метод вместо реализованного:
    // Product findByTitleAndPrice(String title, Integer price)
    // либо Optional<Product> findByTitleAndPrice(String title, Integer price)

    Product findByTitleAndPrice(String title, int price);

}
