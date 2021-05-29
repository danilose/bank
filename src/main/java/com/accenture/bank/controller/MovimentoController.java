package com.accenture.bank.controller;

import com.accenture.bank.model.Movimento;
import com.accenture.bank.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/movimento")
public class MovimentoController {
    @Autowired
    private MovimentoService service;

    @GetMapping
    private List<Movimento> getAllMovimento()
    {
        return service.getAllMovimento();
    }

    @GetMapping("/{id}")
    private Movimento getMovimento(@PathVariable("id") int id)
    {
        return service.getMovimentoById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteMovimento(@PathVariable("id") int id)
    {
        service.delete(id);
    }

    @PostMapping
    private int saveMovimento(@RequestBody @Valid Movimento movimento)
    {
        service.saveOrUpdate(movimento);
        return movimento.getId();
    }

}
