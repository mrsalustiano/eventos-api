package com.qintess.eventos.api.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "USUARIO")
public class Usuario extends AbstractEntity<Long> {

	@NotBlank(message = "Informe um login.")
	@Size(min = 3, max = 60, message = "O nome do usuario deve ter entre {min} e {max} caracteres.")
	@Column(name = "login", nullable = false, unique = true, length = 60)
	private String login;
	
	@NotBlank(message = "Informe uma senha.")
	@Size(min = 8, max = 60, message = "A senha deve ter entre {min} e {max} caracteres.")
	@Column(name = "senha", nullable = false, length = 60)
	private String Senha;
	

	@JsonIgnore
	@NotNull(message = "Selecione o perfil relativo ao usuario.")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "id_perfil_fk")
	private Perfil perfil;
	
	

	public Usuario() {
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getSenha() {
		return Senha;
	}



	public void setSenha(String senha) {
		Senha = senha;
	}



	public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}



	@Override
	public String toString() {
		return "Usuario [login=" + login + ", Senha=" + Senha + ", perfil=" + perfil + "]";
	}

		
}
