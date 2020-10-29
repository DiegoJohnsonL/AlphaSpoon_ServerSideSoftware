package com.gang.alphaspoon.repository;
import com.gang.alphaspoon.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TagRepository extends JpaRepository<Tag, Long > {
}
