package com.example.productservice.services;

import com.example.productservice.models.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();
    List<Category> getSingleCategory(String name);
}
