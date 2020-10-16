package com.gang.alphaspoon.domain.repository;
import com.gang.alphaspoon.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long > {
}
