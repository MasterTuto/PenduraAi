package com.projetofinal.classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Endereco {
	private static int counter = 0;
	private SimpleIntegerProperty codigo;
	private SimpleStringProperty numeroCasa, rua, cep, bairro, municipio, estado;

	public Endereco() {
		this.codigo= new SimpleIntegerProperty(counter++);
	}

	public Endereco(String numeroCasa, String rua, String cep, String bairro, String municipio, String estado) {
		this.codigo= new SimpleIntegerProperty(counter++);
		this.numeroCasa= new SimpleStringProperty(numeroCasa);
		this.rua= new SimpleStringProperty(rua);
		this.cep= new SimpleStringProperty(cep);
		this.bairro= new SimpleStringProperty(bairro);
		this.municipio= new SimpleStringProperty(municipio);
		this.estado= new SimpleStringProperty(estado);
	}

	// Getters

	public int getCodigo() {
		return codigo.get();
	}

	public String getNumeroCasa() {
		return numeroCasa.get();
	}

	public String getRua() {
		return rua.get();
	}

	public String getCep() {
		return cep.get();
	}

	public String getBairro() {
		return bairro.get();
	}

	public String getMunicipio() {
		return municipio.get();
	}

	public String getEstado() {
		return estado.get();
	}

	// Setters

	public void setNumeroCasa(String novoNumeroCasa) {
		numeroCasa.set(novoNumeroCasa);
	}

	public void setRua(String novaRua) {
		rua.set(novaRua);
	}

	public void setCep(String novoCep) {
		cep.set(novoCep);
	}

	public void setBairro(String novoBairro) {
		bairro.set(novoBairro);
	}

	public void setMunicipio(String novoMunicipio) {
		municipio.set(novoMunicipio);
	}

	public void setEstado(String novoEstado) {
		estado.set(novoEstado);
	}

	// Outros

	@Override
	public String toString() {
		return rua.get() + ", nº " + numeroCasa.get() + ". " + bairro.get() + ", " + municipio.get() + " - " + estado.get();
	}

	public void decrementarContador() {
		counter--;
	}
}