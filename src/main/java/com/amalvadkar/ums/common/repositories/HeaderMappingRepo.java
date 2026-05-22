package com.amalvadkar.ums.common.repositories;

import com.amalvadkar.ums.common.entities.HeaderMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeaderMappingRepo extends JpaRepository<HeaderMappingEntity, Long> {

    @Query("""
    select hm from HeaderMappingEntity hm
    join fetch hm.headerConfig hc
    join fetch hm.roleMenu rm
    join fetch hc.optionSource
    where rm.role.id = :roleId
    and rm.menu.id = :menuId
    and hm.deleteFlag = false
    and hc.deleteFlag = false
    and rm.deleteFlag = false
    order by hm.displayOrder asc
    """)
    List<HeaderMappingEntity> findHeaderMappings(
            @Param("roleId") Long roleId,
            @Param("menuId") Long menuId
    );
}