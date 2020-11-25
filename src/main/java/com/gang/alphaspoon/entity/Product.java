package com.gang.alphaspoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="products")
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
}
