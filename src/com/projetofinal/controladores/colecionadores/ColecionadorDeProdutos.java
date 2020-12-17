package com.projetofinal.controladores.colecionadores;

import java.util.ArrayList;

import com.projetofinal.classes.Produto;

public class ColecionadorDeProdutos {
    public static ColecionadorDeProdutos colecionadorDeProdutos;
    public ArrayList<Produto> produtos = new ArrayList<>();

    public ColecionadorDeProdutos() {
        populateForTests();
    }

    public static ColecionadorDeProdutos getInstance() {
        if (colecionadorDeProdutos == null) {
            colecionadorDeProdutos = new ColecionadorDeProdutos();
        }

        return colecionadorDeProdutos;
    }

    private void populateForTests() {
        produtos.add(new Produto("Informática", "Notebook", "Lenovo", "NOTEBOOK IDEAPAD S145", 2800.00));
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void remProduto(int i) {
        produtos.remove(i);
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto addProduto(String categoria, String marca, String tipo, String nome, double preco) {
        Produto p = new Produto(categoria, marca, tipo, nome, preco);

        produtos.add(p);
        return p;
    }
}
