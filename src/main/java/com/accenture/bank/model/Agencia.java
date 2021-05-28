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
@Table(name = "tb_agencia")
public class Agencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
	private String nome;
    @NotNull
	private String endereco;
    @NotNull
	private String fone;
    @NotNull
    @ManyToOne
	private Cliente cliente;
	
}
