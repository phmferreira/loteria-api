package com.phmf.loteriaapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phmf.loteriaapi.model.Pessoa;
import com.phmf.loteriaapi.services.PessoaService;

@RestController
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping("/pessoas")
	public List<Pessoa> getAllPessoas() {
		return pessoaService.getAllPessoas();
	}
	
	@RequestMapping("/pessoas/{id}")
	public Pessoa getPessoa(@PathVariable String id) {
		return pessoaService.getPessoa(id);
	}
	
	@RequestMapping("/pessoas/email/{email}")
	public Pessoa getPessoaByEmail(@PathVariable String email) {
		return pessoaService.getPessoaByEmail(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/pessoas")
	public void addPessoa(@RequestBody Pessoa pessoa) {
		pessoaService.addPessoa(pessoa);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/pessoas/{id}")
	public void updatePessoa(@RequestBody Pessoa pessoa) {
		pessoaService.updatePessoa(pessoa);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/pessoas/{id}")
	public void deletePessoa(@PathVariable String id) {
		pessoaService.deletePessoa(id);
	}
}
