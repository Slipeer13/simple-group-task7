package com.example.simplegrouptask7.service;


import com.example.simplegrouptask7.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    Product findByIdProduct(long id);

    void deleteByIdProduct(long id);

    void saveOrUpdateProduct(Product product);

    Product findProductByTitleAndPrice(String title, int price);
}
