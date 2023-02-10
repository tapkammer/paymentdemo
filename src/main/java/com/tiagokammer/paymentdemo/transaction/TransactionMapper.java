package com.tiagokammer.paymentdemo.transaction;

import com.tiagokammer.paymentdemo.account.AccountEntity;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(TransactionCreateRequest transactionCreateRequest) {
        return TransactionEntity.builder()
                .transactionId(null)
                .account(AccountEntity.builder()
                        .accountId(transactionCreateRequest.getAccountId())
                        .build())
                .operationType(OperationTypeEntity.builder()
                        .operationTypeId(transactionCreateRequest.getOperationTypeId())
                        .build())
                .amount(transactionCreateRequest.getAmount())
                .eventDate(LocalDateTime.now(Clock.systemUTC()))
                .build();
    }

    public TransactionResponse toResponse(TransactionEntity transactionEntity) {
        return TransactionResponse.builder()
                .transactionId(transactionEntity.getTransactionId())
                .accountId(transactionEntity.getAccount().getAccountId())
                .operationTypeId(transactionEntity.getOperationType().getOperationTypeId())
                .amount(transactionEntity.getAmount())
                .eventDate(transactionEntity.getEventDate())
                .build();
    }
}
