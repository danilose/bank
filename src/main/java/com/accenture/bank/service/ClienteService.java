package com.accenture.bank.service;

import com.accenture.bank.model.Cliente;
import com.accenture.bank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> getAllCliente()
    {
        List<Cliente> clientes = new ArrayList<>();
        repository.findAll().forEach(cliente -> clientes.add(cliente));
        return clientes;
    }

    public Cliente getClienteById(int id)
    {
        return repository.findById(id).get();
    }

    public void saveOrUpdate(Cliente cliente)
    {
        repository.save(cliente);
    }

    public void delete(int id)
    {
        repository.deleteById(id);
    }
}
