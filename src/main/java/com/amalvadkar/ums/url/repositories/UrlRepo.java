package com.amalvadkar.ums.url.repositories;

import com.amalvadkar.ums.url.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepo extends JpaRepository<UrlEntity, Long> {
    boolean existsBySlug(String slug);
}