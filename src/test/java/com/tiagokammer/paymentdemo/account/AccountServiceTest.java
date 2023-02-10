package com.tiagokammer.paymentdemo.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tiagokammer.paymentdemo.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    private static final String DEFAULT_DOCUMENT_NUMBER = "12345";
    private static final Long DEFAULT_ACCOUNT_ID = 123L;

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountService service;

    @Test
    void shouldCreateAccount() {
        AccountEntity entity = buildDefaultAccountEntity();
        when(repository.save(entity)).thenReturn(entity);

        AccountEntity returnedEntity = service.createAccount(entity);

        verify(repository).save(entity);
        assertEquals(DEFAULT_ACCOUNT_ID, returnedEntity.getAccountId());
        assertEquals(DEFAULT_DOCUMENT_NUMBER, returnedEntity.getDocumentNumber());
    }

    @Test
    void shouldGetAccount() {
        Optional<AccountEntity> optional = Optional.of(buildDefaultAccountEntity());
        when(repository.findById(DEFAULT_ACCOUNT_ID)).thenReturn(optional);

        AccountEntity returnedEntity = service.getAccount(DEFAULT_ACCOUNT_ID);

        verify(repository).findById(DEFAULT_ACCOUNT_ID);
        assertEquals(DEFAULT_ACCOUNT_ID, returnedEntity.getAccountId());
        assertEquals(DEFAULT_DOCUMENT_NUMBER, returnedEntity.getDocumentNumber());
    }

    @Test
    void shouldThrowExceptionWhenAccountIsNotFound() {
        when(repository.findById(DEFAULT_ACCOUNT_ID)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> service.getAccount(DEFAULT_ACCOUNT_ID));
        verify(repository).findById(DEFAULT_ACCOUNT_ID);
    }

    private AccountEntity buildDefaultAccountEntity() {
        return AccountEntity.builder()
                .accountId(DEFAULT_ACCOUNT_ID)
                .documentNumber(DEFAULT_DOCUMENT_NUMBER)
                .build();
    }
}
