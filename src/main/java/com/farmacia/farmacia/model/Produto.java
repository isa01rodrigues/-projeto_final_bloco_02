package com.farmacia.farmacia.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity // erá utilizada para gerar uma tabela no Banco de dados da aplicação
@Table(name = "tb_produtos")

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gera IDs automaticamente
	private Long id;

	@NotBlank(message = "O nome é obrigatório!")
	@Size(max = 100, message = "O nome atribuido ao produto pode ter até 100 caracteres")
	private String nome;

	@NotBlank(message = "A marce é obrigatório!")
	@Size(max = 100, message = "A marca pode ter até 100 caracteres")
	private String marca;

	@NotBlank(message = "O Lote é obrigatório!")
	@Size(max = 100, message = "O lote pode ter até 100 caracteres")
	private String lote;

	private LocalDate dataFabricacao;

	private LocalDate dataValidade;

	@NotNull(message = "O preço é obrigatório!")
	private Double preco;

	@NotBlank(message = "O Lote é obrigatório!")
	@Size(max = 100, message = "O lote pode ter até 100 caracteres")
	private String foto;
	
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
