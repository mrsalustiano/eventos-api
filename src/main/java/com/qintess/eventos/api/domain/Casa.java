package com.qintess.eventos.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

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
	
	@NotNull(message = "O Nome é obrigatório")
	@Column(nullable = false, length = 80 )
	private String nome;
	
	
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[]  imagemCasa;
	
	@Transient //esse campo não será persistido no hibernate
	private String imagemEncoded;
	
	// parte Endereco	
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
	
	
	
	@NotBlank(message = "O CEP é obrigatório")
	@Column(name = "cep", nullable = false, length = 9)
	private String cep;
	
	@NotBlank(message = "A Cidade é obrigatório")
	@Column(name = "cidade", nullable = false, length = 40)
	private String cidade;
	
	@NotBlank(message = "A UF é obrigatório")
	@Column(name = "uf", nullable = false, length = 2)
	private String UF;


}
