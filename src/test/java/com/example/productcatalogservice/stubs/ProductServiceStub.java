package com.example.productcatalogservice.stubs;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service
//@Primary
public class ProductServiceStub implements IProductService {
    Map<Long, Product> productMap = new HashMap<>();

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productMap.values();
    }

    @Override
    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product input) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
