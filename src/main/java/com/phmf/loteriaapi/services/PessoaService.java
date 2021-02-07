package com.phmf.loteriaapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phmf.loteriaapi.model.Pessoa;
import com.phmf.loteriaapi.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> getAllPessoas() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoaRepository.findAll().forEach(pessoas::add);
		return pessoas;
	}

	public Pessoa getPessoaByEmail(String email) {
		return pessoaRepository.findByEmail(email).orElse(null);
	}

	public void addPessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	public Pessoa getPessoa(String id) {
		return pessoaRepository.findById(id).orElse(null);
	}

	public void updatePessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	public void deletePessoa(String id) {
		pessoaRepository.deleteById(id);
	}
}
