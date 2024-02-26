package com.example.productservice.services;

import com.example.productservice.configs.RedisConfig;
import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.exceptions.ProductNotExistException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    private final RedisTemplate<String, Object> redisTemplate;

    private List<Category> categoryList;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, RedisTemplate<String, Object> redisTemplate) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.redisTemplate = redisTemplate;

        this.categoryList = new ArrayList<>();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductDTO> getSingleProduct(Long id) throws ProductNotExistException {

        ProductDTO p = (ProductDTO) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);

        if (p != null ){

            return Optional.of(p);
        }

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotExistException("no product exist with id: " +id);
        }
        Product product = productOptional.get();
        product.getCategoryList().size();

        ProductDTO productDTO = convertToDTO(product);

        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, productDTO);
        return Optional.of(productDTO);
    }
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
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
    public Product updateProduct(Long id, Product product) throws ProductNotExistException {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotExistException("Product  not exist with id: " + id);
        }
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

           if (product.getCategoryList() != null && !product.getCategoryList().isEmpty()) {

               List<Category> categoryList1 = new ArrayList<>();

               for (Category category : product.getCategoryList()) {
                   List<Category> categories = categoryRepository.findByName(category.getName());

                   if (!categories.isEmpty()) {
                       categoryList1.add(categories.get(0));
                   } else {
                       categoryList1.add(categoryRepository.save(category));
                   }
               }

               savedProduct.setCategoryList(categoryList1); // Update savedProduct's categoryList
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
