package com.accenture.bank;

import com.accenture.bank.model.Agencia;
import com.accenture.bank.model.Cliente;
import com.accenture.bank.model.ContaCorrente;
import com.accenture.bank.repository.AgenciaRepository;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.ContaCorrenteRepository;
import com.accenture.bank.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {

	@Autowired
	private AgenciaRepository agenciaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	private MovimentoRepository movimentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Agencia agencia = new Agencia();

		agencia.setNome("Ag.0001");
		agencia.setEndereco("Avenida Abc");
		agencia.setFone("65489871");

		agenciaRepository.save(agencia);

		Cliente cliente = new Cliente();
		cliente.setNome("Danilo Elias");
		cliente.setCpf("37116515852");
		cliente.setFone("87967415");
		List<Agencia> agencias = new ArrayList<Agencia>();
		agencias.add(agencia);
		cliente.setAgencias(agencias);

		clienteRepository.save(cliente);

		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setAgencia(agencia);
		contaCorrente.setNumero("651651");
		contaCorrente.setCliente(cliente);

		contaCorrenteRepository.save(contaCorrente);

	}
}
