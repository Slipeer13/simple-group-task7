package com.example.simpleGroupTask7.controller;

import com.example.simpleGroupTask7.entity.Product;
import com.example.simpleGroupTask7.exceptionHandling.NoSuchProductException;
import com.example.simpleGroupTask7.exceptionHandling.ProductIncorrectData;
import com.example.simpleGroupTask7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //todo Зачем именованная переменная, можно сразу возвращать результат работы сервиса.
    @GetMapping("/products")
    public List<Product> showAllProducts() {
        List<Product> allProducts = productService.findAllProducts();
        return allProducts;
    }

    //todo Есть уже EntityNotFoundException. Свою можно не делать.
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(name="id") Long id) {
        Product product = productService.findByIdProduct(id);
        if(product == null) {
            throw new NoSuchProductException("There is no product with id = " + id);
        }
        return product;
    }

    //todo Если метод post, почему тогда название метода getProduct?
    //      Если добавлять продукты с одинаковым названием и прайсом, что должно происходить?
    //      Сейчас они просто добавляются, если в бд нет настроек об уникальности каких-либо полей.
    //      Мне кажется, что это не логично.
    //      Для чего слэш в конце адреса?
    @PostMapping("/products/")
    public Product getProduct(@RequestBody Product product) {
        productService.saveOrUpdateProduct(product);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id) {
        Product product = productService.findByIdProduct(id);
        if(product == null) {
            throw new NoSuchProductException("There is no product with id = " + id);
        }
        productService.deleteByIdProduct(id);
        return "Product with id " + id + "was deleted";
    }

    @ExceptionHandler
    public ResponseEntity<ProductIncorrectData> handleException(NoSuchProductException exception) {
        ProductIncorrectData productIncorrectData =  new ProductIncorrectData(exception.getMessage());
        return new ResponseEntity<>(productIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductIncorrectData> handleException(Exception exception) {
        ProductIncorrectData productIncorrectData =  new ProductIncorrectData(exception.getMessage());
        return new ResponseEntity<>(productIncorrectData, HttpStatus.BAD_REQUEST);
    }

}
