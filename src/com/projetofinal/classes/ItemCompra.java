package com.projetofinal.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ItemCompra {
	private SimpleIntegerProperty codigo;
	private SimpleObjectProperty<Compra> compra;
	private SimpleObjectProperty<Produto> produto;
	private SimpleDoubleProperty preco;
	private SimpleIntegerProperty quantidade;
	
	public ItemCompra(int codigo, Compra compra, Produto produto, double preco, int quantidade) {
		this.codigo= new SimpleIntegerProperty(codigo);
		this.compra= new SimpleObjectProperty<>(compra);
		this.produto= new SimpleObjectProperty<>(produto);
		this.preco= new SimpleDoubleProperty(preco);
		this.quantidade= new SimpleIntegerProperty(quantidade);
	}

	// Getters

	public int getCodigo() {
		return codigo.get();
	}

	public Compra getCompra() {
		return compra.get();
	}

	public Produto getProduto() {
		return produto.get();
	}

	public double getPreco() {
		return quantidade.get()*preco.get();
	}

	public int getQuantidade() {
		return quantidade.get();
	}

	// Setters

	public void setCodigo(int codigo) {
		this.codigo.set(codigo);
	}

	public void setCompra(Compra compra) {
		this.compra.set(compra);
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