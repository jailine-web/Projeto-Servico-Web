package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Produto;
import com.example.demo.services.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {

		Produto prod = produtoService.SalvarProduto(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(prod);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> buscarProdutos() {
		List<Produto> produtos = produtoService.buscarProdutos();
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
		Produto produto = produtoService.buscarProdutoPorId(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
	 produtoService.deletarProduto(id);
	 return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto, @PathVariable Long id){
		Produto prod = produtoService.atualizarProduto(produto, id);
		return ResponseEntity.ok().body(prod);
		
	}

}

