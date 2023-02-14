package com.tiagokammer.paymentdemo.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class AccountCreateRequest {

    @NotNull
    @JsonProperty("document_number")
    private String documentNumber;

    @Min(0)
    @JsonProperty("available_credit_limit")
    private BigDecimal availableCreditLimit;

}
