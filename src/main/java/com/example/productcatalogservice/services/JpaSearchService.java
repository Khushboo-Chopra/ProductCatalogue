package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.SortOrder;
import com.example.productcatalogservice.dtos.SortParam;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaSearchService implements ISearchService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParamList) {
        //Sort sort = Sort.by("price").and(Sort.by("id")).descending();
        List<Sort.Order> orders = new ArrayList<>();
        for (SortParam sortParam : sortParamList) {
            Sort.Order order;
            if (sortParam.getSortOrder() == SortOrder.DESC) {
                order = Sort.Order.desc(sortParam.getParamName());
            } else {
                order = Sort.Order.asc(sortParam.getParamName());
            }

            orders.add(order);
        }

        Sort sort = Sort.by(orders);

        return productRepo.findByName(query, PageRequest.of(pageNumber, pageSize, sort));
    }

}
