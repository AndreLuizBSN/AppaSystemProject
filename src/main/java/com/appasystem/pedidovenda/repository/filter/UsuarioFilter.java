package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;

public class UsuarioFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
