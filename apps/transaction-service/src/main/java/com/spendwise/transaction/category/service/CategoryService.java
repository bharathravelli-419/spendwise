package com.spendwise.transaction.category.service;

import com.spendwise.transaction.category.api.CategoryMapper;
import com.spendwise.transaction.category.api.dto.CategoryResponse;
import com.spendwise.transaction.category.api.dto.CreateCategoryRequest;
import com.spendwise.transaction.category.domain.Category;
import com.spendwise.transaction.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryResponse create(UUID userId, CreateCategoryRequest request){
        Category category = categoryMapper.toEntity(request);
        category.setUserId(userId);
        if(request.parentId() != null){
            Category parentCategory = categoryRepository.findById(request.parentId())
                .filter(p -> p.getUserId().equals(userId) && p.getDeletedAt() == null)
                .orElseThrow(()-> new RuntimeException("parent category not found with id: "+request.parentId()));
            category.setParent(parentCategory);
        }
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Transactional
    public List<CategoryResponse> getAll(UUID userId){
        List<Category> categories =categoryRepository.findByUserIdAndDeletedAtIsNull(userId);
        return categories.stream().map(categoryMapper::toResponse).toList();
    }


}
