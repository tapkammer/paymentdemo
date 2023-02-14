package com.tiagokammer.paymentdemo.account;

import com.tiagokammer.paymentdemo.exception.DataNotFoundException;
import com.tiagokammer.paymentdemo.exception.DataViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public AccountEntity createAccount(AccountEntity accountEntity) {
        Optional<AccountEntity> accountByDocumentNumber = findAccountByDocumentNumber(accountEntity.getDocumentNumber());

        if (accountByDocumentNumber.isPresent()) {
            throw new RuntimeException("Account with same document number already exists!");
        }

        return repository.save(accountEntity);
    }

    public AccountEntity getAccount(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new DataNotFoundException("Account not found!"));
    }

    public Optional<AccountEntity> findAccountByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber);
    }

    public AccountEntity updateAvailableCreditLimit(Long accountId, BigDecimal valueToAdd) {
        AccountEntity entity = getAccount(accountId);
        BigDecimal newCreditLimit = entity.getAvailableCreditLimit().add(valueToAdd);

        if (newCreditLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new DataViolationException("Credit limit could not be less than 0!");
        }

        entity.setAvailableCreditLimit(newCreditLimit);
        return repository.save(entity);
    }
}
