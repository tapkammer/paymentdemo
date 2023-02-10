package com.tiagokammer.paymentdemo.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateRequest {

    @NotNull
    @JsonProperty("account_id")
    public Long accountId;

    @NotNull
    @JsonProperty("operation_type_id")
    public Integer operationTypeId;

    @NotNull
    @JsonProperty("amount")
    public BigDecimal amount;
}
