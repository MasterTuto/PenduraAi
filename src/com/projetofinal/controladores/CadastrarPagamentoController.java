package com.projetofinal.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import com.projetofinal.classes.Compra;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class CadastrarPagamentoController {
    @FXML
    private ChoiceBox<String> cbFormaPagamento;

    @FXML
    private ChoiceBox<String> cbNumeroDeParcelas;

    @FXML
    public URL location;

    @FXML
    public ResourceBundle resources;

    private Compra venda;

    @FXML
    public void initialize() {
        cbFormaPagamento.getItems().addAll("Dinheiro", "Debito", "Credito");
        cbFormaPagamento.getSelectionModel().selectFirst();

        cbNumeroDeParcelas.getItems().addAll("1x");
        cbNumeroDeParcelas.getSelectionModel().selectFirst();

        cbFormaPagamento.getSelectionModel()
            .selectedIndexProperty()
            .addListener( new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number antigoValor, Number novoValor) {
                    atualizarFormaDePagamento((Integer)novoValor);
                }
            }
        );
    }

    private void fechar(ActionEvent e) {
        Button btn = (Button)e.getSource();
        Stage   st = (Stage )btn.getScene().getWindow();

        st.close();
    }

    public void cancelarCadastro(ActionEvent e ) {
        fechar(e);
    }

    public void setCompra(Compra compra) {
        venda = compra;
    }

    public void atualizarFormaDePagamento(int novoValor) {
        if (novoValor != 2)
            cbNumeroDeParcelas.getItems().setAll("1x");
        else
            cbNumeroDeParcelas.getItems().setAll("1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", "11x", "12x");

        cbNumeroDeParcelas.getSelectionModel().selectFirst();
        
    }

    public void salvarPagamento(ActionEvent e) {
        int meio;
        if (cbFormaPagamento.getValue().equals("Credito"))
            meio = 1;
        else if (cbFormaPagamento.getValue().equals("Debito"))
            meio = 2;
        else
            meio = 3;
        
        int numeroDeParcelas = Integer.parseInt(cbNumeroDeParcelas.getValue().replace("x", ""));


        venda.setPagamento(meio, numeroDeParcelas);
        fechar(e);
    }
}
