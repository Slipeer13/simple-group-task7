package com.example.simpleGroupTask7.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer price;

    public Product() {
    }

    //todo Не используется этот конструктор. Зачем он?
    public Product(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

}
