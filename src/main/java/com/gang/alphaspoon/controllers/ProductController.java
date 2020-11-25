package com.gang.alphaspoon.controllers;


import com.gang.alphaspoon.converters.ProductConverter;
import com.gang.alphaspoon.dtos.ProductDTO;
import com.gang.alphaspoon.entity.Product;
import com.gang.alphaspoon.services.ProductService;
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
    private ProductConverter converter;

    @GetMapping("/restaurants/{restaurantId}/products")
    public ResponseEntity<WrapperResponse<Page<ProductDTO>>> getAllProductsByRestaurantId(@PathVariable(name = "restaurantId") Long restaurantId, Pageable pageable){
        List<ProductDTO> resources = productService.getAllProductsByRestaurantId(restaurantId, pageable).getContent()
                .stream().map(converter::fromEntity).collect(Collectors.toList());
        Page<ProductDTO> page = new PageImpl<>(resources, pageable, resources.size());
        return new WrapperResponse<>(true, "success", page).createResponse();
    }

    @GetMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<WrapperResponse<ProductDTO>> getProductByIdAndRestaurantId(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                                                      @PathVariable(name = "productId") Long productId){
        return new WrapperResponse<>(true, "success",
                converter.fromEntity(productService.getProductByIdAndRestaurantId(productId, restaurantId))).createResponse();
    }

    @PostMapping("/restaurants/{restaurantId}/products")
    public ResponseEntity<WrapperResponse<ProductDTO>> createProduct(@PathVariable(name = "restaurantId") Long restaurantId, @Valid @RequestBody ProductDTO productRequest){
        return new WrapperResponse<>(true, "success",
                converter.fromEntity(productService.createProduct(restaurantId, converter.fromDTO(productRequest)))).createResponse();
    }

    @PutMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<WrapperResponse<ProductDTO>> updateProduct(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                          @PathVariable(name = "productId") Long productId, ProductDTO productResource){

        return new WrapperResponse<>(true, "success",
                converter.fromEntity(productService.updateProduct(restaurantId, productId, converter.fromDTO(productResource)))).createResponse();
    }

    @DeleteMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "restaurantId") Long restaurantId, @PathVariable(name = "productId") Long productId){
        return productService.deleteProduct(restaurantId, productId);
    }

    @GetMapping("/tags/{tagId}/products")
    public ResponseEntity<WrapperResponse<Page<ProductDTO>>> getAllProductsByTagId(@PathVariable(name = "tagId") Long tagId, Pageable pageable){
        Page<Product> productPage = productService.getAllProductsByTagId(tagId, pageable);
        List<ProductDTO> resources = productPage.getContent().stream()
                .map(converter::fromEntity).collect(Collectors.toList());
        Page<ProductDTO> page = new PageImpl<>(resources, pageable, resources.size());
        return new WrapperResponse<>(true, "success", page).createResponse();
    }

    @PostMapping("/products/{productId}/tags/{tagId}")
    public ResponseEntity<WrapperResponse<ProductDTO>> assignProductTag(@PathVariable(name = "productId") Long productId,
                                            @PathVariable(name = "tagId") Long tagId){
        return new WrapperResponse<>(true, "success", converter.fromEntity(productService.assignProductTag(productId, tagId))).createResponse();
    }

    @DeleteMapping("/products/{productId}/tags/{tagId}")
    public ResponseEntity<WrapperResponse<ProductDTO>> unassignPostTag(@PathVariable(name = "productId") Long productId,
                                        @PathVariable(name = "tagId") Long tagId){
        return new WrapperResponse<>(true, "success", converter.fromEntity(productService.assignProductTag(productId, tagId))).createResponse();
    }
}
