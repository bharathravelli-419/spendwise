package com.spendwise.transaction.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionResponse(
    UUID id,
    UUID accountId,
    UUID categoryId,
    BigDecimal amount,
    String currency,
    String direction,
    String note,
    OffsetDateTime occurredAt
) {
}
