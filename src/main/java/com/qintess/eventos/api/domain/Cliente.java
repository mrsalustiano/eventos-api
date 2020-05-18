package com.qintess.eventos.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	  
	  @Getter(onMethod = @__({@JsonIgnore}))
	  @Setter(onMethod = @__({@JsonProperty}))
	  @Column(name = "senhaCliente", nullable = false , length = 200)
	  private String senhaCliente;
	


	
}
