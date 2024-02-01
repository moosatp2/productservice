package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotExistException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @MockBean

    private ProductRepository productRepository;

    @Test
    void testGetSingleUser() throws ProductNotExistException {

        //arrange

        Product product = new Product();
        product.setTitle("test product");
        product.setDescription("test desc");

        Optional<Product> optionalProduct = Optional.of(product);

        when(
             productService.getSingleProduct(any())
        ).thenReturn(optionalProduct);

        //act

        ResponseEntity<Optional<Product>> response =
                productController.getSingleProduct(100L);
        //assert

        assertEquals(optionalProduct, response.getBody());

    }


}