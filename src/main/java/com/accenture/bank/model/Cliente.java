package com.accenture.bank.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @NotNull
	private String nome;

    @CPF(message = "CPF inválido")
    @NotNull
    @Size(max = 14)
    private String cpf;

    @NotNull
	private String fone;

    @NotNull
    @ManyToMany(mappedBy = "clientes", fetch = FetchType.LAZY)
    private List<Agencia> agencias;

    @OneToMany
    @JoinColumn(name="cliente_id")
    private List<ContaCorrente> contasCorrente;
}
