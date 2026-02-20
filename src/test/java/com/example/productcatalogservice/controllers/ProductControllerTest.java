package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithValidId_ReturnProductSuccessfully() {
//Arrange
        // i have to mock the response
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        product.setPrice(100000D);
        when(productService.getProductById(id)).thenReturn(product);


        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity = productController.getProductById(id);


        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(id, productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone", productDtoResponseEntity.getBody().getName());
        assertEquals(100000D, productDtoResponseEntity.getBody().getPrice());
        assertEquals(HttpStatus.OK, productDtoResponseEntity.getStatusCode());
        assertNull(productDtoResponseEntity.getBody().getCategory());
        verify(productService, times(1)).getProductById(id);


    }

    @Test
    public void Test_GetProductById_WithNegativeId_ResultsInIllegalArgumentException() {
        //arrange
        Long id = -1L;
        //act & assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> productController.getProductById(id));

        assertEquals("Invalid id", e.getMessage());
        verify(productService, times(0)).getProductById(id);

    }

    @Test
    public void Test_GetProductById_WhereProductServiceThrowsRuntimeException_ResultsInRuntimeException() {
        //arrange
        Long id = 100L;
        //act
        when(productService.getProductById(id)).thenThrow(RuntimeException.class);
        //assert
        assertThrows(RuntimeException.class, () -> productController.getProductById(id));

    }
}