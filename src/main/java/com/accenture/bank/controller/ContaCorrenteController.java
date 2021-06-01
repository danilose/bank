package com.accenture.bank.controller;

import com.accenture.bank.model.ContaCorrente;
import com.accenture.bank.model.exception.ContaCorrenteDestinoNotFoundException;
import com.accenture.bank.model.exception.ContaCorrenteNotFoundException;
import com.accenture.bank.model.exception.ContaCorrenteSaldoInsuficienteException;
import com.accenture.bank.model.request.DepositoRequest;
import com.accenture.bank.model.request.SaqueRequest;
import com.accenture.bank.model.request.TransferenciaRequest;
import com.accenture.bank.service.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/depositar")
    private void depositar(@RequestBody @Valid DepositoRequest request)
    {
        try {
            service.depositar(request);
        } catch (ContaCorrenteNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID do contrato não encontrado", e);
        }
    }

    @PostMapping("/sacar")
    private void sacar(@RequestBody @Valid SaqueRequest request) {
        try {
            service.sacar(request);
        } catch (ContaCorrenteSaldoInsuficienteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente", e);
        } catch (ContaCorrenteNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID do contrato não encontrado", e);
        }
    }

    @PostMapping("/transferir")
    private void transferir(@RequestBody @Valid TransferenciaRequest request)
    {
        try {
            service.transferir(request);
        } catch (ContaCorrenteNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID do contrato não encontrado", e);
        } catch (ContaCorrenteDestinoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID do contrato destino não encontrado", e);
        } catch (ContaCorrenteSaldoInsuficienteException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Saldo insuficiente", e);
        }
    }
}
