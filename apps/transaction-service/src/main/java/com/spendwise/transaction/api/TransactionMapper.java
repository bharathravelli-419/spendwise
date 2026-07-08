package com.spendwise.transaction.api;

import com.spendwise.transaction.api.dto.CreateTransactionRequest;
import com.spendwise.transaction.api.dto.TransactionResponse;
import com.spendwise.transaction.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Transaction toEntity(CreateTransactionRequest req);
    TransactionResponse toResponse(Transaction tx);
}
