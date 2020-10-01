package com.gang.alphaspoon.restaurants.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gang.alphaspoon.model.AuditModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)  // Un post puede tener varios comentarios - muchos a uno - Al crear no quiero un comment sin un post(optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false) // cual va a ser la columna que hara de foreing key en la tabla de comment
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore // en caso se creo un archivo JSON, no agregar la columna post
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_tags", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name="tag_id")})
    @JsonIgnore
    private List<Tag> tags;

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

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
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
}
