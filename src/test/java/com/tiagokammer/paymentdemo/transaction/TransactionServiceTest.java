package com.tiagokammer.paymentdemo.transaction;

import com.tiagokammer.paymentdemo.account.AccountEntity;
import com.tiagokammer.paymentdemo.exception.DataViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    private static final Long DEFAULT_TRANSACTION_ID = 1000L;
    private static final Long DEFAULT_ACCOUNT_ID = 10L;
    private static final Integer DEFAULT_OPERATION_TYPE_ID = 4;
    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal("1.5");
    private static final LocalDateTime DEFAULT_EVENT_DATE = LocalDateTime.of(2023, 1, 31, 23, 59, 59);

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    @ParameterizedTest
    @MethodSource("getCreditTransactions")
    void shouldCreateCreditTransaction(Integer operationTypeId) {
        TransactionEntity entity = buildDefaultTransactionEntity(operationTypeId);
        when(repository.save(entity)).thenReturn(entity);

        TransactionEntity returnedEntity = service.createTransaction(entity);

        verify(repository).save(entity);
        assertEquals(DEFAULT_TRANSACTION_ID, returnedEntity.getTransactionId());
        assertEquals(DEFAULT_ACCOUNT_ID, returnedEntity.getAccount().getAccountId());
        assertEquals(operationTypeId, returnedEntity.getOperationType().getOperationTypeId());
        assertEquals(DEFAULT_AMOUNT, returnedEntity.getAmount());
        assertEquals(DEFAULT_EVENT_DATE, returnedEntity.getEventDate());
    }

    @ParameterizedTest
    @MethodSource("getDebitTransactions")
    void shouldCreateDebitTransaction(Integer operationTypeId) {
        TransactionEntity entity = buildDefaultTransactionEntity(operationTypeId);
        when(repository.save(entity)).thenReturn(entity);

        TransactionEntity returnedEntity = service.createTransaction(entity);

        verify(repository).save(entity);
        assertEquals(DEFAULT_TRANSACTION_ID, returnedEntity.getTransactionId());
        assertEquals(DEFAULT_ACCOUNT_ID, returnedEntity.getAccount().getAccountId());
        assertEquals(operationTypeId, returnedEntity.getOperationType().getOperationTypeId());
        assertEquals(DEFAULT_AMOUNT, returnedEntity.getAmount().negate());
        assertEquals(DEFAULT_EVENT_DATE, returnedEntity.getEventDate());
    }

    @Test
    void shouldThrowDataViolationExceptionWhenDataIntegrityViolationExceptionIsThrown() {
        TransactionEntity entity = buildDefaultTransactionEntity(DEFAULT_OPERATION_TYPE_ID);
        when(repository.save(entity)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataViolationException.class, () -> service.createTransaction(entity));
        verify(repository).save(entity);
    }

    private TransactionEntity buildDefaultTransactionEntity(Integer operationTypeId) {
        return TransactionEntity.builder()
                .transactionId(DEFAULT_TRANSACTION_ID)
                .account(AccountEntity.builder()
                        .accountId(DEFAULT_ACCOUNT_ID)
                        .build())
                .operationType(OperationTypeEntity.builder()
                        .operationTypeId(operationTypeId)
                        .build())
                .amount(DEFAULT_AMOUNT)
                .eventDate(DEFAULT_EVENT_DATE)
                .build();
    }

    private static IntStream getDebitTransactions() {
        return IntStream.of(1, 2, 3);
    }

    private static IntStream getCreditTransactions() {
        return IntStream.of(-1, 0, 4, 5, 10);
    }

}
