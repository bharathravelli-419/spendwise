package com.spendwise.transaction.api;

import com.spendwise.transaction.api.dto.CreateTransactionRequest;
import com.spendwise.transaction.api.dto.TransactionResponse;
import com.spendwise.transaction.api.helper.Idempotency;
import com.spendwise.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private static final UUID DEMO_USER = UUID.fromString("692fee48-590e-40e5-bf61-4fdfa0ab9ff5");

    @PostMapping
    public ResponseEntity<TransactionResponse> create(
        @RequestHeader(value = "Idempotency-Key", required = false) String idemKey,
        @Valid @RequestBody CreateTransactionRequest request
        ){
        //Idempotency key in-memory for now.(Redis later)
        TransactionResponse cached = Idempotency.lookup(idemKey, request);
        if(cached != null) return  ResponseEntity.ok(cached);
        TransactionResponse response = transactionService.create(DEMO_USER, request);
        Idempotency.store(idemKey,request,response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public TransactionResponse get(@PathVariable UUID id){
        return transactionService.get(DEMO_USER, id);
    }
}
