package com.accenture.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_conta_corrente")
public class ContaCorrente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @NotNull
    @OneToOne
    private Agencia agencia;

    @NotNull
	private String numero;

    @NotNull
	private Double saldo = 0.0;

    @NotNull
    @ManyToOne
	private Cliente cliente;

    @OneToMany
    @JoinColumn(name="conta_corrente_id")
    private List<Movimento> movimento;

}
