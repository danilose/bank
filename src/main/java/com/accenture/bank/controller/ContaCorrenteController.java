package com.accenture.bank.controller;

import com.accenture.bank.model.ContaCorrente;
import com.accenture.bank.service.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/conta-corrente")
public class ContaCorrenteController {
    @Autowired
    private ContaCorrenteService service;

    @GetMapping
    private List<ContaCorrente> getAllContaCorrente()
    {
        return service.getAllContaCorrente();
    }

    @GetMapping("/{id}")
    private ContaCorrente getContaCorrente(@PathVariable("id") int id)
    {
        return service.getContaCorrenteById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteContaCorrente(@PathVariable("id") int id)
    {
        service.delete(id);
    }

    @PostMapping
    private int saveContaCorrente(@RequestBody @Valid ContaCorrente contaCorrente)
    {
        service.saveOrUpdate(contaCorrente);
        return contaCorrente.getId();
    }

}
