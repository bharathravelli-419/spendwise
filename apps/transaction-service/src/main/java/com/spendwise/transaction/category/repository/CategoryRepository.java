package com.spendwise.transaction.category.repository;

import com.spendwise.transaction.category.domain.Category;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

  List<Category> findByUserIdAndDeletedAtIsNull(UUID userId);

  List<Category> findByUserIdAndParentIdAndDeletedAtIsNull(UUID userId, UUID parentId);
}
