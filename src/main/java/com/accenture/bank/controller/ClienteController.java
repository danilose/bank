package com.accenture.bank.controller;

import com.accenture.bank.model.Cliente;
import com.accenture.bank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    private List<Cliente> getAllCliente()
    {
        return service.getAllCliente();
    }

    @GetMapping("/{id}")
    private Cliente getCliente(@PathVariable("id") int id)
    {
        return service.getClienteById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteCliente(@PathVariable("id") int id)
    {
        service.delete(id);
    }

    @PostMapping
    private int saveCliente(@RequestBody @Valid Cliente cliente)
    {
        service.saveOrUpdate(cliente);
        return cliente.getId();
    }

}
