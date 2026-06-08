package com.farmacia.farmacia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity  //erá utilizada para gerar uma tabela no Banco de dados da aplicação
@Table(name = "tb_categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //gera IDs automaticamente
	private Long id;
	
	@NotBlank (message = "O atributo nome é Obrigatório!")  //
	@Size( max = 100, message = "O nome da Categoria pode ter até 100 caracteres")
    private String nome;
	

	@NotBlank (message = "O atributo Descrição é Obrigatório!")  //
	@Size( max = 300, message = "Informe a descrição: ")
    private String descricao;
	

	@NotBlank (message = "O atributo Foto é Obrigatório!")  //
	@Size( max = 100, message = "A foto pode ter até 100 caracteres")
    private String foto;
	
}
