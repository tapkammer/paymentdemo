package com.tiagokammer.paymentdemo.account;

import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toEntity(AccountCreateRequest accountCreateRequest) {
        return AccountEntity.builder()
                .accountId(null)
                .documentNumber(accountCreateRequest.getDocumentNumber())
                .availableCreditLimit(accountCreateRequest.getAvailableCreditLimit())
                .build();
    }

    public AccountResponse toResponse(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .accountId(accountEntity.getAccountId())
                .documentNumber(accountEntity.getDocumentNumber())
                .availableCreditLimit(accountEntity.getAvailableCreditLimit())
                .build();
    }
}
