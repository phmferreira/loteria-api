package com.phmf.loteriaapi.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Aposta implements Comparable<Aposta> {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Integer numeroAleatorio;
	@ManyToOne
	private Pessoa pessoa;
	private Date data_cadastro;

	public Aposta() {
		this.data_cadastro = Calendar.getInstance().getTime();
	}

	public Aposta(Integer numeroAleatorio, String pessoaId) {
		super();
		this.numeroAleatorio = numeroAleatorio;
		this.pessoa = new Pessoa();
		this.pessoa.setId(pessoaId);
		this.data_cadastro = Calendar.getInstance().getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumeroAleatorio() {
		return numeroAleatorio;
	}

	public void setNumeroAleatorio(Integer numeroAleatorio) {
		this.numeroAleatorio = numeroAleatorio;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	@Override
	public int compareTo(Aposta aposta) {
		return getData_cadastro().compareTo(aposta.getData_cadastro());
	}
}
