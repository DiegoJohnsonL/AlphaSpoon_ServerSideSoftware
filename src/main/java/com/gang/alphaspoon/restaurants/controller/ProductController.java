package com.gang.alphaspoon.restaurants.controller;


import com.gang.alphaspoon.restaurants.domain.model.Product;
import com.gang.alphaspoon.restaurants.domain.service.ProductService;
import com.gang.alphaspoon.restaurants.dto.resource.ProductResource;
import com.gang.alphaspoon.restaurants.dto.save.SaveProductResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<ProductResource> getAllProducts(Pageable pageable){
        List<ProductResource> resources = productService.getAllProducts(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }
    @GetMapping("/{productId}")
    public ProductResource getProductById(@PathVariable(name = "productId") Long productId){
        return convertToResource(productService.getProductById(productId));
    }

    @PostMapping
    public ProductResource createProduct(@Valid @RequestBody SaveProductResource productResource){
        return convertToResource(productService.createProduct(convertToEntity(productResource)));
    }

    @PutMapping("/{productId}")
    public ProductResource updateProduct(@PathVariable(name = "productId")Long productId, SaveProductResource productResource){
        return convertToResource(productService.updateProduct(productId, convertToEntity(productResource)));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") Long productId){
        return productService.deleteProduct(productId);
    }

    private Product convertToEntity(@Valid SaveProductResource resource){return mapper.map(resource,Product.class);}
    private ProductResource convertToResource(Product entity){return mapper.map(entity, ProductResource.class);}
}
