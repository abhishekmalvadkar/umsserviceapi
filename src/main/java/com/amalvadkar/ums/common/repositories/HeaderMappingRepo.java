package com.amalvadkar.ums.common.repositories;

import com.amalvadkar.ums.common.entities.HeaderMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HeaderMappingRepo extends JpaRepository<HeaderMappingEntity, Long> {

    @Query("""
    select hm from HeaderMappingEntity hm
    join fetch hm.headerConfig hc
    join fetch hm.roleMenu rm
    left join fetch hc.optionSource
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

    @Query("""
    select hm from HeaderMappingEntity hm
    join hm.roleMenu rm
    where hm.headerConfig.id = :headerConfigId
    and rm.role.id = :roleId
    and rm.menu.id = :menuId
    and hm.deleteFlag = false
    """)
    Optional<HeaderMappingEntity> findHeaderMappingBy(@Param("headerConfigId") Long headerConfigId,
                                                      @Param("menuId") Long menuId,
                                                      @Param("roleId") Long roleId);
}