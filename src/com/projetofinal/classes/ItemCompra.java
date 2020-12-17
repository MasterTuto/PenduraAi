package com.projetofinal.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ItemCompra {
	private static int counter = 0;
	private SimpleIntegerProperty codigo;
	private SimpleObjectProperty<Produto> produto;
	private SimpleDoubleProperty preco;
	private SimpleIntegerProperty quantidade;
	private SimpleDoubleProperty subtotal;

	public ItemCompra() {
		this.codigo= new SimpleIntegerProperty(counter++);
	}
	
	public ItemCompra(Produto produto, double preco, int quantidade) {
		this.codigo= new SimpleIntegerProperty(counter++);
		this.produto= new SimpleObjectProperty<>(produto);
		this.preco= new SimpleDoubleProperty(preco);
		this.quantidade= new SimpleIntegerProperty(quantidade);
		this.subtotal = new SimpleDoubleProperty(preco*quantidade);
	}

	// Getters

	public int getCodigo() {
		return codigo.get();
	}

	public Produto getProdutoObj() {
		return produto.get();
	}

	public String getProduto() {
		return produto.get().getNome();
	}

	public double getPreco() {
		return preco.get();
	}

	public int getQuantidade() {
		return quantidade.get();
	}

	public double getSubtotal() {
		return subtotal.get();
	}

	// Setters

	public void setCodigo(int codigo) {
		this.codigo.set(codigo);
	}

	public void setProduto(Produto produto) {
		this.produto.set(produto);
	}

	public void setPreco(double preco) {
		this.preco.set(preco);
	}

	public void setQuantidade(int quantidade) {
		this.quantidade.set(quantidade);
	}
}