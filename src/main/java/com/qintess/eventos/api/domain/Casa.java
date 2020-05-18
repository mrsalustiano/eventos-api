package com.qintess.eventos.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "Casa_Show")
@AllArgsConstructor
@Getter 
@Setter
@NoArgsConstructor
public class Casa extends AbstractEntity<Long> {
	
	private String nome;
	
	
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[]  imagemCasa;
	
	@Transient //esse campo não será persistido no hibernate
	private String imagemEncoded;
	
	
	// parte Endereco	
	@Column(nullable = false, length = 80)
	private String logradouro;
	
	@Column(nullable = true)
	private int numero;
	
	@Column(nullable = true, length = 80)
	private String complemento;
	
	@Column(nullable = true, length = 50)
	private String bairro;
	
	@Column(nullable = false, length = 9)
	private String cep;
	
	@Column(nullable = false, length = 80)
	private String cidade;
	
	@Column(nullable = false, length = 2 , name = "UF")
	private String UF;


}
