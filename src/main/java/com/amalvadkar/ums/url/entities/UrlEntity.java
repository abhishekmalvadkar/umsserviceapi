package com.amalvadkar.ums.url.entities;

import com.amalvadkar.ums.common.entities.AbstractAuditEntity;
import com.amalvadkar.ums.common.entities.UrlStatusEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static com.amalvadkar.ums.url.enums.UrlStatusEnum.IN_ACTIVE;

@Getter
@Setter
@Entity
@Table(name = "urls")
public class UrlEntity extends AbstractAuditEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "slug", nullable = false)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_status_id", nullable = false)
    private UrlStatusEntity urlStatus;

    public boolean isInActive(){
        return IN_ACTIVE.id().equals(urlStatus.getId());
    }


}