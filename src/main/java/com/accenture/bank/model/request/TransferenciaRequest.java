package com.accenture.bank.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaRequest {
    private Integer contaFrom;
    private Integer contaTo;
    private Double valor;
}
