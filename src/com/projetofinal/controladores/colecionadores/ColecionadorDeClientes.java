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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(String cpf, String nome, Calendar dataNascimento, String sobrenome, String email, String telefone,
                           String whatsapp, Endereco endereco, double limit) {
        Cliente c = new Cliente(cpf, nome, dataNascimento, sobrenome, email, telefone, whatsapp, endereco, limit);
        
        clientes.add(c);
    }

	public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    
    public void remova(Cliente c) {
        clientes.remove(c);
    }
}
