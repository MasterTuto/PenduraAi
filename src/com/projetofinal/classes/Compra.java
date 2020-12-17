// VERIFICAR OS TIPOS DATA E HORA

package com.projetofinal.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Compra {
	private static int counter = 0;
	private SimpleIntegerProperty codigo;
	private SimpleObjectProperty<Cliente> cliente;
	private SimpleStringProperty data, hora, descricao, dataVencimento;
	private SimpleDoubleProperty total;
	private SimpleObjectProperty<Pagamento> pagamento;

	private ObservableList<ItemCompra> itens = FXCollections.observableArrayList();

	public Compra(){
		this.codigo= new SimpleIntegerProperty(counter++);
		total = new SimpleDoubleProperty(0.0);
	}

	public Compra(Cliente cliente, String data, String hora, String descricao, String dataVencimento, double total) {
		this.codigo= new SimpleIntegerProperty(counter++);
		this.cliente= new SimpleObjectProperty<>(cliente);
		this.data= new SimpleStringProperty(data);
		this.hora= new SimpleStringProperty(hora);
		this.descricao= new SimpleStringProperty(descricao);
		this.dataVencimento= new SimpleStringProperty(dataVencimento);
		this.total= new SimpleDoubleProperty(total);
	}

	public Compra(Cliente cliente, String data, String hora, String descricao, String dataVencimento, double total, ItemCompra[] itens) {
		this.codigo= new SimpleIntegerProperty(counter++);
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
		if (pagamento == null)
			return "<sem pagamento>";
		else
			return pagamento.get().toString();
	}

	// Setters

	public void setCliente(Cliente cliente) {
		if (this.cliente == null)
			this.cliente= new SimpleObjectProperty<>(cliente);
		else
			this.cliente.set(cliente);
	}

	public void setData(String data) {
		if (this.data == null)
			this.data= new SimpleStringProperty(data);
		else
			this.data.set(data);
	}

	public void setDescricao(String novaDescricao) {
		if (this.descricao == null)
			this.descricao= new SimpleStringProperty(novaDescricao);
		else
			this.descricao.set(novaDescricao);
	}

	public void setDataVencimento(String novaDataVencimento) {
		if (this.dataVencimento == null)
			this.dataVencimento= new SimpleStringProperty(novaDataVencimento);
		else
			dataVencimento.set(novaDataVencimento);
	}

	public void addItem(ItemCompra itemCompra) {
		itens.add(itemCompra);
		setTotal(total.get() + itemCompra.getSubtotal());
	}

	private void setTotal(double d) {
		total.set(d);
	}

	public void setItens(ItemCompra[] itensArray) {
		total.set(0.0);
		itens.clear();

		for (ItemCompra item: itensArray) {
			total.set(total.get() + item.getPreco());

			itens.add(item);
		}
	}

	public void setPagamento(Pagamento nPagamento) {
		if (this.pagamento == null)
			this.pagamento = new SimpleObjectProperty<Pagamento>(nPagamento);
		else
			this.pagamento.set(nPagamento);
	}

	public Pagamento setPagamento(int meio, int numeroDeParcelas, double valor) {
		Pagamento nPagamento = new Pagamento(meio, numeroDeParcelas, valor, this);
		if (this.pagamento == null)
			this.pagamento = new SimpleObjectProperty<Pagamento>(nPagamento);
		else
			this.pagamento.set(nPagamento);

		return nPagamento;
	}
}