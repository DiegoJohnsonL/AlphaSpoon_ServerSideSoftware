package com.gang.alphaspoon.restaurants.domain.service;

import com.gang.alphaspoon.restaurants.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    public Page<Product> getAllProducts(Pageable pageable);
    public Product getProductById(Long productId);
    public Product createProduct(Product product);
    public Product updateProduct(Long productId, Product product);
    public ResponseEntity<?> deleteProduct(Long productId);

}
