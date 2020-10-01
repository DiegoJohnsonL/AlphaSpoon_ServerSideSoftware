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
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/products")
    public Page<ProductResource> getAllProducts(Pageable pageable){
        List<ProductResource> resources = productService.getAllProducts(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }
    @GetMapping("/products/{productId}")
    public ProductResource getProductById(@PathVariable(name = "productId") Long productId){
        return convertToResource(productService.getProductById(productId));
    }

    @PostMapping("/products")
    public ProductResource createProduct(@Valid @RequestBody SaveProductResource productResource){
        return convertToResource(productService.createProduct(convertToEntity(productResource)));
    }

    @PutMapping("/products/{productId}")
    public ProductResource updateProduct(@PathVariable(name = "productId")Long productId, SaveProductResource productResource){
        return convertToResource(productService.updateProduct(productId, convertToEntity(productResource)));
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") Long productId){
        return productService.deleteProduct(productId);
    }
    @GetMapping("/tags/{tagId}/products")
    public Page<ProductResource> getAllProductsByTagId(@PathVariable(name = "tagId") Long tagId, Pageable pageable){
        Page<Product> productPage = productService.getAllProductsByTagId(tagId, pageable);
        List<ProductResource> resources = productPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @PostMapping("products/{productId}/tags/{tagId}")
    public ProductResource assignProductTag(@PathVariable(name = "productId") Long productId,
                                            @PathVariable(name = "tagId") Long tagId){
        return  convertToResource(productService.assignProductTag(productId, tagId));
    }
    @DeleteMapping("/products/{productId}/tags/{tagId}")
    public ProductResource unassignPostTag(@PathVariable(name = "productId") Long productId,
                                        @PathVariable(name = "tagId") Long tagId){
        return  convertToResource(productService.assignProductTag(productId, tagId));
    }


    private Product convertToEntity(@Valid SaveProductResource resource){return mapper.map(resource,Product.class);}
    private ProductResource convertToResource(Product entity){return mapper.map(entity, ProductResource.class);}
}
