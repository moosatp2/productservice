package com.example.productservice.controllers;


import com.example.productservice.models.Category;
import com.example.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(){

    ResponseEntity<List<Category>> getResponse =
        new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    return  getResponse;
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<Category>> getSingleCategory(@PathVariable("name") String name){

        ResponseEntity<List<Category>> getResponse =
                new ResponseEntity<>(categoryService.getSingleCategory(name), HttpStatus.OK);
        return getResponse;
    }
}
