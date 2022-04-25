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

    @GetMapping("/products")
    public List<Product> showAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(name="id") Long id) {
        Product product = productService.findByIdProduct(id);
        if(product == null) {
            throw new EntityNotFoundException("There is no product with id = " + id);
        }
        return product;
    }

    //todo Запрос на создание таблицы, приведённый в script.txt позволяет создать, например, продукт:
    // {
    //    "title":null,
    //    "price":100
    // }
    // Как следствие, переопределённый метод equals в сущность Product отрабатывает как реализован
    // и два таких объекта не считает equals друг другу.
    // Можно создать сколько угодно таких некорректных объектов в БД.
    // Метод называется savaOrUpdateProduct().
    // Судя по названию, метод предназначен для создания или обновления продуктов в БД.
    // При этом, по факту данный метод никакого обновления не выполняет он только создаёт новый продукт,
    // если нет точного совпадения по title и price. Либо бросает исключение, если такое совпадение есть.
    // Как тогда обновить цену у существующего уже продукта?
    // Есть EntityExistsException. Создавать свои ексепшены это хорошо.
    // Но для многих ситуаций уже есть сформированные эксепшены. Если работаем с подключенной библиотекой,
    // то надо бы их использовать.
    @PostMapping("/products")
    public Product saveOrUpdateProduct(@RequestBody Product product) {
        Product productFromDB = productService.findProductByTitleAndPrice(product.getTitle(), product.getPrice());
        if(product.equals(productFromDB)) {
            throw new IsSuchProductException("there is such a product in database");
        }
        productService.saveOrUpdateProduct(product);
        return product;
    }

    // todo Ранее был уже комментарий про EntityNotFoundException.
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
