package com.projetofinal.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import com.projetofinal.classes.Cliente;
import com.projetofinal.controladores.colecionadores.ColecionadorDeClientes;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ResultadosPesquisaClienteController {
    @FXML
    public TableView<Cliente> tvClientes;
    
    @FXML
    public TableColumn<Cliente,Integer> tcClientesCodigo;

    @FXML
    public TableColumn<Cliente,String> tcClientesNome;

    @FXML
    public TableColumn<Cliente,String> tcClientesCpf;

    @FXML
    public TableColumn<Cliente,String> tcClientesEmail;

    @FXML
    public TableColumn<Cliente,String> tcClientesTelefone;

    @FXML
    public TableColumn<Cliente,String> tcClientesEndereco;

    @FXML
    public TableColumn<Cliente,Double> tcClientesLimite;

    public ColecionadorDeClientes colecionadorDeClientes = ColecionadorDeClientes.getInstance();

    @FXML
    public URL location;

    @FXML
    public ResourceBundle resources;

    private Cliente cliente;

    @FXML
    public void initialize() {
        tcClientesCodigo.setCellValueFactory( new PropertyValueFactory<Cliente, Integer>("codigo") );
        tcClientesNome.setCellValueFactory( new PropertyValueFactory<Cliente, String>("nomeCompleto") );
        tcClientesCpf.setCellValueFactory( new PropertyValueFactory<Cliente, String>("cpf") );
        tcClientesEmail.setCellValueFactory( new PropertyValueFactory<Cliente, String>("email") );
        tcClientesTelefone.setCellValueFactory( new PropertyValueFactory<Cliente, String>("telefone") );
        tcClientesEndereco.setCellValueFactory( new PropertyValueFactory<Cliente, String>("endereco") );
        tcClientesLimite.setCellValueFactory( new PropertyValueFactory<Cliente, Double>("limite") );
    }

    public void setItems(ObservableList<Cliente> itens) {
        tvClientes.setItems(itens);
    }

    public void selecionarCliente(ActionEvent e) {
        cliente = tvClientes.getSelectionModel().getSelectedItem();

        Button btn = (Button)e.getSource();
        Stage stg = (Stage) btn.getScene().getWindow();
        stg.close();

    }

    public Cliente getSelecionado() {
        return cliente;
    }
}
