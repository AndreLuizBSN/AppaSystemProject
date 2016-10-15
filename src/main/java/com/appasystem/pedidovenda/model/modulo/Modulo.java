package com.appasystem.pedidovenda.model.modulo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "modulo")
public class Modulo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private EModulos eModulos;
	private String diretorio;
	private String descricaoModulo;
	private String imagem;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	public EModulos geteModulos() {
		return eModulos;
	}
	public void seteModulos(EModulos eModulos) {
		this.eModulos = eModulos;
	}
	
	@NotBlank
	@Size(max = 255)
	@Column(nullable = false, length = 255)
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	
	public String getDescricaoModulo() {
		return descricaoModulo;
	}
	public void setDescricaoModulo(String descricaoModulo) {
		this.descricaoModulo = descricaoModulo;
	}
	
	@Column(nullable = false)
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
}
