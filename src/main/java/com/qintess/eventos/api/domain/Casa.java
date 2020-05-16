package com.qintess.eventos.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "Casa_Show")
public class Casa extends AbstractEntity<Long> {
	
	private String nome;
	
	
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[] imagemCasa;
	
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


	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public byte[] getImagemCasa() {
		return imagemCasa;
	}

	public void setImagemCasa(byte[] imagemCasa) {
		this.imagemCasa = imagemCasa;
	}

	public String getImagemEncoded() {
		return imagemEncoded;
	}

	public void setImagemEncoded(String imagemEncoded) {
		this.imagemEncoded = imagemEncoded;
	}

	
	
	
	public String getUF() {
		return UF;
	}

	public void setUF(String UF) {
		this.UF = UF;
	}

	public Casa() {
	
	}

	public Casa(String nome, byte[] imagemCasa,  String logradouro, int numero, String complemento,
			String bairro, String cep, String cidade, String uF) {
		super();
		this.nome = nome;
		this.imagemCasa = imagemCasa;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		UF = uF;
	}





	
	
	

}
