package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private ProdutoRepository pr;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Produto p1 = new Produto(1L, "Computador" , 2.988, "Dell - 8° geração");
		pr.save(p1);
		System.out.println(p1);
		
	}

	
	
}
