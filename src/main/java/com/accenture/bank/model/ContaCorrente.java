package com.accenture.bank.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "tb_conta_corrente")
public class ContaCorrente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @NotNull
    @OneToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Agencia agencia;

    @NotNull
    @Length(max = 45)
	private String numero;

    @NotNull
    @Column(precision=11, scale=2)
	private Double saldo = 0.0;

    @NotNull
    @ManyToOne
	private Cliente cliente;

    @OneToMany
    @JoinColumn(name="conta_corrente_id")
    private List<Movimento> movimento;

}
