package com.projetofinal.controladores.colecionadores;

import java.util.ArrayList;

import com.projetofinal.classes.Compra;

public class ColecionadorDeVendas {
    public static ColecionadorDeVendas colecionadorDeVendas;
    public ArrayList<Compra> vendas = new ArrayList<>();
    
    public static ColecionadorDeVendas getInstance() {
        if (colecionadorDeVendas == null) {
            colecionadorDeVendas = new ColecionadorDeVendas();
        }

        return colecionadorDeVendas;
    }

    public ArrayList<Compra> getVendas() {
        return vendas;
    }

    public void addVenda(Compra venda) {
        vendas.add(venda);
    }

    public void remova(Compra v) {
        vendas.remove(v);
    }
    
}
