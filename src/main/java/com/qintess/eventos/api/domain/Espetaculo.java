package com.qintess.eventos.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "espetaculo")
@AllArgsConstructor
@Getter 
@Setter
@NoArgsConstructor
public class Espetaculo extends AbstractEntity<Long> {




	@NotBlank(message = "A Faixa etária é obrigatória")
	@Column(nullable = false, length = 80)
	private String faixaEtaria;

	@NotNull(message = "A data é obrigatória")
	@Future(message = "A Data deve ser maior que a data de hoje")
	@Column(nullable = false, columnDefinition = "DATE")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	private LocalDate dataEspetaculo;

	@NotNull
	@Digits(integer=4,fraction=2,message="Apenas 2 casas após o ponto.")
	private BigDecimal valor;

	@NotNull(message = "A Capacidade é obrigatória")
	@Column(nullable = false)
	private int capacidade;

	private int destaque;
	

	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[] imagemCasa;
	
	@Transient //esse campo não será persistido no hibernate
	private String imagemEncoded;
	
	@NotNull(message = "A Casa de Show é obrigatória")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "casa_id", nullable = false)
	public Casa casa;

	
	@Column(nullable = true, length = 10000)
	private String descricao;

	@NotNull(message = "O Nome é obrigatório")
	@Column(nullable = false, length = 80)
	private String nome;


//	@OneToMany(mappedBy = "espetaculo", cascade = CascadeType.PERSIST)
//	Set<Venda> vendas;


}
