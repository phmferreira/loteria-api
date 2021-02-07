package com.phmf.loteriaapi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.phmf.loteriaapi.exceptions.SequenciaNumerosIguaisException;
import com.phmf.loteriaapi.model.Aposta;
import com.phmf.loteriaapi.model.Pessoa;
import com.phmf.loteriaapi.repositories.ApostaRepository;

@Service
public class ApostaService {

	@Autowired
	private ApostaRepository apostaRepository;
	@Autowired
	private Environment env;

	public List<Aposta> getAllAposta(String pessoaId) {
		List<Aposta> apostas = new ArrayList<Aposta>();
		apostaRepository.findByPessoaId(pessoaId).forEach(apostas::add);
		ordenarPorDataCadastro(apostas);
		return apostas;
	}

	private void ordenarPorDataCadastro(List<Aposta> apostas) {
		Collections.sort(apostas);
	}

	public Aposta getAposta(String id) {
		return apostaRepository.findById(id).orElse(null);
	}

	public void addAposta(Aposta aposta) {
		apostaRepository.save(aposta);
	}

	public void updateAposta(Aposta aposta) {
		apostaRepository.save(aposta);
	}

	public void deleteAposta(String id) {
		apostaRepository.deleteById(id);
	}

	private Integer gerarNumeroAleatorio(int semente, int indice) {
		Random random = new Random(semente);
		return random.ints(getMenorValor(), getMaiorValor() + 1).skip(indice).findFirst().getAsInt();
	}

	private Integer getMaiorValor() {
		return getPropertiesValorInterior("application.valorMaxNumerosGerados");
	}

	private Integer getMenorValor() {
		return getPropertiesValorInterior("application.valorMinNumerosGerados");
	}

	private Integer getPropertiesValorInterior(String property) {
		return Integer.parseInt(env.getProperty(property));
	}

	private Integer gerarSequenciaNumerosAleotorioIgual(Pessoa pessoa) {
		int qntApostas = apostaRepository.findByPessoaId(pessoa.getId()).size();
		int semente = pessoa.getEmail().hashCode();
		return gerarNumeroAleatorio(semente, qntApostas);
	}

	public Integer addAposta(Pessoa pessoa) throws SequenciaNumerosIguaisException {
		Aposta aposta = new Aposta();
		aposta.setPessoa(pessoa);
		aposta.setNumeroAleatorio(gerarSequenciaNumerosAleotorioIgual(pessoa));

		if (isSequenciasNumerosIguais(pessoa, aposta.getNumeroAleatorio())) {
			throw new SequenciaNumerosIguaisException(
					"Já há uma sequencia desses mesmos números apostados!");
		}

		apostaRepository.save(aposta);
		return aposta.getNumeroAleatorio();
	}

	private boolean isSequenciasNumerosIguais(Pessoa pessoa, int numeroAleatorio) {
		List<Aposta> apostas = getAllAposta(pessoa.getId());
		apostas.removeIf(t -> t.getNumeroAleatorio() != numeroAleatorio);
		return !apostas.isEmpty();
	}
}
