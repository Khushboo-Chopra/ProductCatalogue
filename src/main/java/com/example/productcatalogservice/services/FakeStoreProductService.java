package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = requestForEntity("https://fakestoreapi.com/products/{id}", null, HttpMethod.GET, FakeStoreProductDto.class, id);

        if (fakeStoreProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200)) && fakeStoreProductDto.getBody() != null) {
            return from(fakeStoreProductDto.getBody());
        }
        return null;
        //return from(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product input) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(input);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{id}", fakeStoreProductDtoInput, HttpMethod.PUT, FakeStoreProductDto.class, id);
        return from(fakeStoreProductDtoResponseEntity.getBody());
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, @Nullable Object request, HttpMethod httpMethod, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if (product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

}
