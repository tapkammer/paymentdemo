package com.tiagokammer.paymentdemo.account;

import com.tiagokammer.paymentdemo.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public AccountEntity createAccount(AccountEntity accountEntity) {
        return repository.save(accountEntity);
    }

    public AccountEntity getAccount(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new DataNotFoundException("Account not found!"));
    }
}
