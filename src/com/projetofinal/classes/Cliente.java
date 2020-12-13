package com.projetofinal.classes;

import java.util.Calendar;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
	private SimpleIntegerProperty codigo;
	private SimpleLongProperty cpf;
	private SimpleObjectProperty<Calendar> dataNascimento;
	private SimpleStringProperty nome, sobrenome, email, telefone, whatsapp;
	private SimpleObjectProperty<Endereco> endereco;
	private SimpleDoubleProperty limite;

	public Cliente(int codigo, long cpf, String nome, Calendar dataNascimento, String sobrenome, String email, String telefone,
				   String whatsapp, Endereco endereco, double limite) {

		this.codigo= new SimpleIntegerProperty(codigo);
		this.cpf= new SimpleLongProperty(cpf);
		this.nome= new SimpleStringProperty(nome);
		this.dataNascimento= new SimpleObjectProperty<>(dataNascimento);
		this.sobrenome= new SimpleStringProperty(sobrenome);
		this.email= new SimpleStringProperty(email);
		this.telefone= new SimpleStringProperty(telefone);
		this.whatsapp= new SimpleStringProperty(whatsapp);
		this.endereco= new SimpleObjectProperty<>(endereco);
		this.limite= new SimpleDoubleProperty(limite);
	}

	// Getters
	public int getCodigo() {
		return codigo.get();
	}

	public long getCpf() {
		return cpf.get();
	}

	public String getNome() {
		return nome.get();
	}

	public String getDataNascimento() {
		return dataNascimento.get().get(Calendar.DAY_OF_MONTH) + "/" +
			   dataNascimento.get().get(Calendar.MONTH) + "/" +
			   dataNascimento.get().get(Calendar.YEAR);
	}

	public String getSobrenome() {
		return sobrenome.get();
	}

	public String getEmail() {
		return email.get();
	}

	public String getTelefone() {
		return telefone.get();
	}

	public String getWhatsapp() {
		return whatsapp.get();
	}

	public String getEndereco() {
		return endereco.get().toString();
	}

	public double getLimite() {
		return limite.get();
	}

	// Setters

	public void setCpf(long novoCpf) {
		cpf.set(novoCpf);
	}

	public void setNome(String novoNome) {
		nome.set(novoNome);
	}

	public void setDataNascimento(Calendar novaDataNascimento) {
		dataNascimento.set(novaDataNascimento);
	}

	public void setSobrenome(String novoSobrenome) {
		sobrenome.set(novoSobrenome);
	}

	public void setEmail(String novoEmail) {
		email.set(novoEmail);
	}

	public void setTelefone(String novoTelefone) {
		telefone.set(novoTelefone);
	}

	public void setWhatsapp(String novoWhatsapp) {
		whatsapp.set(novoWhatsapp);
	}

	public void setEndereco(Endereco novoEndereco) {
		endereco.set(novoEndereco);
	}

	public void setLimite(double novoLimite) {
		limite.set(novoLimite);
	}
}