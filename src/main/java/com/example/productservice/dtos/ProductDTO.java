package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter

public class ProductDTO implements Serializable {
    private Long Id;
    private String title;
    private String description;
//    private List<String> categoryNames; // Assuming you only need category names
    private Double price;
    private String imageUrl;
    // Getters and setters
}