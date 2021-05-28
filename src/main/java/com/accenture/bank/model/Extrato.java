package com.accenture.bank.model;

import com.accenture.bank.model.enumeration.OperacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_extrato")
public class Extrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraMovimento;
    @NotNull
	private OperacaoEnum operacaoEnum;
    @NotNull
	private Integer idContaCorrente;

}
