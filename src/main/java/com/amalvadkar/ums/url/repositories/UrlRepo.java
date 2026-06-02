package com.amalvadkar.ums.url.repositories;

import com.amalvadkar.ums.url.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UrlRepo extends JpaRepository<UrlEntity, Long>, JpaSpecificationExecutor<UrlEntity> {
    boolean existsBySlug(String slug);
}