package com.accenture.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_conta_corrente")
public class ContaCorrente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @NotNull
	private String agencia;
    @NotNull
	private String numero;
    @NotNull
	private Double saldo;
    @NotNull
    @ManyToOne
	private Cliente cliente;
	
}
