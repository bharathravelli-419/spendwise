package com.spendwise.transaction.category.api.dto;

import java.util.UUID;

public record CategoryResponse(
    UUID id,
    String name,
    UUID parentId,
    String icon,
    String color
) {
}
