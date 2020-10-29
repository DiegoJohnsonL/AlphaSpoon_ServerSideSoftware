package com.gang.alphaspoon.controllers;


import com.gang.alphaspoon.dtos.requests.AdministratorRequest;
import com.gang.alphaspoon.dtos.resources.AdministratorResource;
import com.gang.alphaspoon.entity.Product;
import com.gang.alphaspoon.services.ProductService;
import com.gang.alphaspoon.dtos.requests.ProductRequest;
import com.gang.alphaspoon.dtos.resources.ProductResource;
import com.gang.alphaspoon.utils.WrapperResponse;
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

    @GetMapping("/restaurants/{restaurantId}/products")
    public ResponseEntity<WrapperResponse<Page<ProductResource>>> getAllProductsByRestaurantId(@PathVariable(name = "restaurantId") Long restaurantId, Pageable pageable){
        List<ProductResource> resources = productService.getAllProductsByRestaurantId(restaurantId, pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        Page<ProductResource> page = new PageImpl<>(resources, pageable, resources.size());
        return new WrapperResponse<>(true, "success", page).createResponse();
    }

    @GetMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<WrapperResponse<ProductResource>> getProductByIdAndRestaurantId(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                                                      @PathVariable(name = "productId") Long productId){
        return new WrapperResponse<>(true, "success",
                convertToResource(productService.getProductByIdAndRestaurantId(productId, restaurantId))).createResponse();
    }

    @PostMapping("/restaurants/{restaurantId}/products")
    public ResponseEntity<WrapperResponse<ProductResource>> createProduct(@PathVariable(name = "restaurantId") Long restaurantId, @Valid @RequestBody ProductRequest productRequest){
        return new WrapperResponse<>(true, "success",
                convertToResource(productService.createProduct(restaurantId, convertToEntity(productRequest)))).createResponse();
    }

    @PutMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<WrapperResponse<ProductResource>> updateProduct(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                          @PathVariable(name = "productId") Long productId, ProductRequest productResource){

        return new WrapperResponse<>(true, "success",
                convertToResource(productService.updateProduct(restaurantId, productId, convertToEntity(productResource)))).createResponse();
    }

    @DeleteMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "restaurantId") Long restaurantId, @PathVariable(name = "productId") Long productId){
        return productService.deleteProduct(restaurantId, productId);
    }

    @GetMapping("/tags/{tagId}/products")
    public ResponseEntity<WrapperResponse<Page<ProductResource>>> getAllProductsByTagId(@PathVariable(name = "tagId") Long tagId, Pageable pageable){
        Page<Product> productPage = productService.getAllProductsByTagId(tagId, pageable);
        List<ProductResource> resources = productPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        Page<ProductResource> page = new PageImpl<>(resources, pageable, resources.size());
        return new WrapperResponse<>(true, "success", page).createResponse();
    }

    @PostMapping("/products/{productId}/tags/{tagId}")
    public ResponseEntity<WrapperResponse<ProductResource>> assignProductTag(@PathVariable(name = "productId") Long productId,
                                            @PathVariable(name = "tagId") Long tagId){
        return new WrapperResponse<>(true, "success", convertToResource(productService.assignProductTag(productId, tagId))).createResponse();
    }

    @DeleteMapping("/products/{productId}/tags/{tagId}")
    public ResponseEntity<WrapperResponse<ProductResource>> unassignPostTag(@PathVariable(name = "productId") Long productId,
                                        @PathVariable(name = "tagId") Long tagId){
        return new WrapperResponse<>(true, "success", convertToResource(productService.assignProductTag(productId, tagId))).createResponse();
    }

    private Product convertToEntity(@Valid ProductRequest resource){return mapper.map(resource,Product.class);}
    private ProductResource convertToResource(Product entity){return mapper.map(entity, ProductResource.class);}
}
