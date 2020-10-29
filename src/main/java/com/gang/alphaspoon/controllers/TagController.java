package com.gang.alphaspoon.controllers;

import com.gang.alphaspoon.dtos.requests.TagRequest;
import com.gang.alphaspoon.dtos.resources.TagResource;
import com.gang.alphaspoon.entity.Tag;
import com.gang.alphaspoon.services.TagService;
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
public class TagController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public Page<TagResource> getAllTags(Pageable pageable) {
        List<TagResource> resources = tagService.getAllTags(pageable).getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }


    @GetMapping("/products/{productId}/tags")
    public Page<TagResource> getTagsByProductId(@PathVariable(name = "productId") Long productId, Pageable pageable){
        List<TagResource> tags = tagService.getAllTagsByProductId(productId,pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(tags,pageable, tags.size());
    }

    @GetMapping("/tags/{tagId}")
    public TagResource getTagById(@PathVariable(name = "tagId") Long tagId){
        return convertToResource(tagService.getTagById(tagId));
    }

    @PostMapping("/tags")
    public TagResource createTag(@Valid @RequestBody TagRequest tagResource){
        return convertToResource(tagService.createTag(convertToEntity(tagResource)));
    }

    @PutMapping("/tags/{tagId}")
    public TagResource updateTag(@PathVariable(name = "tagId") Long tagId, TagRequest resource){
        return convertToResource(tagService.updateTag(tagId, convertToEntity(resource)));
    }

    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "tagId") Long tagId){
        return tagService.deleteTag(tagId);
    }


    private Tag convertToEntity(TagRequest resource){ return mapper.map(resource, Tag.class);   }
    private TagResource convertToResource(Tag entity){return mapper.map(entity, TagResource.class);  }

}