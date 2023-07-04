package org.wirehood.productservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wirehood.productservice.dto.ProductRequest;
import org.wirehood.productservice.dto.ProductResponse;
import org.wirehood.productservice.model.Product;
import org.wirehood.productservice.repository.ProductRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.insert(product);
        log.info("Product was saved !");
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productResponseList = productRepository.findAll();
        return productResponseList.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
