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
        produtos.add(new Produto("INFORMATICA", "NOTEBOOK", "LENOVO", "NOTEBOOK IDEAPAD S145", 1500.00));
        produtos.add(new Produto("INFORMATICA", "CELULAR", "ASUS", "SMT ROG PHONE", 800.00));
        produtos.add(new Produto("ALIMENTO", "ARROZ", "DALON", "ARROZ DALON 1KG", 4.35));
        produtos.add(new Produto("ALIMENTO", "ARROZ", "DONA MARIA", "ARROZ DONA MARIA 1KG", 5.25));
        produtos.add(new Produto("ALIMENTO", "FEIJAO", "TIA MARTA", "FIJAO TIA MARTA", 6.39));
        produtos.add(new Produto("BRINQUEDO", "BONECO", "LIVSTAR", "IRON MAN", 5.50));
        produtos.add(new Produto("BRINQUEDO", "BONECO", "LIVSTAR", "BONECO RAUL GIL", 5.50));
        produtos.add(new Produto("BRINQUEDO", "CARRO",  "LIVSTAR", "LUCCAS NETO BONECO", 5.50));
        produtos.add(new Produto("PAPELARIA", "LIVRO", "EDITORA PRINCIPIS", "MAQUIAVEL - O PRINCIPE", 6.99));
        produtos.add(new Produto("PAPELARIA", "LIVRO", "EDITORA INTRINSECA", "JOHN GREEN - O TEOREMA DE KATHERINES", 6.99));
        produtos.add(new Produto("PAPELARIA", "LIVRO", "EDITORA BRASIL", "GEORGE ORWELL - 1984", 6.99));
        produtos.add(new Produto("PAPELARIA", "LIVRO", "EDITORA MASSA", "ALDOUS HUXLEY - ADMIRAVEL MUNDO NOVO", 6.99));
        produtos.add(new Produto("PAPELARIA", "PAPEL", "CHAMEX", "PAPEL CHAMEX 50G 500FLHS", 16.99));
   
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

    public void remova(Produto p) {
        produtos.remove(p);
    }
}
