package com.tiagokammer.paymentdemo.account;

import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toEntity(AccountCreateRequest accountCreateRequest) {
        return AccountEntity.builder()
                .accountId(null)
                .documentNumber(accountCreateRequest.documentNumber)
                .build();
    }

    public AccountResponse toResponse(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .accountId(accountEntity.getAccountId())
                .documentNumber(accountEntity.getDocumentNumber())
                .build();
    }
}
