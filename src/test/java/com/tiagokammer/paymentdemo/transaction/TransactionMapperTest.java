package com.tiagokammer.paymentdemo.transaction;

import com.tiagokammer.paymentdemo.account.AccountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class TransactionMapperTest {

    private static final Long DEFAULT_TRANSACTION_ID = 1000L;
    private static final Long DEFAULT_ACCOUNT_ID = 10L;
    private static final Integer DEFAULT_OPERATION_TYPE_ID = 1;
    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal("1.5");
    private static final LocalDateTime DEFAULT_EVENT_DATE = LocalDateTime.of(2023, 1, 31, 23, 59, 59);

    @InjectMocks
    private TransactionMapper mapper;

    @Test
    void shouldMapTransactionCreateRequestToTransactionEntity() {
        TransactionCreateRequest createRequest = buildDefaultTransactionCreateRequest();

        TransactionEntity entity = mapper.toEntity(createRequest);

        assertNull(entity.getTransactionId());
        assertEquals(DEFAULT_ACCOUNT_ID, entity.getAccount().getAccountId());
        assertEquals(DEFAULT_OPERATION_TYPE_ID, entity.getOperationType().getOperationTypeId());
        assertEquals(DEFAULT_AMOUNT, entity.getAmount());
    }

    @Test
    void shouldMapTransactionEntityToTransactionResponse() {
        TransactionEntity entity = buildDefaultTransactionEntity();

        TransactionResponse response = mapper.toResponse(entity);

        assertEquals(DEFAULT_TRANSACTION_ID, response.getTransactionId());
        assertEquals(DEFAULT_ACCOUNT_ID, response.getAccountId());
        assertEquals(DEFAULT_OPERATION_TYPE_ID, response.getOperationTypeId());
        assertEquals(DEFAULT_AMOUNT, response.getAmount());
        assertEquals(DEFAULT_EVENT_DATE, response.getEventDate());
    }

    private TransactionCreateRequest buildDefaultTransactionCreateRequest() {
        return TransactionCreateRequest.builder()
                .accountId(DEFAULT_ACCOUNT_ID)
                .operationTypeId(DEFAULT_OPERATION_TYPE_ID)
                .amount(DEFAULT_AMOUNT)
                .build();
    }

    private TransactionEntity buildDefaultTransactionEntity() {
        return TransactionEntity.builder()
                .transactionId(DEFAULT_TRANSACTION_ID)
                .account(AccountEntity.builder()
                        .accountId(DEFAULT_ACCOUNT_ID)
                        .build())
                .operationType(OperationTypeEntity.builder()
                        .operationTypeId(DEFAULT_OPERATION_TYPE_ID)
                        .build())
                .amount(DEFAULT_AMOUNT)
                .eventDate(DEFAULT_EVENT_DATE)
                .build();
    }
}
