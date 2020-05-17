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

@SuppressWarnings("serial")
@Entity
@Table(name = "espetaculo")
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

	public Set<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(Set<Venda> vendas) {
		this.vendas = vendas;
	}

	public Espetaculo() {

	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public LocalDate getDataEspetaculo() {
		return dataEspetaculo;
	}

	public void setDataEspetaculo(LocalDate dataEspetaculo) {
		this.dataEspetaculo = dataEspetaculo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public int getDestaque() {
		return destaque;
	}

	public void setDestaque(int destaque) {
		this.destaque = destaque;
	}

	

	public byte[] getImagemCasa() {
		return imagemCasa;
	}

	public void setImagemCasa(byte[] imagemCasa) {
		this.imagemCasa = imagemCasa;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagemEncoded() {
		return imagemEncoded;
	}

	public void setImagemEncoded(String imagemEncoded) {
		this.imagemEncoded = imagemEncoded;
	}

	public Espetaculo(String faixaEtaria, LocalDate dataEspetaculo, BigDecimal valor, int capacidade, int destaque,
			byte[] imagemCasa,  Casa casa, String descricao, String nome, Set<Venda> vendas) {
		super();
		this.faixaEtaria = faixaEtaria;
		this.dataEspetaculo = dataEspetaculo;
		this.valor = valor;
		this.capacidade = capacidade;
		this.destaque = destaque;
		this.imagemCasa = imagemCasa;
		this.casa = casa;
		this.descricao = descricao;
		this.nome = nome;
		this.vendas = vendas;
	}

	

}
