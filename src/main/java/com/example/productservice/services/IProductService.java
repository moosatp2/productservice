package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getSingleProduct(Long id);
    Product addNewProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
