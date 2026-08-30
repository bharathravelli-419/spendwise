package com.spendwise.transaction.respository;

import com.spendwise.transaction.domain.Transaction;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecs {

  public static Specification<Transaction> ownedBy(UUID userId) {
    return (root, query, cb) -> cb.equal(root.get("userId"), userId);
  }

  public static Specification<Transaction> notDeleted() {
    return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
  }

  public static Specification<Transaction> categoryIs(UUID id) {
    return ((root, query, cb) -> cb.equal(root.get("categoryId"), id));
  }

  public static Specification<Transaction> occurredBetween(OffsetDateTime from, OffsetDateTime to) {
    return ((root, query, criteriaBuilder) -> {
      if (from != null && to != null)
        return criteriaBuilder.between(root.get("occurredAt"), from, to);
      if (from != null) return criteriaBuilder.greaterThanOrEqualTo(root.get("occurredAt"), from);
      if (to != null) return criteriaBuilder.lessThanOrEqualTo(root.get("occurredAt"), to);
      return null;
    });
  }

  public static Specification<Transaction> amountAtLeast(BigDecimal min) {
    return min == null
        ? null
        : ((root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), min));
  }
}
