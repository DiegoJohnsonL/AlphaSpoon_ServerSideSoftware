package com.gang.alphaspoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "products")
@Builder
public class Product extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_tags", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name="tag_id")})
    @JsonIgnore
    private List<Tag> tags;

    public Product() {
    }

    public Product(Long id, String name, @NotNull Double price, Restaurant restaurant, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
        this.tags = tags;
    }


    public boolean isTaggedWith(Tag tag){       // Business methods
        return (this.getTags().contains(tag));
    }

    public Product tagWith(Tag tag) {
        if(!this.isTaggedWith(tag)) {
            this.getTags().add(tag);
        }
        return this;
    }
    public Product unTagWith(Tag tag) {
        if(this.isTaggedWith(tag)) {
            this.getTags().remove(tag);
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Product setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Product setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }
}
