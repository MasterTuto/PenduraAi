package com.projetofinal.controladores.colecionadores;

import java.util.ArrayList;
import java.util.Calendar;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Endereco;

public class ColecionadorDeClientes {
    public static ColecionadorDeClientes colecionadorDeClientes;
    public ArrayList<Cliente> clientes = new ArrayList<>();

    public static ColecionadorDeClientes getInstance() {
        if (colecionadorDeClientes == null) {
            colecionadorDeClientes = new ColecionadorDeClientes();
        }

        return colecionadorDeClientes;
    }

    public ColecionadorDeClientes() {
        populateForTests();
    }

    private void populateForTests() {
        clientes.add(new Cliente(
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
        ));
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(long cpf, String nome, Calendar dataNascimento, String sobrenome, String email, String telefone,
                           String whatsapp, Endereco endereco, double limit) {
        Cliente c = new Cliente(cpf, nome, dataNascimento, sobrenome, email, telefone, whatsapp, endereco, limit);
        
        clientes.add(c);
    }
}
