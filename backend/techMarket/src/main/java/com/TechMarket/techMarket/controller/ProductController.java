package com.TechMarket.techMarket.controller;

import com.TechMarket.techMarket.dao.ProductCategoryRepository;
import com.TechMarket.techMarket.dao.ProductRepository;
import com.TechMarket.techMarket.entity.NewProduct;
import com.TechMarket.techMarket.entity.Product;
import com.TechMarket.techMarket.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4030")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @PostMapping("/addProduct")
    public <ProductData> ResponseEntity<Product> addProduct(@RequestBody NewProduct productData) {

        ProductCategory productCategory = productCategoryRepository.findById(productData.getCategory())
                .orElseThrow(() -> new RuntimeException("ProductCategory with ID " + productData.getCategory() + " not found"));

        Product product = new Product();
        // Map fields from productData to product
        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        product.setUnitPrice(productData.getUnitPrice());
        product.setUnitsInStock(productData.getUnitsInStock());
        product.setImageUrl(productData.getImageUrl());
        product.setCategory(productCategory);
        product.setActive(true);


        // Save the new product
        Product savedProduct = productRepository.save(product);

        // Return the created product or appropriate response
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

}
