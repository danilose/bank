package com.accenture.bank;

import com.accenture.bank.model.Agencia;
import com.accenture.bank.model.Cliente;
import com.accenture.bank.model.ContaCorrente;
import com.accenture.bank.service.AgenciaService;
import com.accenture.bank.service.ClienteService;
import com.accenture.bank.service.ContaCorrenteService;
import com.accenture.bank.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {

	@Autowired
	private AgenciaService agenciaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ContaCorrenteService contaCorrenteService;

	@Autowired
	private MovimentoService movimentoService;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Agencia agencia1 = new Agencia();

		agencia1.setNome("Ag.0001");
		agencia1.setEndereco("Avenida Abc");
		agencia1.setFone("123456789");

		agenciaService.saveOrUpdate(agencia1);

		Agencia agencia2 = new Agencia();

		agencia2.setNome("Ag.0002");
		agencia2.setEndereco("Avenida Def");
		agencia2.setFone("123456789");

		agenciaService.saveOrUpdate(agencia2);

		Agencia agencia3 = new Agencia();

		agencia3.setNome("Ag.0003");
		agencia3.setEndereco("Avenida Ghi");
		agencia3.setFone("123456789");

		agenciaService.saveOrUpdate(agencia3);

		Cliente cliente1 = new Cliente();
		cliente1.setNome("Danilo Um");
		cliente1.setCpf("37116515852");
		cliente1.setFone("87967415");

		clienteService.saveOrUpdate(cliente1);

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Danilo Dois");
		cliente2.setCpf("37116515852");
		cliente2.setFone("87967415");

		clienteService.saveOrUpdate(cliente2);

		Cliente cliente3 = new Cliente();
		cliente3.setNome("Danilo Tres");
		cliente3.setCpf("37116515852");
		cliente3.setFone("87967415");

		clienteService.saveOrUpdate(cliente3);

		Cliente cliente4 = new Cliente();
		cliente4.setNome("Danilo Quatro");
		cliente4.setCpf("37116515852");
		cliente4.setFone("87967415");

		clienteService.saveOrUpdate(cliente4);

		List<Cliente> clientesAg1 = new ArrayList<Cliente>();
		clientesAg1.add(cliente1);
		clientesAg1.add(cliente4);

		agencia1.setClientes(clientesAg1);
		agenciaService.saveOrUpdate(agencia1);

		List<Cliente> clientesAg2 = new ArrayList<Cliente>();
		clientesAg2.add(cliente2);

		agencia2.setClientes(clientesAg2);
		agenciaService.saveOrUpdate(agencia2);

		List<Cliente> clientesAg3 = new ArrayList<Cliente>();
		clientesAg3.add(cliente1);
		clientesAg3.add(cliente3);

		agencia3.setClientes(clientesAg3);
		agenciaService.saveOrUpdate(agencia3);

		ContaCorrente contaCorrenteC1Ag1 = new ContaCorrente();
		contaCorrenteC1Ag1.setAgencia(agencia1);
		contaCorrenteC1Ag1.setNumero("123");
		contaCorrenteC1Ag1.setCliente(cliente1);

		contaCorrenteService.saveOrUpdate(contaCorrenteC1Ag1);

		ContaCorrente contaCorrenteC1Ag3 = new ContaCorrente();
		contaCorrenteC1Ag3.setAgencia(agencia3);
		contaCorrenteC1Ag3.setNumero("456");
		contaCorrenteC1Ag3.setCliente(cliente1);

		contaCorrenteService.saveOrUpdate(contaCorrenteC1Ag3);

		ContaCorrente contaCorrenteC2Ag2 = new ContaCorrente();
		contaCorrenteC2Ag2.setAgencia(agencia2);
		contaCorrenteC2Ag2.setNumero("789");
		contaCorrenteC2Ag2.setCliente(cliente2);

		contaCorrenteService.saveOrUpdate(contaCorrenteC2Ag2);

		ContaCorrente contaCorrenteC3Ag3 = new ContaCorrente();
		contaCorrenteC3Ag3.setAgencia(agencia3);
		contaCorrenteC3Ag3.setNumero("123");
		contaCorrenteC3Ag3.setCliente(cliente3);

		contaCorrenteService.saveOrUpdate(contaCorrenteC3Ag3);

		ContaCorrente contaCorrenteC4Ag1 = new ContaCorrente();
		contaCorrenteC4Ag1.setAgencia(agencia1);
		contaCorrenteC4Ag1.setNumero("123");
		contaCorrenteC4Ag1.setCliente(cliente4);

		contaCorrenteService.saveOrUpdate(contaCorrenteC4Ag1);

//		DepositoRequest depositoRequestCC1 = new DepositoRequest();
//		depositoRequestCC1.setContaFrom(contaCorrenteC1Ag1.getId());
//		depositoRequestCC1.setValor(5000.00);
//		contaCorrenteService.depositar(depositoRequestCC1);
//
//		DepositoRequest depositoRequestCC2 = new DepositoRequest();
//		depositoRequestCC2.setContaFrom(contaCorrenteC4Ag1.getId());
//		depositoRequestCC2.setValor(8000.00);
//		contaCorrenteService.depositar(depositoRequestCC2);
//
//		SaqueRequest saqueRequest = new SaqueRequest();
//		saqueRequest.setContaFrom(contaCorrenteC4Ag1.getId());
//		saqueRequest.setValor(1000.00);
//		contaCorrenteService.sacar(saqueRequest);
//
//		TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
//		transferenciaRequest.setContaFrom(contaCorrenteC4Ag1.getId());
//		transferenciaRequest.setContaTo(contaCorrenteC1Ag1.getId());
//		transferenciaRequest.setValor(4500.00);
//		contaCorrenteService.transferir(transferenciaRequest);

//		Movimento movimento = new Movimento();
//		movimento.setOperacaoEnum(OperacaoEnum.DEPOSITO);
//		movimento.setValor(5000.00);
//
//		movimentoService.saveOrUpdate(movimento);
//
//		List<Movimento> movimentos = new ArrayList<>();
//		movimentos.add(movimento);
//
//		contaCorrenteC4Ag1.setMovimento(movimentos);
//
//		contaCorrenteService.saveOrUpdate(contaCorrenteC4Ag1);

	}
}
