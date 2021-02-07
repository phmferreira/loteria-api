package com.phmf.loteriaapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.phmf.loteriaapi.model.Aposta;

public interface ApostaRepository extends CrudRepository<Aposta, String>{

	public List<Aposta> findByPessoaId(String pessoaId);
}
