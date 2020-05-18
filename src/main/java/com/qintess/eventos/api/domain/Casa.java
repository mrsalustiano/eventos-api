package com.qintess.eventos.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank
	private String nome;
	
	
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[]  imagemCasa;
	
	@Transient //esse campo não será persistido no hibernate
	private String imagemEncoded;
	
	@NotBlank
	// parte Endereco	
	@Column(nullable = false, length = 80)
	private String logradouro;
	
	@NotBlank
	@Column(nullable = true)
	private int numero;
	
	@NotBlank
	@Column(nullable = true, length = 80)
	private String complemento;
	
	@NotBlank
	@Column(nullable = true, length = 50)
	private String bairro;
	
	@NotBlank
	@Column(nullable = false, length = 9)
	private String cep;
	
	@NotBlank
	@Column(nullable = false, length = 80)
	private String cidade;
	
	@NotBlank
	@Column(nullable = false, length = 2 , name = "UF")
	private String UF;


}
