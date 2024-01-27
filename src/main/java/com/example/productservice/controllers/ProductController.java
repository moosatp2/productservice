package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotExistException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {

        ResponseEntity<List<Product>> getAllProduct =
                new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return getAllProduct;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getSingleProduct(
            @PathVariable("id") Long id) throws ProductNotExistException {
        ResponseEntity<Optional<Product>> productResponseEntity =
                new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        return productResponseEntity;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {

        ResponseEntity postResponse = new ResponseEntity(productService.addNewProduct(product), HttpStatus.OK);

        return postResponse;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotExistException {

        ResponseEntity updateResponse =
                new ResponseEntity(productService.updateProduct(id, product), HttpStatus.OK);
        return updateResponse;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
