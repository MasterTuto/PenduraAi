package com.projetofinal.controladores.colecionadores;

import com.projetofinal.classes.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColecionadorDeProdutos {
    public ObservableList<Produto> produtos = FXCollections.observableArrayList();

    public ColecionadorDeProdutos() {
        populateForTests();
    }

    private void populateForTests() {
        produtos.add(new Produto(21244, 1231, 12, 123, "Cangaia", 12.3));
    }

    public ObservableList<Produto> getProdutos() {
        return produtos;
    }

    public void remProduto(int i) {
        produtos.remove(i);
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto addProduto(int codigo, int categoria, int marca, int tipo, String nome, double preco) {
        Produto p = new Produto(codigo, categoria, marca, tipo, nome, preco);

        produtos.add(p);
        return p;
    }
}
