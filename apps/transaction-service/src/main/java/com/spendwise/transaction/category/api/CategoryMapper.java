package com.spendwise.transaction.category.api;

import com.spendwise.transaction.category.api.dto.CreateCategoryRequest;
import com.spendwise.transaction.category.api.dto.CategoryResponse;
import com.spendwise.transaction.category.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Category toEntity(CreateCategoryRequest createCategoryRequest);
    CategoryResponse toResponse(Category category);
}
