package com.accenture.bank.service;

import com.accenture.bank.model.Extrato;
import com.accenture.bank.repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExtratoService {
    @Autowired
    private ExtratoRepository repository;

    public List<Extrato> getAllExtrato()
    {
        List<Extrato> extratos = new ArrayList<Extrato>();
        repository.findAll().forEach(extrato -> extratos.add(extrato));
        return extratos;
    }

    public Extrato getExtratoById(int id)
    {
        return repository.findById(id).get();
    }

    public void saveOrUpdate(Extrato extrato)
    {
        repository.save(extrato);
    }

    public void delete(int id)
    {
        repository.deleteById(id);
    }
}
