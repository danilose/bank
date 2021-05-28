package com.accenture.bank.service;

import com.accenture.bank.model.Agencia;
import com.accenture.bank.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository repository;

    public List<Agencia> getAllAgencia()
    {
        List<Agencia> agencias = new ArrayList<Agencia>();
        repository.findAll().forEach(agencia -> agencias.add(agencia));
        return agencias;
    }

    public Agencia getAgenciaById(int id)
    {
        return repository.findById(id).get();
    }

    public void saveOrUpdate(Agencia agencia)
    {
        repository.save(agencia);
    }

    public void delete(int id)
    {
        repository.deleteById(id);
    }
}
