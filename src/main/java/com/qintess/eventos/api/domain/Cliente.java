package com.qintess.eventos.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "cliente")
public class Cliente extends AbstractEntity<Long> {


	
	  @Column(name = "celular", nullable = false , length = 20)
	  private String celular;
	
	  
	  @Column(name = "cpf", nullable = false , length = 20)
	  private String cpf;
	  
	  @Column(name = "email", nullable = false , length = 80)
	  private String email;
	  
	  @Column(name = "newsletter", nullable = false)
	  private Boolean newsletter;
	  
	  @Column(name = "nome", nullable = false , length = 80)
	  private String nome;
	  
	  
	  @Column(name = "senhaCliente", nullable = false , length = 200)
	  private String senhaCliente;
	

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}




	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}


	
}
