package com.accenture.bank.repository;

import com.accenture.bank.model.Extrato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtratoRepository extends CrudRepository<Extrato, Integer> {
}
