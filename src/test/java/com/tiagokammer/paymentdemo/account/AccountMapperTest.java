package com.tiagokammer.paymentdemo.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AccountMapperTest {

    private static final String DEFAULT_DOCUMENT_NUMBER = "12345";

    @InjectMocks
    private AccountMapper mapper;

    @Test
    void shouldMapAccountCreateRequestToAccountEntity() {
        AccountCreateRequest createRequest = buildDefaultAccountCreateRequest();

        AccountEntity entity = mapper.toEntity(createRequest);

        assertEquals(DEFAULT_DOCUMENT_NUMBER, entity.getDocumentNumber());
        assertNull(entity.getAccountId());
    }

    @Test
    void shouldMapAccountEntityToAccountResponse() {
        AccountEntity entity = buildDefaultAccountEntity();

        AccountResponse response = mapper.toResponse(entity);

        assertEquals(DEFAULT_DOCUMENT_NUMBER, response.getDocumentNumber());
    }

    private AccountCreateRequest buildDefaultAccountCreateRequest() {
        return AccountCreateRequest.builder()
                .documentNumber(DEFAULT_DOCUMENT_NUMBER)
                .build();
    }

    private AccountEntity buildDefaultAccountEntity() {
        return AccountEntity.builder()
                .documentNumber(DEFAULT_DOCUMENT_NUMBER)
                .build();
    }
}
