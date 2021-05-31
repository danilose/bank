package com.accenture.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
    @Length(max = 45)
	private String nome;

    @NotNull
    @Length(max = 45)
	private String endereco;

    @NotNull
    @Length(max = 15)
	private String fone;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name="tb_cliente_agencia",
            joinColumns={@JoinColumn(name="cliente_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="agencia_id", referencedColumnName="id")})
    private List<Cliente> clientes;
	
}
