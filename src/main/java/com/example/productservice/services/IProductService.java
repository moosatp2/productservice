package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotExistException;
import com.example.productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getSingleProduct(Long id) throws ProductNotExistException;
    Product addNewProduct(Product product);
    Product updateProduct(Long id, Product product) throws ProductNotExistException;
    void deleteProduct(Long id);
}
