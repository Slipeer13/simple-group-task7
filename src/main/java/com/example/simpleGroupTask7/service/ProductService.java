package com.example.simpleGroupTask7.service;


import com.example.simpleGroupTask7.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    Product findByIdProduct(long id);

    void deleteByIdProduct(long id);

    void saveOrUpdateProduct(Product product);



}
