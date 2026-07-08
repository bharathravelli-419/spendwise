package com.spendwise.transaction.api.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateTransactionRequest(
    @NotNull
    UUID accountId,
    @NotNull @Positive BigDecimal amount,
    @NotBlank @Size(min = 3, max = 3) String currency,
    @NotNull @Pattern( regexp = "DEBIT|CREDIT") String direction,
    @Size(max = 500) String note,
    @NotNull OffsetDateTime occurredAt,
    @NotNull UUID categoryId
    ){}
