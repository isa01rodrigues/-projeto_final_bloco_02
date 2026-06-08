package com.farmacia.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.farmacia.farmacia.model.Categoria;
import com.farmacia.farmacia.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController //Informa ao Spring que esta classe é um Controller REST. //Os métodos retornarão dados em formato JSON automaticamente.
@RequestMapping("/categoria") //Define a rota principal da API. //Exemplo: localhost:8080/produto
@CrossOrigin(origins = "*", allowedHeaders = "*") //
public class CategoriaController {
	@Autowired
    private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
	    this.categoriaRepository = categoriaRepository;
	}

	// Listar todos os Atributos
		@GetMapping
		public ResponseEntity<List<Categoria>> getAll() {
			return ResponseEntity.ok(categoriaRepository.findAll());
		}

		// Lista por ID
		@GetMapping("/{id}") //
		public ResponseEntity<Categoria> getById(@PathVariable Long id) {
			return categoriaRepository.findById(id) // métodos de manipulação de dados no sql

					// .map metodo lambda
					.map(resposta -> ResponseEntity.ok(resposta)) // resposta -> representa o objeto recuperado dentro do
																	// Optional,ResponseEntity.ok() cria uma resposta HTTP
																	// 200 (OK) e envia o objeto encontrado para o usuário
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); 
																					
		}

		// Listar por foto
		@GetMapping("/foto/{foto}")
		public ResponseEntity<List<Categoria>> getByFoto(@PathVariable String foto) {

			return ResponseEntity.ok(categoriaRepository.findAllByFotoContainingIgnoreCase(foto));

		}

		// Operações do CRUD
		// Representa o INSERT do SQL (criar/inserir dados)

		@PostMapping
		public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {

			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
		}

		@PutMapping

		public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {

			if (categoriaRepository.existsById(categoria.getId())) {

				return ResponseEntity.ok(categoriaRepository.save(categoria));
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");
		}

		//Deletar
			// Representa o DELETE do SQL (remover dados)
				@ResponseStatus(HttpStatus.NO_CONTENT)
				@DeleteMapping("/{id}")
				public void delete(@PathVariable Long id) {
					Optional<Categoria> categoria = categoriaRepository.findById(id);
					// Se o id não existir, retorna erro 404
					if(categoria.isEmpty())
						throw new ResponseStatusException(HttpStatus.NOT_FOUND);
					
					categoriaRepository.deleteById(id);
					
				}
		
	
		
}

