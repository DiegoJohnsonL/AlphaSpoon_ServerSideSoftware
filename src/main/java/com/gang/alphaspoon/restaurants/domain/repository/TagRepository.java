package com.gang.alphaspoon.restaurants.domain.repository;
import com.gang.alphaspoon.restaurants.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long > {
}
