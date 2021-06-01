package com.accenture.bank.service;

import com.accenture.bank.model.ContaCorrente;
import com.accenture.bank.model.Movimento;
import com.accenture.bank.model.enumeration.OperacaoEnum;
import com.accenture.bank.model.exception.ContaCorrenteDestinoNotFoundException;
import com.accenture.bank.model.request.DepositoRequest;
import com.accenture.bank.model.request.SaqueRequest;
import com.accenture.bank.model.request.TransferenciaRequest;
import com.accenture.bank.model.exception.ContaCorrenteNotFoundException;
import com.accenture.bank.model.exception.ContaCorrenteSaldoInsuficienteException;
import com.accenture.bank.repository.ContaCorrenteRepository;
import com.accenture.bank.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaCorrenteService {
    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;
    @Autowired
    private MovimentoRepository movimentoRepository;

    public List<ContaCorrente> getAllContaCorrente()
    {
        List<ContaCorrente> contasCorrente = new ArrayList<>();
        contaCorrenteRepository.findAll().forEach(contaCorrente -> contasCorrente.add(contaCorrente));
        return contasCorrente;
    }

    public ContaCorrente getContaCorrenteById(int id)
    {
        return contaCorrenteRepository.findById(id).get();
    }

    public void saveOrUpdate(ContaCorrente contaCorrente)
    {
        contaCorrenteRepository.save(contaCorrente);
    }

    public void delete(int id)
    {
        contaCorrenteRepository.deleteById(id);
    }

    private Movimento novoMovimento(Double valor, OperacaoEnum operacaoEnum) {
        Movimento movimento = new Movimento();
        movimento.setValor(valor);
        movimento.setOperacaoEnum(operacaoEnum);
        return movimentoRepository.save(movimento);
    }

    public void depositar(DepositoRequest deposito) throws ContaCorrenteNotFoundException {

        Optional<ContaCorrente> optionalContaCorrente = contaCorrenteRepository.findById(deposito.getContaFrom());

        if (!optionalContaCorrente.isPresent()) {
            throw new ContaCorrenteNotFoundException();
        }

        ContaCorrente contaCorrente = depositarContaCorrente(deposito, optionalContaCorrente.get());

        contaCorrenteRepository.save(contaCorrente);
    }

    private ContaCorrente depositarContaCorrente(DepositoRequest deposito, ContaCorrente contaCorrente) {
        
        contaCorrente.setSaldo( contaCorrente.getSaldo() + deposito.getValor() );

        List<Movimento> movimentos = contaCorrente.getMovimento();

        Movimento movimento = novoMovimento(deposito.getValor(), OperacaoEnum.DEPOSITO);

        addMovimento(contaCorrente, movimentos, movimento);

        return contaCorrente;
    }

    private void addMovimento(ContaCorrente contaCorrente, List<Movimento> movimentos, Movimento movimento) {
        if (movimentos.isEmpty()) {
            movimentos = new ArrayList<>();
            movimentos.add(movimento);
            contaCorrente.setMovimento(movimentos);
        } else {
            contaCorrente.getMovimento().add(movimento);
        }
    }


    public void sacar(SaqueRequest saque) throws ContaCorrenteSaldoInsuficienteException, ContaCorrenteNotFoundException {

        Optional<ContaCorrente> optionalContaCorrente = contaCorrenteRepository.findById(saque.getContaFrom());

        if (!optionalContaCorrente.isPresent()) {
            throw new ContaCorrenteNotFoundException();
        }

        ContaCorrente contaCorrente = sacarContaCorrente(saque, optionalContaCorrente.get());
        
        contaCorrenteRepository.save(contaCorrente);
    }

    private ContaCorrente sacarContaCorrente(SaqueRequest saque, ContaCorrente contaCorrente) throws ContaCorrenteSaldoInsuficienteException {

        Double novoSaldo = contaCorrente.getSaldo() - saque.getValor();

        if (novoSaldo < 0) {
            throw new ContaCorrenteSaldoInsuficienteException();
        }

        contaCorrente.setSaldo( novoSaldo );

        List<Movimento> movimentos = contaCorrente.getMovimento();

        Movimento movimento = novoMovimento(saque.getValor(), OperacaoEnum.SAQUE);

        addMovimento(contaCorrente, movimentos, movimento);

        return contaCorrente;
    }


    public void transferir(TransferenciaRequest transferencia) throws ContaCorrenteNotFoundException, ContaCorrenteDestinoNotFoundException, ContaCorrenteSaldoInsuficienteException {

        Optional<ContaCorrente> optionalContaCorrenteFrom = contaCorrenteRepository.findById(transferencia.getContaFrom());
        Optional<ContaCorrente> optionalContaCorrenteTo = contaCorrenteRepository.findById(transferencia.getContaTo());

        if (!optionalContaCorrenteFrom.isPresent()) {
            throw new ContaCorrenteNotFoundException();
        }

        if (!optionalContaCorrenteTo.isPresent()) {
            throw new ContaCorrenteDestinoNotFoundException();
        }

        ContaCorrente contaCorrenteFrom = transferirFromContaCorrente(transferencia, optionalContaCorrenteFrom.get());
        ContaCorrente contaCorrenteTo = transferirToContaCorrente(transferencia, optionalContaCorrenteTo.get());

        contaCorrenteRepository.save(contaCorrenteFrom);
        contaCorrenteRepository.save(contaCorrenteTo);
    }

    private ContaCorrente transferirFromContaCorrente(TransferenciaRequest transferencia, ContaCorrente contaCorrenteFrom) throws ContaCorrenteSaldoInsuficienteException {

        Double novoSaldo = contaCorrenteFrom.getSaldo() - transferencia.getValor();

        if (novoSaldo < 0) {
            throw new ContaCorrenteSaldoInsuficienteException();
        }

        contaCorrenteFrom.setSaldo( novoSaldo );
        List<Movimento> movimentosFrom = contaCorrenteFrom.getMovimento();
        Movimento movimentoFrom = novoMovimento(-1 * transferencia.getValor(), OperacaoEnum.TRANSFERENCIA);
        addMovimento(contaCorrenteFrom, movimentosFrom, movimentoFrom);
        return contaCorrenteFrom;
    }

    private ContaCorrente transferirToContaCorrente(TransferenciaRequest transferencia, ContaCorrente contaCorrenteTo) {
        contaCorrenteTo.setSaldo( contaCorrenteTo.getSaldo() + transferencia.getValor() );
        List<Movimento> movimentosTo = contaCorrenteTo.getMovimento();
        Movimento movimentoTo = novoMovimento(transferencia.getValor(), OperacaoEnum.TRANSFERENCIA);
        addMovimento(contaCorrenteTo, movimentosTo, movimentoTo);
        return contaCorrenteTo;
    }

}
