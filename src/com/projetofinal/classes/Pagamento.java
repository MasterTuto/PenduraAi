package com.projetofinal.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Pagamento {
	private static int counter = 0;
	private SimpleIntegerProperty codigo, meio, numeroDeParcelas;
	private SimpleDoubleProperty valor;
	private SimpleObjectProperty<Compra> compra;

	public Pagamento() {
		this.codigo= new SimpleIntegerProperty(counter++);
	}

	public Pagamento(int meio, int numeroDeParcelas, double valor, Compra compra) {
		this.codigo= new SimpleIntegerProperty(counter++);
		this.meio= new SimpleIntegerProperty(meio);
		this.numeroDeParcelas= new SimpleIntegerProperty(numeroDeParcelas);
		this.valor= new SimpleDoubleProperty(valor);
		this.compra= new SimpleObjectProperty<>(compra);
	}

	// Getters

	public int getCodigo() {
		return codigo.get();
	}

	public int getMeio() {
		return meio.get();
	}

	public int getNumeroDeParcelas() {
		return numeroDeParcelas.get();
	}

	public double getValor() {
		return valor.get();
	}

	public Compra getCompra() {
		return compra.get();
	}

	// Setters, acho que nao precisa, mas ja digitei :/ kkkk

	public void setMeio(int novoMeio) {
		meio.set(novoMeio);
	}

	public void setNumeroDeParcelas(int novoNumeroDeParcelas) {
		numeroDeParcelas.set(novoNumeroDeParcelas);
	}

	public void setValor(double novoValor) {
		valor.set(novoValor);
	}

	public void setCompra(Compra novaCompra) {
		compra.set(novaCompra);
	}

	@Override
	public String toString() {
		return meio.get() == 1 ? "Credito (" : (meio.get() == 2 ? "Debito (" : "Dinheiro (") + (Integer.toString(numeroDeParcelas.get())) + ")";
	}

	public void decrementarContador() {
		counter--;
	}
}