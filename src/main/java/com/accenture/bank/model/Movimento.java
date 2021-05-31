package com.accenture.bank.model;

import com.accenture.bank.model.enumeration.OperacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_movimento")
public class Movimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraMovimento;

    @NotNull
    @Enumerated(EnumType.STRING)
	private OperacaoEnum operacaoEnum;

    @NotNull
    @Column(precision=17, scale=2)
    private Double valor;

}
