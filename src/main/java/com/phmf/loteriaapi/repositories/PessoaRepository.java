package com.phmf.loteriaapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.phmf.loteriaapi.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String> {

	public Optional<Pessoa> findByNome(String name);

	public Optional<Pessoa> findByEmail(String name);
}
