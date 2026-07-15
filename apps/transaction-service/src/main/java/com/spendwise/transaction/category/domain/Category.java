package com.spendwise.transaction.category.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(nullable = false, length = 120)
    private String name;
    @Column(length = 40)
    private String icon;
    @Column(length = 9)
    private String color;
    @Column(name = "created_at",insertable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "deleted_at")
    private Instant deletedAt;
}
