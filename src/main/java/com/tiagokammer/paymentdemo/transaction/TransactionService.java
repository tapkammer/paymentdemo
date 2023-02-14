package com.tiagokammer.paymentdemo.transaction;

import com.tiagokammer.paymentdemo.account.AccountService;
import com.tiagokammer.paymentdemo.exception.DataViolationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final AccountService accountService;

    @Transactional
    public TransactionEntity createTransaction(TransactionEntity transactionEntity) {
        BigDecimal transactionValue;
        if (isNegativeValue(transactionEntity)) {
            transactionValue = transactionEntity.getAmount().negate();
        } else {
            transactionValue = transactionEntity.getAmount();
        }

        accountService.updateAvailableCreditLimit(transactionEntity.getAccount().getAccountId(), transactionValue);

        transactionEntity.setAmount(transactionValue);

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
