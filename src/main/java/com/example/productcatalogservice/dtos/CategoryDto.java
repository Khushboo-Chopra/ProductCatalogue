package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;
}
