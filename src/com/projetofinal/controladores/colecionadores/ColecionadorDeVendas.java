package com.projetofinal.controladores.colecionadores;

import java.util.Calendar;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Compra;
import com.projetofinal.classes.Endereco;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColecionadorDeVendas {
    public ObservableList<Compra> vendas = FXCollections.observableArrayList();

    public ColecionadorDeVendas() {
        populateForTests();
    }

    private void populateForTests() {
        vendas.add(
            new Compra(
            2019,
            new Cliente(
                2010,
                123213123123L,
                "Breno",
                Calendar.getInstance(),
                "Carvalho",
                "brenocarvalho2011@gmail.com",
                "77988082313",
                "77988667459",
                new Endereco(
                    20313,
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

    public ObservableList<Compra> getVendas() {
        return vendas;
    }
    
}
