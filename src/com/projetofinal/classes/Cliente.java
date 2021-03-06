package com.projetofinal.classes;

import java.util.Calendar;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
	private static int counter = 0;
	private SimpleIntegerProperty codigo;
	private SimpleObjectProperty<Calendar> dataNascimento;
	private SimpleStringProperty nome, sobrenome, email, telefone, whatsapp, cpf;
	private SimpleObjectProperty<Endereco> endereco;
	private SimpleDoubleProperty limite;

	public Cliente() {
		this.codigo= new SimpleIntegerProperty(counter++);
		this.cpf= new SimpleStringProperty("");
		this.nome= new SimpleStringProperty("");
		this.dataNascimento= new SimpleObjectProperty<>(null);
		this.sobrenome= new SimpleStringProperty("");
		this.email= new SimpleStringProperty("");
		this.telefone= new SimpleStringProperty("");
		this.whatsapp= new SimpleStringProperty("");
		this.endereco= new SimpleObjectProperty<>(null);
		this.limite= new SimpleDoubleProperty(500.0);
	}

	public Cliente(String cpf, String nome, Calendar dataNascimento, String sobrenome, String email, String telefone,
				   String whatsapp, Endereco endereco, double limite) {

		this.codigo= new SimpleIntegerProperty(counter++);
		this.cpf= new SimpleStringProperty(cpf);
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

	public String getNomeCompleto() {
		return nome.get() + " " + sobrenome.get();
	}

	public String getCpf() {
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

	public Endereco getEnderecoObj() {
		return endereco.get();
	}
	
	public Calendar getDataNascimentoObj() {
		return dataNascimento.get();
	}
	public double getLimite() {
		return limite.get();
	}

	// Setters

	public void setCpf(String novoCpf) {
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


	public void decrementarContador() {
		counter--;
	}

}