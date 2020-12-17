package com.projetofinal.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {
	public static int counter = 0;
	private SimpleIntegerProperty codigo;
	private SimpleStringProperty nome, categoria, marca, tipo;;
	private SimpleDoubleProperty preco;

	public Produto() {
		this.codigo= new SimpleIntegerProperty(counter++);
	}

	public Produto(String categoria, String marca, String tipo, String nome, double preco) {
		this.codigo= new SimpleIntegerProperty(counter++);
		this.categoria= new SimpleStringProperty(categoria);
		this.marca= new SimpleStringProperty(marca);
		this.tipo= new SimpleStringProperty(tipo);
		this.nome= new SimpleStringProperty(nome);
		this.preco= new SimpleDoubleProperty(preco);
	}

	// Setters

	public int getCodigo() {
		return codigo.get();
	}

	public String getCategoria() {
		return categoria.get();
	}

	public String getMarca() {
		return marca.get();
	}

	public String getTipo() {
		return tipo.get();
	}

	public String getNome() {
		return nome.get();
	}

	public double getPreco() {
		return preco.get();
	}

	// Setters

	public void setCategoria(String novaCategoria) {
		categoria.set(novaCategoria);
	}

	public void setMarca(String novaMarca) {
		marca.set(novaMarca);
	}

	public void setTipo(String novoTipo) {
		tipo.set(novoTipo);
	}

	public void setNome(String novoNome) {
		nome.set(novoNome);
	}

	public void setPreco(double novoPreco) {
		preco.set(novoPreco);
	}

}