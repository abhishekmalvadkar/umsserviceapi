package com.amalvadkar.ums.common.entities;

import jakarta.persistence.*;

@MappedSuperclass
public class AbstractIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
}
