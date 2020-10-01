package com.gang.alphaspoon.restaurants.service;

import com.gang.alphaspoon.exception.ResourceNotFoundException;
import com.gang.alphaspoon.restaurants.domain.model.Product;
import com.gang.alphaspoon.restaurants.domain.repository.ProductRepository;
import com.gang.alphaspoon.restaurants.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product with"+ productId + "not found"  ));
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product productRequest) {
        return productRepository.findById(productId).map(product -> {
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Product with"+ productId + "not found"  ));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Product with"+ productId + "not found"  ));
    }
}
