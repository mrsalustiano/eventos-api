package com.qintess.eventos.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "cliente")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends AbstractEntity<Long> {

	@NotEmpty(message = "O numero do Celular é obrigatório")
	@Column(name = "celular", nullable = false, length = 20)
	private String celular;

	@NotBlank(message = "O CPF é obrigatório e unico")
	@Column(name = "cpf", nullable = false, length = 20, unique = true)
	private String cpf;

	@NotBlank(message = "O Email é obrigatório")
	@Column(name = "email", nullable = false, length = 80, unique = true)
	private String email;


	@Column(name = "newsletter", nullable = false)
	private Boolean newsletter;

	@NotBlank(message = "O Nome é obrigatório")
	@Column(name = "nome", nullable = false, length = 80)
	private String nome;

	@NotBlank(message = "A Senha é obrigatória")
	@Getter(onMethod = @__({ @JsonIgnore }))
	@Setter(onMethod = @__({ @JsonProperty }))
	@Column(name = "senhaCliente", nullable = false, length = 200)
	private String senhaCliente;
	
	@NotBlank(message = "O Logradouro é obrigatório")
	@Column(name = "logradouro", nullable = false, length = 80)
	private String logradouro;
	
	@NotNull(message = "O Numero é obrigatório")
	@Range(min=1, max = 99999, message= "O valor minimo é 1 e maximo 9999" )
	@Column(name = "numero", nullable = false)
	private int numero;
	

	@Column(name = "complemento", nullable = true, length = 40)
	private String complemento;
	
	@NotBlank(message = "O Bairro é obrigatório")
	@Column(name = "bairro", nullable = false, length = 40)
	private String bairro;
	
	@NotBlank(message = "A Cidade é obrigatório")
	@Column(name = "cidade", nullable = false, length = 40)
	private String cidade;
	
	@NotBlank(message = "A UF é obrigatório")
	@Column(name = "uf", nullable = false, length = 2)
	private String uf;
	
	@NotBlank(message = "O CEP é obrigatório")
	@Column(name = "cep", nullable = false, length = 9)
	private String cep;
	

}
