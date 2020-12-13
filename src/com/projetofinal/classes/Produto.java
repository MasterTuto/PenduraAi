package com.projetofinal.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {
	private SimpleIntegerProperty codigo, categoria, marca, tipo;
	private SimpleStringProperty nome;
	private SimpleDoubleProperty preco;

	public Produto(int codigo, int categoria, int marca, int tipo, String nome, double preco) {
		this.codigo= new SimpleIntegerProperty(codigo);
		this.categoria= new SimpleIntegerProperty(categoria);
		this.marca= new SimpleIntegerProperty(marca);
		this.tipo= new SimpleIntegerProperty(tipo);
		this.nome= new SimpleStringProperty(nome);
		this.preco= new SimpleDoubleProperty(preco);
	}

	// Setters

	public int getCodigo() {
		return codigo.get();
	}

	public String getCategoria() {
		return Integer.toString(categoria.get());
	}

	public String getMarca() {
		return Integer.toString(marca.get());
	}

	public String getTipo() {
		return Integer.toString(tipo.get());
	}

	public String getNome() {
		return nome.get();
	}

	public double getPreco() {
		return preco.get();
	}

	// Setters

	public void setCategoria(int novaCategoria) {
		categoria.set(novaCategoria);
	}

	public void setMarca(int novaMarca) {
		marca.set(novaMarca);
	}

	public void setTipo(int novoTipo) {
		tipo.set(novoTipo);
	}

	public void setNome(String novoNome) {
		nome.set(novoNome);
	}

	public void setPreco(double novoPreco) {
		preco.set(novoPreco);
	}
}