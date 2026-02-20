package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetAllProducts_RunSuccessfully() throws Exception {

        //arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Macbook");
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Ipad");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productService.getAllProducts()).thenReturn(productList);
        ProductDto productDto1 = new ProductDto();
        productDto1.setId(1L);
        productDto1.setName("Macbook");
        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("Ipad");
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto1);
        productDtoList.add(productDto2);


        String expectedResponse = objectMapper.writeValueAsString(productDtoList);

        //act & assert
        mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(expectedResponse))
                .andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].name").value("Macbook"));

    }

    @Test
    public void Test_CreateProduct_WithValidBody_RunSuccessfully() throws Exception {
        //arrange
        ProductDto productDto = new ProductDto();
        productDto.setName("IPencil");
        productDto.setId(2L);
        productDto.setPrice(25000D);

        Product product = new Product();
        product.setId(2L);
        product.setName("IPencil");
        product.setPrice(25000D);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("IPencil"));
    }
}
