package com.spendwise.transaction.category.api;

import com.spendwise.transaction.api.dto.CreateTransactionRequest;
import com.spendwise.transaction.api.dto.TransactionResponse;
import com.spendwise.transaction.api.helper.Idempotency;
import com.spendwise.transaction.category.api.dto.CategoryResponse;
import com.spendwise.transaction.category.api.dto.CreateCategoryRequest;
import com.spendwise.transaction.category.service.CategoryService;
import com.spendwise.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private static final UUID DEMO_USER = UUID.fromString("692fee48-590e-40e5-bf61-4fdfa0ab9ff5");

    @PostMapping
    public ResponseEntity<CategoryResponse> create(
        @Valid @RequestBody CreateCategoryRequest request
    ){

        CategoryResponse response = categoryService.create(DEMO_USER, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<CategoryResponse> getAll(){
        return categoryService.getAll(DEMO_USER);
    }

}

