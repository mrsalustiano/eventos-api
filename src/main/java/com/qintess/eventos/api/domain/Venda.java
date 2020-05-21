package com.qintess.eventos.api.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda extends AbstractEntity<Long>{


	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id" , nullable = false)
	Cliente Cliente;
	

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "espetaculo_id", nullable = false)
	Espetaculo Espetaculo;
	
	@NotNull(message = "A Quantidade é obrigatoria") 
	@Column(name= "Quantidade" , nullable = false)
	private int quantidade;
	
	
	@Column(name = "Valor" , nullable = false)
	private BigDecimal valor;

	
}
