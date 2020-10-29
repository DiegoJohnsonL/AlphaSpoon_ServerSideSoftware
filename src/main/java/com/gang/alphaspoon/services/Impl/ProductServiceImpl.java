package com.gang.alphaspoon.services.Impl;

import com.gang.alphaspoon.entity.Product;
import com.gang.alphaspoon.entity.Tag;
import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.NoDataFoundException;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.repository.ProductRepository;
import com.gang.alphaspoon.repository.RestaurantRepository;
import com.gang.alphaspoon.repository.TagRepository;
import com.gang.alphaspoon.services.ProductService;
import com.gang.alphaspoon.validators.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public Page<Product> getAllProductsByRestaurantId(Long restaurantId, Pageable pageable) {
        try{
            return productRepository.findByRestaurantId(restaurantId, pageable);
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Product getProductByIdAndRestaurantId(Long productId, Long restaurantId) {
        try{
            return productRepository.findByIdAndRestaurantId(productId, restaurantId)
                    .orElseThrow(()->new NoDataFoundException("Product with"+ productId + "not found"  ));
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Product createProduct(Long restaurantId, Product product) {
        try{
            ProductValidator.validate(product);
            return restaurantRepository.findById(restaurantId).map(restaurant -> {
                product.setRestaurant(restaurant);
                return productRepository.save(product);
            }).orElseThrow(() -> new NoDataFoundException( "Restaurant", "Id", restaurantId));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
    @Transactional
    @Override
    public Product updateProduct(Long restaurantId, Long productId, Product productRequest) {
        try{
            ProductValidator.validate(productRequest);
            if(!restaurantRepository.existsById(restaurantId))
                throw new NoDataFoundException("Restaurant", "Id", restaurantId);
            return productRepository.findById(productId).map(product -> {
                product.setName(productRequest.getName());
                product.setPrice(productRequest.getPrice());
                return productRepository.save(product);
            }).orElseThrow(()->new NoDataFoundException("Product with"+ productId + "not found"  ));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
    @Transactional
    @Override
    public ResponseEntity<?> deleteProduct(Long restaurantId, Long productId) {
        try{
            return productRepository.findByIdAndRestaurantId(productId, restaurantId).map(product -> {
                productRepository.delete(product);
                return ResponseEntity.ok().build();
            }).orElseThrow(()->new NoDataFoundException("Product with"+ productId + "not found"  ));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Product assignProductTag(Long productId, Long tagId) {
        try{
            Tag tag = tagRepository.findById(tagId).orElseThrow(()-> new NoDataFoundException("Tag", "Id", tagId));
            return productRepository.findById(productId).map(product -> {
                return productRepository.save(product.tagWith(tag));
            }).orElseThrow(() -> new NoDataFoundException("Product", "Id", productId));
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Product unassignProductTag(Long productId, Long tagId) {
        try{
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new NoDataFoundException(
                            "Tag", "Id", tagId));
            return productRepository.findById(productId).map(product -> {
                return productRepository.save(product.unTagWith(tag));
            }).orElseThrow(() -> new NoDataFoundException("product", "Id", productId));
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Page<Product> getAllProductsByTagId(Long tagId, Pageable pageable) {
        try{
            return tagRepository.findById(tagId).map( tag -> {
                List<Product> products = tag.getProducts();
                return new PageImpl<>(products, pageable, products.size());
            }).orElseThrow(() -> new NoDataFoundException("Tag", "Id", tagId));
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
}
