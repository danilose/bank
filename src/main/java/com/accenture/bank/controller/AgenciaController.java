package com.accenture.bank.controller;

import com.accenture.bank.model.Agencia;
import com.accenture.bank.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/agencia")
public class AgenciaController {
    @Autowired
    private AgenciaService service;

    @GetMapping
    private List<Agencia> getAllAgencia()
    {
        return service.getAllAgencia();
    }

    @GetMapping("/{id}")
    private Agencia getAgencia(@PathVariable("id") int id)
    {
        return service.getAgenciaById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteAgencia(@PathVariable("id") int id)
    {
        service.delete(id);
    }

    @PostMapping
    private int saveAgencia(@RequestBody Agencia agencia)
    {
        service.saveOrUpdate(agencia);
        return agencia.getId();
    }

}
