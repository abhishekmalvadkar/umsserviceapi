package com.amalvadkar.ums.common.repositories;

import com.amalvadkar.ums.common.entities.UrlStatusEntity;
import com.amalvadkar.ums.common.model.response.dto.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UrlStatusRepo extends JpaRepository<UrlStatusEntity, Long> {

    @Query("""
    select
        new com.amalvadkar.ums.common.model.response.dto.KeyValue
        (
            us.id,
            us.name
        )
    from UrlStatusEntity us
    where us.deleteFlag = false
    """)
    List<KeyValue<Long, String>> findUrlStatusList();

}