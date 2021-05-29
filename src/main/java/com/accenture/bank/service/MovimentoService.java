package com.accenture.bank.service;

import com.accenture.bank.model.Movimento;
import com.accenture.bank.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimentoService {
    @Autowired
    private MovimentoRepository repository;

    public List<Movimento> getAllMovimento()
    {
        List<Movimento> movimentos = new ArrayList<Movimento>();
        repository.findAll().forEach(movimento -> movimentos.add(movimento));
        return movimentos;
    }

    public Movimento getMovimentoById(int id)
    {
        return repository.findById(id).get();
    }

    public void saveOrUpdate(Movimento movimento)
    {
        repository.save(movimento);
    }

    public void delete(int id)
    {
        repository.deleteById(id);
    }
}
