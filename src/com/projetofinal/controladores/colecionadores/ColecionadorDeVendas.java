package com.projetofinal.controladores.colecionadores;

import java.util.ArrayList;
import java.util.Calendar;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Compra;
import com.projetofinal.classes.Endereco;

public class ColecionadorDeVendas {
    public static ColecionadorDeVendas colecionadorDeVendas;
    public ArrayList<Compra> vendas = new ArrayList<>();

    public ColecionadorDeVendas() {
        populateForTests();
    }
    
    public static ColecionadorDeVendas getInstance() {
        if (colecionadorDeVendas == null) {
            colecionadorDeVendas = new ColecionadorDeVendas();
        }

        return colecionadorDeVendas;
    }

    private void populateForTests() {
        vendas.add(
            new Compra(
            new Cliente(
                123213123123L,
                "Breno",
                Calendar.getInstance(),
                "Carvalho",
                "brenocarvalho2011@gmail.com",
                "77988082313",
                "77988667459",
                new Endereco(
                    "13",
                    "Barra Nova",
                    "45.120-000",
                    "Povoado Cafezal",
                    "Barra do Choca",
                    "Bahia"
                ),
                500.0
            ),
            "25/10/2020",
            "07:23",
            "Mantimentos gays",
            "25/11/2020",
            499.99
            )
        );
    }

    public ArrayList<Compra> getVendas() {
        return vendas;
    }

    public void addVenda(Compra venda) {
        vendas.add(venda);
    }
    
}
