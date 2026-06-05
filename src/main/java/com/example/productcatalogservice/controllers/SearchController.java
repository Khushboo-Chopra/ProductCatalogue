package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.SearchRequestDTO;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDTO searchRequestDTO) {
        return searchService.searchProducts(searchRequestDTO.getQuery(), searchRequestDTO.getPageNumber(), searchRequestDTO.getPageSize(),searchRequestDTO.getSortParamList());
    }
}
