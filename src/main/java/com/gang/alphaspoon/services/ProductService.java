package com.gang.alphaspoon.services;

import com.gang.alphaspoon.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Product getProductById(Long productId);
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
    ResponseEntity<?> deleteProduct(Long productId);
    Product assignProductTag(Long productId, Long tagId);
    Product unassignProductTag(Long productId, Long tagId);
    Page<Product> getAllProductsByTagId(Long tagId, Pageable pageable);
}
