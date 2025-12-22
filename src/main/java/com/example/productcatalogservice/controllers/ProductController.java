package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(value = "id") Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product id should be greater than 0");
        }
        Product product = productService.getProductById(id);
        if (product != null) {
            ProductDto resp = from(product);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = productService.replaceProduct(id, from(productDto));
        return from(product);
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

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        if (productDto.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(productDto.getCategory().getId());
            categoryDto.setName(productDto.getCategory().getName());
            categoryDto.setDescription(productDto.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }

        return product;
    }


}

