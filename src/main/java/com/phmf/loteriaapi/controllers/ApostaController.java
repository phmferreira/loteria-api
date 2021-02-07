package com.phmf.loteriaapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phmf.loteriaapi.exceptions.SequenciaNumerosIguaisException;
import com.phmf.loteriaapi.model.Aposta;
import com.phmf.loteriaapi.model.Pessoa;
import com.phmf.loteriaapi.services.ApostaService;
import com.phmf.loteriaapi.services.PessoaService;

@RestController
public class ApostaController {

	@Autowired
	private ApostaService apostaService;
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping("/email/{email}/apostas")
	public List<Aposta> getAllApostaByEmail(@PathVariable String email) {
		Pessoa pessoa = pessoaService.getPessoaByEmail(email);
		return apostaService.getAllAposta(pessoa.getId());
	}
	
	@RequestMapping(method=RequestMethod.POST,
			value="/email/{email}/apostas")
	public Integer addApostaByEmail(@PathVariable String email) throws SequenciaNumerosIguaisException {
		Pessoa pessoa = pessoaService.getPessoaByEmail(email);
		return apostaService.addAposta(pessoa);
	}
	
	@RequestMapping("/pessoas/{pessoaId}/apostas")
	public List<Aposta> getAllAposta(@PathVariable String pessoaId) {
		return apostaService.getAllAposta(pessoaId);
	}
	
	@RequestMapping("/pessoas/{pessoaId}/apostas/{id}")
	public Aposta getAposta(@PathVariable String id) {
		return apostaService.getAposta(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,
			value="/pessoas/{pessoaId}/apostas")
	public void addAposta(@RequestBody Aposta aposta,
			@PathVariable String pessoaId) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaId);
		aposta.setPessoa(pessoa);
		apostaService.addAposta(aposta);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/pessoas/{pessoaId}/apostas/{id}")
	public void updateAposta(@RequestBody Aposta aposta,
			@PathVariable String pessoaId) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaId);
		aposta.setPessoa(pessoa);
		apostaService.addAposta(aposta);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/pessoas/{pessoaId}/apostas/{id}")
	public void deleteAposta(@PathVariable String id) {
		apostaService.deleteAposta(id);
	}
}