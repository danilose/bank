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
@Table(name = "tb_agencia")
public class Agencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
	private String nome;

    @NotNull
	private String endereco;

    @NotNull
	private String fone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="tb_cliente_agencia",
            joinColumns={@JoinColumn(name="cliente_id")},
            inverseJoinColumns={@JoinColumn(name="agencia_id")})
    private List<Cliente> clientes;
	
}
