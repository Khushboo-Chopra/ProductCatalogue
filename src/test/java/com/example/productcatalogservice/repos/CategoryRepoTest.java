package com.example.productcatalogservice.repos;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testFetchTypes() {
        Category category = categoryRepo.findById(10L).get();
        for (Product product : category.getProducts()) {
            System.out.println(product.getName());
        }
    }

    @Test
    @Transactional
    public void testNPlusOne() {
        List<Category> categoryList = categoryRepo.findAll();
        for (Category category : categoryList) {
            System.out.println(category.getName());
            for (Product product : category.getProducts()) {
                System.out.println(product.getName());
            }
        }
    }

}