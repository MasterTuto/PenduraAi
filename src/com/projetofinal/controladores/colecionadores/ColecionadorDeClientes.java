package com.projetofinal.controladores.colecionadores;

import com.projetofinal.classes.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColecionadorDeClientes {
    public ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public ColecionadorDeClientes() {
        populateForTests();
    }

    private void populateForTests() {
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }
}
