// VERIFICAR OS TIPOS DATA E HORA

package com.projetofinal.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Compra {
	private SimpleIntegerProperty codigo;
	private SimpleObjectProperty<Cliente> cliente;
	private SimpleStringProperty data, hora, descricao, dataVencimento;
	private SimpleDoubleProperty total;
	private SimpleObjectProperty<Pagamento> pagamento;
	private ObservableList<ItemCompra> itens = FXCollections.observableArrayList();

	public Compra(int codigo, Cliente cliente, String data, String hora, String descricao, String dataVencimento, double total) {
		this.codigo= new SimpleIntegerProperty(codigo);
		this.cliente= new SimpleObjectProperty<>(cliente);
		this.data= new SimpleStringProperty(data);
		this.hora= new SimpleStringProperty(hora);
		this.descricao= new SimpleStringProperty(descricao);
		this.dataVencimento= new SimpleStringProperty(dataVencimento);
		this.total= new SimpleDoubleProperty(total);
		this.pagamento = new SimpleObjectProperty<>(new Pagamento(213134, 1, 2, 2442.50, this));
	}

	public Compra(int codigo, Cliente cliente, String data, String hora, String descricao, String dataVencimento, double total, ItemCompra[] itens) {
		this.codigo= new SimpleIntegerProperty(codigo);
		this.cliente= new SimpleObjectProperty<>(cliente);
		this.data= new SimpleStringProperty(data);
		this.hora= new SimpleStringProperty(hora);
		this.descricao= new SimpleStringProperty(descricao);
		this.dataVencimento= new SimpleStringProperty(dataVencimento);
		this.total= new SimpleDoubleProperty(total);

		setItens(itens);
	}

	// Getters

	public int getCodigo() {
		return codigo.get();
	}

	public String getCliente() {
		if (cliente != null)
			return cliente.get().getNome() + " " + cliente.get().getSobrenome();
		else
			return "";
	}

	public String getData() {
		return data.get();
	}

	public String getHora() {
		return hora.get();
	}

	public String getDescricao() {
		return descricao.get();
	}

	public String getDataVencimento() {
		return dataVencimento.get();
	}

	public double getTotal() {
		return total.get();
	}

	public ObservableList<ItemCompra> getItens() {
		return itens;
	}

	public String getPagamento() {
		return pagamento.get().toString();
	}

	// Setters

	public void setDescricao(String novaDescricao) {
		descricao.set(novaDescricao);
	}

	public void setDataVencimento(String novaDataVencimento) {
		dataVencimento.set(novaDataVencimento);
	}

	public void setItens(ItemCompra[] itensArray) {
		total.set(0.0);
		itens.clear();

		for (ItemCompra item: itensArray) {
			total.set(total.get() + item.getPreco());

			itens.add(item);
		}
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento.set(pagamento);
	}

	public Pagamento setPagamento(int codigo, int meio, int numeroDeParcelas, double valor) {
		Pagamento nPagamento = new Pagamento(codigo, meio, numeroDeParcelas, valor, this);
		this.pagamento.set(nPagamento);

		return nPagamento;
	}
}