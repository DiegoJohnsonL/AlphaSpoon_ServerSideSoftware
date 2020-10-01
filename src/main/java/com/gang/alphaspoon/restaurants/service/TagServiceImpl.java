package com.gang.alphaspoon.restaurants.service;

import com.gang.alphaspoon.exceptions.ResourceNotFoundException;
import com.gang.alphaspoon.restaurants.domain.model.Tag;
import com.gang.alphaspoon.restaurants.domain.repository.ProductRepository;
import com.gang.alphaspoon.restaurants.domain.repository.TagRepository;
import com.gang.alphaspoon.restaurants.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Page<Tag> getAllTagsByProductId(Long productId, Pageable pageable) {
        return productRepository.findById(productId).map(product->{
            List<Tag> tags = product.getTags();
            return new PageImpl<>(tags, pageable, tags.size());
        }).orElseThrow(()-> new ResourceNotFoundException("Product"+"Id"+productId));
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag", "Id", tagId));
    }

    @Override
    @Transactional
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long tagId, Tag tagDetails) {
        return tagRepository.findById(tagId).map(tag -> {
            tag.setName(tagDetails.getName());
            return tagRepository.save(tag);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tag", "Id", tagId));
    }

    @Override
    public ResponseEntity<?> deleteTag(Long tagId) {
        return tagRepository.findById(tagId).map(tag -> {
            tagRepository.delete(tag);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tag", "Id", tagId));
    }
}
