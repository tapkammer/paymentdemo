package com.tiagokammer.paymentdemo.transaction;

import com.tiagokammer.paymentdemo.exception.DataViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionEntity createTransaction(TransactionEntity transactionEntity) {
        if (isNegativeValue(transactionEntity)) {
            transactionEntity.setAmount(transactionEntity.getAmount().negate());
        }
        try {
            return repository.save(transactionEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new DataViolationException("Invalid data!");
        }
    }

    private boolean isNegativeValue(TransactionEntity transactionEntity) {
        return transactionEntity.getOperationType().getOperationTypeId() == 1
                || transactionEntity.getOperationType().getOperationTypeId() == 2
                || transactionEntity.getOperationType().getOperationTypeId() == 3;
    }
}
