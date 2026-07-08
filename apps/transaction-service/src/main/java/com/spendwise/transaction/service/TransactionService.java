package com.spendwise.transaction.service;

import com.spendwise.transaction.api.TransactionMapper;
import com.spendwise.transaction.api.dto.CreateTransactionRequest;
import com.spendwise.transaction.api.dto.TransactionResponse;
import com.spendwise.transaction.domain.Transaction;
import com.spendwise.transaction.respository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Transactional
    public TransactionResponse create(UUID userId, CreateTransactionRequest request){
        Transaction tx = transactionMapper.toEntity(request);
        tx.setUserId(userId);
        return transactionMapper.toResponse(transactionRepository.save(tx));
    }

    @Transactional
    public TransactionResponse get(UUID userId, UUID id){
        Transaction tx = transactionRepository.findById(id)
            .filter(t -> t.getUserId().equals(userId))
            .orElseThrow(()-> new RuntimeException("transaction not found with id: "+id));
        return transactionMapper.toResponse(tx);
    }
}
