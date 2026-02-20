package com.example.productcatalogservice.repos;

import com.example.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testJpaMethods() {
        String productList = productRepo.getMeNameOfProductWhosePriceIs(60000D);
        System.out.println(productList);
    }

}