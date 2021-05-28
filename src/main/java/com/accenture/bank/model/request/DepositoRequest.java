package com.accenture.bank.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositoRequest {
    private Integer contaFrom;
    private Double valor;
}
