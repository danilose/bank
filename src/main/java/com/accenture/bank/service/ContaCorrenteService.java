package com.accenture.bank.service;

import com.accenture.bank.model.ContaCorrente;
import com.accenture.bank.model.request.DepositoRequest;
import com.accenture.bank.model.request.SaqueRequest;
import com.accenture.bank.model.request.TransferenciaRequest;
import com.accenture.bank.model.exception.ContaCorrenteNotFoundException;
import com.accenture.bank.model.exception.ContaCorrenteSaldoInsuficienteException;
import com.accenture.bank.repository.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaCorrenteService {
    @Autowired
    private ContaCorrenteRepository repository;

    public List<ContaCorrente> getAllContaCorrente()
    {
        List<ContaCorrente> contasCorrente = new ArrayList<ContaCorrente>();
        repository.findAll().forEach(contaCorrente -> contasCorrente.add(contaCorrente));
        return contasCorrente;
    }

    public ContaCorrente getContaCorrenteById(int id)
    {
        return repository.findById(id).get();
    }

    public void saveOrUpdate(ContaCorrente contaCorrente)
    {
        repository.save(contaCorrente);
    }

    public void delete(int id)
    {
        repository.deleteById(id);
    }

    public void depositar(DepositoRequest deposito) throws ContaCorrenteNotFoundException {

        Optional<ContaCorrente> optionalContaCorrente = repository.findById(deposito.getContaFrom());

        if (!optionalContaCorrente.isPresent()) {
            throw new ContaCorrenteNotFoundException();
        }

        ContaCorrente contaCorrente = optionalContaCorrente.get();
        contaCorrente.setSaldo( contaCorrente.getSaldo() + deposito.getValor() );

        repository.save(contaCorrente);
    }

    public void sacar(SaqueRequest saque) throws ContaCorrenteNotFoundException, ContaCorrenteSaldoInsuficienteException {

        Optional<ContaCorrente> optionalContaCorrente = repository.findById(saque.getContaFrom());

        if (!optionalContaCorrente.isPresent()) {
            throw new ContaCorrenteNotFoundException();
        }

        ContaCorrente contaCorrente = optionalContaCorrente.get();

        Double novoSaldo = contaCorrente.getSaldo() - saque.getValor();

        if (novoSaldo < 0) {
            throw new ContaCorrenteSaldoInsuficienteException();
        }

        contaCorrente.setSaldo( novoSaldo );

        repository.save(contaCorrente);
    }

    public void transferir(TransferenciaRequest transferencia) throws ContaCorrenteNotFoundException, ContaCorrenteSaldoInsuficienteException {

        Optional<ContaCorrente> optionalContaCorrenteFrom = repository.findById(transferencia.getContaFrom());
        Optional<ContaCorrente> optionalContaCorrenteTo = repository.findById(transferencia.getContaTo());

        if (!optionalContaCorrenteFrom.isPresent() || !optionalContaCorrenteTo.isPresent()) {
            throw new ContaCorrenteNotFoundException();
        }

        ContaCorrente contaCorrenteFrom = optionalContaCorrenteFrom.get();
        ContaCorrente contaCorrenteTo = optionalContaCorrenteTo.get();

        Double novoSaldo = contaCorrenteFrom.getSaldo() - transferencia.getValor();

        if (novoSaldo < 0) {
            throw new ContaCorrenteSaldoInsuficienteException();
        }

        contaCorrenteFrom.setSaldo( novoSaldo );
        contaCorrenteTo.setSaldo( contaCorrenteTo.getSaldo() + transferencia.getValor() );

        repository.save(contaCorrenteFrom);
        repository.save(contaCorrenteTo);
    }
}
