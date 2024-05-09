package com.example.demo.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long Id;
	
	private String nome;
	private Double preco;
	private String descricao;
	
	public Produto() {

	}
	
	public Produto(Long id, String nome, Double preco, String descricao) {
		Id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Produto [Id=" + Id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao + "]";
	}
	
}
