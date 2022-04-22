package com.example.simplegrouptask7.controller;

import com.example.simplegrouptask7.entity.Product;
import com.example.simplegrouptask7.exceptionHandling.IsSuchProductException;
import com.example.simplegrouptask7.exceptionHandling.NoSuchProductException;
import com.example.simplegrouptask7.exceptionHandling.ProductIncorrectData;
import com.example.simplegrouptask7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
        return productService.findAllProducts();
    }

    //todo Есть уже EntityNotFoundException. Свою можно не делать.
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(name="id") Long id) {
        Product product = productService.findByIdProduct(id);
        if(product == null) {
            throw new EntityNotFoundException("There is no product with id = " + id);
        }
        return product;
    }

    //todo Если метод post, почему тогда название метода getProduct?
    //      Если добавлять продукты с одинаковым названием и прайсом, что должно происходить?
    //      Сейчас они просто добавляются, если в бд нет настроек об уникальности каких-либо полей.
    //      Мне кажется, что это не логично.
    //      Для чего слэш в конце адреса?
    @PostMapping("/products")
    public Product saveOrUpdateProduct(@RequestBody Product product) {
        Product productFromDB = productService.findProductByTitleAndPrice(product.getTitle(), product.getPrice());
        if(product.equals(productFromDB)) {
            throw new IsSuchProductException("there is such a product in database");
        }
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
        return "Product " + product.getTitle() + " was deleted";
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

    @ExceptionHandler
    public ResponseEntity<ProductIncorrectData> handleException(IsSuchProductException exception) {
        ProductIncorrectData productIncorrectData =  new ProductIncorrectData(exception.getMessage());
        return new ResponseEntity<>(productIncorrectData, HttpStatus.OK);
    }

}
