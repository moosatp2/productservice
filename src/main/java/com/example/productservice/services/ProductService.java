package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService implements IProductService{


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private List<Category> categoryList;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.categoryList = new ArrayList<>();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getSingleProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product;
    }

    @Override
    public Product addNewProduct(Product product) {

        List<Category> categoryList = product.getCategoryList();
        if (!categoryList.isEmpty()) {

            List<Category> savedCategories = new ArrayList<>();

            for (Category category : categoryList) {
                // Assuming categoryRepository.findByName returns a List<Category>
                List<Category> existingCategories = categoryRepository.findByName(category.getName());

                if (!existingCategories.isEmpty()) {
                    savedCategories.add(existingCategories.get(0)); // Assuming you want to add the first matching category
                } else {
                    // If the category doesn't exist, you might want to save it first
                    savedCategories.add(categoryRepository.save(category));
                }

                // product.setCategoryList(categoryList);
                product.setCategoryList(savedCategories);

            }
        }

            return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id);

       if (optionalProduct.isPresent()) {

            Product savedProduct = optionalProduct.get();

            if (product.getTitle() != null) {
                savedProduct.setTitle(product.getTitle());
            }

            if (product.getDescription() != null) {
                savedProduct.setDescription(product.getDescription());
            }

            if (product.getPrice() != null) {
                savedProduct.setPrice(product.getPrice());
            }

            if (product.getImageUrl() != null) {
                savedProduct.setImageUrl(product.getImageUrl());
            }

            if (product.getCategoryList() !=null ){
                savedProduct.setCategoryList(product.getCategoryList());
            }
            
            return productRepository.save(savedProduct);
      }
        else throw new NoSuchElementException("Product with ID " + id + " not found");

    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);

    }
}
