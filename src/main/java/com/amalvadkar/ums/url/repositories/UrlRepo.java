package com.amalvadkar.ums.url.repositories;

import com.amalvadkar.ums.url.entities.UrlEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UrlRepo extends JpaRepository<UrlEntity, Long>, JpaSpecificationExecutor<UrlEntity> {

    @Override
    @EntityGraph(attributePaths = {
            "urlStatus"
    })
    List<UrlEntity> findAll(Specification<UrlEntity> spec);

    boolean existsBySlug(String slug);

    Optional<UrlEntity> findBySlugAndDeleteFlagIsFalse(String slug);

    default Optional<UrlEntity> findBySlug(String slug){
        return findBySlugAndDeleteFlagIsFalse(slug);
    }
}