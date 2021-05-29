package com.accenture.bank.repository;

import com.accenture.bank.model.Movimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoRepository extends CrudRepository<Movimento, Integer> {
}
