package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        Product product = new Product();
        product.setName("IPhone");
        product.setDescription("My new Iphone");
        product.setId(1L);
        product.setPrice(5500D);
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable(value = "id") Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return from(product);
        }
        else{
            return null;
        }
//        Product product = new Product();
//        product.setId(productId);
//        product.setName("IPhone");
//        product.setDescription("My new Iphone");
//        product.setPrice(5500D);
//        return product;

    }

    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);

        }

        return productDto;

    }
}

