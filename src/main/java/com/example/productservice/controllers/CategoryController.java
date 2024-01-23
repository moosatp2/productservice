package com.example.productservice.controllers;


import com.example.productservice.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(){
        return null;
    }
    @GetMapping("/{name}")
    public ResponseEntity<Category> getSingleCategory(@PathVariable("name") String name){
        return null;
    }
}
