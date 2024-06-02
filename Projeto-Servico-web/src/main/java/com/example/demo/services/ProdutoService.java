package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto SalvarProduto(Produto produto) {
		Produto prod = produtoRepository.save(produto);
		return prod;
	}

	@Transactional(readOnly = true)
	public List<Produto> buscarProdutos() {
		List<Produto> produtos = new ArrayList<>();
		produtos = produtoRepository.findAll();
		return produtos;
	}

	@Transactional(readOnly = true)
	public Produto buscarProdutoPorId(Long id) {
		Produto prod = produtoRepository.getReferenceById(id);

		Produto produto = new Produto(prod.getId(), prod.getNome(), prod.getPreco(), prod.getDescricao());
		return produto;
	}

	@Transactional
	public void deletarProduto( Long id) {

		produtoRepository.deleteById(id);

	}
	
	@Transactional
	public Produto atualizarProduto(Produto produto, Long id) {
		
		Produto produtoNovo = new Produto();
		
		try {
			
			produtoNovo = produtoRepository.getReferenceById(id);
			
			produtoNovo.setNome(produto.getNome());
			produtoNovo.setPreco(produto.getPreco());
			produtoNovo.setDescricao(produto.getDescricao());
			
		}
		
		catch(RuntimeException e) {
			throw new RuntimeException("Identificador não encontrado");
		}
		
		return produtoRepository.save(produtoNovo);
		
	}
	
	public Produto produtoDto (ProdutoDTO produtoDto) {
		return new Produto(produtoDto.getId(), produtoDto.getNome(), produtoDto.getPreco(), produtoDto.getDescricao());
		
	}

}
