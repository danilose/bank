package com.accenture.bank.repository;

import com.accenture.bank.model.Agencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends CrudRepository<Agencia, Integer> {
}
