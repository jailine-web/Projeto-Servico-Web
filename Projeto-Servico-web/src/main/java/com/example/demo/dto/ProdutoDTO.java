package com.example.demo.dto;

import com.example.demo.entities.Produto;

public class ProdutoDTO {

	private Long id;
	private String nome;
	private double preco;
	private String descricao;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto produto) {

		id = produto.getId();
		nome = produto.getNome();
		preco = produto.getPreco();
		descricao = produto.getDescricao();
	}

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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
