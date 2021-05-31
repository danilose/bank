package com.accenture.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Length(max = 45)
	private String nome;

    @CPF(message = "CPF inválido")
    @NotNull
    @Length(max = 14)
    private String cpf;

    @NotNull
    @Size(max = 20)
	private String fone;

    @NotNull
    @JsonIgnore
    @ManyToMany(mappedBy = "clientes")
    private List<Agencia> agencias = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    @JoinColumn(name="cliente_id")
    private List<ContaCorrente> contasCorrente = new ArrayList<>();
}
