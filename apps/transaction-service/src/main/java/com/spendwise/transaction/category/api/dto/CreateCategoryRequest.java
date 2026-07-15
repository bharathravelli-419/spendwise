package com.spendwise.transaction.category.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateCategoryRequest(
    @NotBlank
    @Size(max = 120)
    String name,
    UUID parentId,
    String icon,
    String color
){}
