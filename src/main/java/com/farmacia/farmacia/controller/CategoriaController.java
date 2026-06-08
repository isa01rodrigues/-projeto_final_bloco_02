package com.farmacia.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmacia.model.Categoria;
import com.farmacia.farmacia.repository.CategoriaRepository;

@RestController //Informa ao Spring que esta classe é um Controller REST. //Os métodos retornarão dados em formato JSON automaticamente.
@RequestMapping("/categoria") //Define a rota principal da API. //Exemplo: localhost:8080/produto
@CrossOrigin(origins = "*", allowedHeaders = "*") //
public class CategoriaController {
	@Autowired
    private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
	    this.categoriaRepository = categoriaRepository;
	}

	//Listar todos os Atributos
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
	    return ResponseEntity.ok(categoriaRepository.findAll());
	}
	

	//Lista por ID
		@GetMapping("/{id}") //
		public ResponseEntity<Categoria> getById(@PathVariable Long id){
			return categoriaRepository.findById(id) //métodos de manipulação de dados no sql
					
					//.map metodo lambda
					.map(resposta -> ResponseEntity.ok(resposta)) // resposta -> representa o objeto recuperado dentro do Optional,ResponseEntity.ok() cria uma resposta HTTP 200 (OK) e envia o objeto encontrado para o usuário
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());  //Se o Optional estiver vazio (nenhum objeto encontrado),retorna uma resposta HTTP 404 (NOT_FOUND) sem corpo na resposta.
		}
		
		//Listar por foto
		@GetMapping("/foto/{foto}")
		public ResponseEntity<List<Categoria>> getByFoto(@PathVariable String foto){

		    return ResponseEntity.ok(
		        categoriaRepository.findAllByFotoContainingIgnoreCase(foto)
		    );

		}
		
}

