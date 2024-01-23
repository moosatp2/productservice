package com.example.productservice.services;

import com.example.productservice.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class categoryService implements ICategoryService{
    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getSingleCategory(String name) {
        return null;
    }
}
