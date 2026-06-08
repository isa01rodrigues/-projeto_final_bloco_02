package com.farmacia.farmacia.controller;

import java.time.LocalDate;
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

import com.farmacia.farmacia.model.Produto;
import com.farmacia.farmacia.repository.CategoriaRepository;
import com.farmacia.farmacia.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController // Informa ao Spring que esta classe é um Controller REST. //Os métodos
				// retornarão dados em formato JSON automaticamente.
@RequestMapping("/produtos") // Define a rota principal da API. //Exemplo: localhost:8080/produto
@CrossOrigin(origins = "*", allowedHeaders = "*") //
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	// Lista por ID
	@GetMapping("/{id}") //
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id) // métodos de manipulação de dados no sql

				// .map metodo lambda
				.map(resposta -> ResponseEntity.ok(resposta)) // resposta -> representa o objeto recuperado dentro do
																// Optional,ResponseEntity.ok() cria uma resposta HTTP
																// 200 (OK) e envia o objeto encontrado para o usuário
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Se o Optional estiver vazio (nenhum
																				// objeto encontrado),retorna uma
																				// resposta HTTP 404 (NOT_FOUND) sem
																				// corpo na resposta.
	}

	// Listagem por nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));

	}

	
	
	//Listar por Validade
	@GetMapping("/validade/{dataValidade}")
	public ResponseEntity<List<Produto>> getByDataValidade(
	        @PathVariable LocalDate dataValidade) {

	    return ResponseEntity.ok(
	            produtoRepository.findAllByDataValidade(dataValidade));
	}

	// CRUD
	// Cadastrar
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {

	    if (categoriaRepository.existsById(
	            produto.getCategoria().getId())) {

	        produto.setId(null);

	        Produto saved = produtoRepository.save(produto);

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(saved);
	    }

	    throw new ResponseStatusException(
	            HttpStatus.BAD_REQUEST,
	            "Categoria não existe!",
	            null
	    );
	}

	// Atualizar
	@PutMapping
	public ResponseEntity<Produto> put(
	        @Valid @RequestBody Produto produto){

	    if (produtoRepository.existsById(produto.getId())){

	        if (categoriaRepository
	                .existsById(produto.getCategoria().getId()))

	            return ResponseEntity.status(HttpStatus.OK)
	                    .body(produtoRepository.save(produto));

	        throw new ResponseStatusException(
	                HttpStatus.BAD_REQUEST,
	                "Categoria não existe!",
	                null
	        );

	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	
	
	// Deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");

		produtoRepository.deleteById(id);
	}

}
