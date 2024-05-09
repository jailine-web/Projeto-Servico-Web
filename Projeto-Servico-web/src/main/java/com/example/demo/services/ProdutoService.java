package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto SalvarProduto(Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}

	@Transactional(readOnly = true)
	public List<Produto> buscarProdutos(){
		List<Produto> produtos = new ArrayList<>();
		produtos = produtoRepository.findAll();
		return produtos;
	}
}
