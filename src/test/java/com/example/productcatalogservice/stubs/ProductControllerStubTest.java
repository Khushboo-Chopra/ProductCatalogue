package com.example.productcatalogservice.stubs;

import com.example.productcatalogservice.controllers.ProductController;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductControllerStubTest {
    @Autowired
    ProductController productController;

    @Autowired
    IProductService productService;
}
