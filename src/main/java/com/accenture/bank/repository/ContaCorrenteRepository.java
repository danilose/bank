package com.accenture.bank.repository;

import com.accenture.bank.model.ContaCorrente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends CrudRepository<ContaCorrente, Integer> {
}
