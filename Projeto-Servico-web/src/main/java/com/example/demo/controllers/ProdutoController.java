package com.example.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.entities.Produto;
import com.example.demo.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	@Operation(summary = "Endpoint: cadastra um novo produto na base de dados")
	@ApiResponses (value = {
			@ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados incorretos, por favor insira dados válidos")
			})
	public ResponseEntity<Produto> salvarProduto(@RequestBody ProdutoDTO produtoDTO) {

		Produto prod = new Produto(produtoDTO.getId(), produtoDTO.getNome(), produtoDTO.getPreco(), produtoDTO.getDescricao());
		produtoService.SalvarProduto(prod);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prod.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(prod);
	}

	@GetMapping
	@Operation(summary = "Endpoint: retorna todos os produtos cadastrados")
	@ApiResponses (value = {
			@ApiResponse(responseCode = "200", description = "Lista dos produtos cadastrados"),
			@ApiResponse(responseCode = "400", description = "Nenhum produto disponível")
			})
	public ResponseEntity<List<ProdutoDTO>> buscarProdutos() {
		
		List<Produto> produtos = produtoService.buscarProdutos();
		List<ProdutoDTO> produtosDTO = produtos.stream().
				map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(produtosDTO);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Endpoint: retorna um produto cadastrado mediante o id")
	@ApiResponses (value = {
			@ApiResponse(responseCode = "200", description = "Retorna um produto cadastrado"),
			@ApiResponse(responseCode = "400", description = "Id não encontrado!")
			})
	public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
		Produto produto = produtoService.buscarProdutoPorId(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@DeleteMapping (value = "/{id}")
	@Operation(summary = "Endpoint: deleta um produto por meio do seu identificador")
	@ApiResponses (value = {
			@ApiResponse(responseCode = "204", description = "Produto deletado com sucesso!"),
			@ApiResponse(responseCode = "400", description = "Nenhum produto disponível")
			})
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
	 produtoService.deletarProduto(id);
	 return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary = "Endpoint: atualiza um produto por meio do seu identificador")
	@ApiResponses (value = {
			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso!"),
			@ApiResponse(responseCode = "400", description = "Nenhum produto encontrado")
			})
	public ResponseEntity<Produto> atualizarProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable Long id){
		Produto prod = produtoService.produtoDto(produtoDTO);
		prod.setId(id);
		
		produtoService.atualizarProduto(prod, id);
		return ResponseEntity.ok().body(prod);
		
	}

}

