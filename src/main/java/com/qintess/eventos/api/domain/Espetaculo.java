package com.qintess.eventos.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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




	@Column(nullable = false, length = 80)
	private String faixaEtaria;

	@Column(nullable = false, columnDefinition = "DATE")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	private LocalDate dataEspetaculo;

	@Column(nullable = false, scale = 2)
	private BigDecimal valor = new BigDecimal(0);

	@Column(nullable = false)
	private int capacidade;

	private int destaque;
	

	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[] imagemCasa;
	
	@Transient //esse campo não será persistido no hibernate
	private String imagemEncoded;
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "casa_id")
	public Casa casa;

	
	@Column(nullable = true, length = 10000)
	private String descricao;

	@Column(nullable = false, length = 80)
	private String nome;


	@OneToMany(mappedBy = "espetaculo", cascade = CascadeType.PERSIST)
	Set<Venda> vendas;


}
