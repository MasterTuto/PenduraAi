package com.projetofinal.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import com.projetofinal.classes.Produto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarProdutoController {
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfTipo;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfPreco;

    @FXML
    public URL location;

    @FXML
    public ResourceBundle resources;

    private Produto produto;

    @FXML
    public void initialize() {
        produto = new Produto();
    }

    public void salvarProduto(ActionEvent e) {
        boolean salve = true;

        Control[] textFields = {tfNome, tfCategoria, tfTipo, tfMarca, tfPreco};
        for (Control ctr: textFields) {
            TextField tf = (TextField)ctr;

            if (tf.getText().length() == 0) {
                System.out.println("Acusado...");
                if (!tf.getStyle().endsWith("#ff2e2e;"))
                    tf.setStyle(tf.getStyle()+"-fx-background-color: #ff2e2e;");
                salve = false;
            }
        }

        if (tfPreco.getText().replaceAll("[0-9]", "").length() > 1) {
            System.out.println("Acusado2...");
            if (!tfPreco.getStyle().endsWith("#ff2e2e;"))
                tfPreco.setStyle(tfPreco.getStyle()+"-fx-background-color: #ff2e2e;");
            salve = false;
        }

        double preco;
        try {
            preco = Double.parseDouble(tfPreco.getText());
        } catch (NumberFormatException exception) {
            preco = -1;
            salve = false;
        }
            
        if (salve) {
            produto.setCategoria(tfCategoria.getText().toUpperCase());
            produto.setNome(tfNome.getText().toUpperCase());
            produto.setTipo(tfTipo.getText().toUpperCase());
            produto.setMarca(tfMarca.getText().toUpperCase());
            produto.setPreco(preco);

            close(e);
        }
    }

    private void close(ActionEvent e) {
        Button btn = (Button)e.getSource();
        Stage stg  = (Stage)btn.getScene().getWindow();
        stg.close();
    }

    public void cancelarCadastro(ActionEvent e) {
        produto.decrementarContador();
        produto = null;
        close(e);
        e.consume();
    }

    public Produto getProduto() {
        return produto;
    }

    public void disableAll() {
        tfNome.setDisable(true);
        tfCategoria.setDisable(true);
        tfTipo.setDisable(true);
        tfMarca.setDisable(true);
        tfPreco.setDisable(true);
    }

    public void setProduto(Produto p) {
        produto = p;

        tfNome.setText(produto.getNome());
        tfCategoria.setText(produto.getCategoria());
        tfTipo.setText(produto.getTipo());
        tfMarca.setText(produto.getMarca());
        tfPreco.setText(Double.toString(produto.getPreco()));
    }
}
