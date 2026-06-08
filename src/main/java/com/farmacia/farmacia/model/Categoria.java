package com.farmacia.farmacia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // erá utilizada para gerar uma tabela no Banco de dados da aplicação
@Table(name = "tb_categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gera IDs automaticamente
	private Long id;

	@NotBlank(message = "O atributo nome é Obrigatório!") //
	@Size(max = 100, message = "O nome da Categoria pode ter até 100 caracteres")
	private String nome;

	@NotBlank(message = "O atributo Descrição é Obrigatório!") //
	@Size(max = 300, message = "Informe a descrição: ")
	private String descricao;

	 //
	@Size(max = 100, message = "A foto pode ter até 100 caracteres")
	private String foto;
	
	 @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	    @JsonIgnoreProperties("categoria")
	    private List<Produto> produto;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

}
