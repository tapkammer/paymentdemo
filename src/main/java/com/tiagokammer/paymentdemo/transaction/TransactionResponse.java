package com.tiagokammer.paymentdemo.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    @JsonProperty("transaction_id")
    public Long transactionId;

    @JsonProperty("account_id")
    public Long accountId;

    @JsonProperty("operation_type_id")
    public Integer operationTypeId;

    @JsonProperty("amount")
    public BigDecimal amount;

    @JsonProperty("event_date")
    public LocalDateTime eventDate;
}
