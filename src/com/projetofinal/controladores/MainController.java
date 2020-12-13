package com.projetofinal.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
    @FXML
    public Button btnVendas;

    @FXML
    public Button btnProdutos;
    
    @FXML
    public Button btnClientes;

    @FXML
    public Button btnResumo;

    public void navegarDaMain(ActionEvent event) throws Exception {
        Button botaoOrigem = (Button)event.getSource();
        Stage stage = (Stage)botaoOrigem.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Sells.fxml"));
        Parent novoConteudo = loader.load();
        Scene scene = new Scene(novoConteudo);
        stage.setScene(scene);

        SellsController sellsController = loader.getController();
        if (botaoOrigem.equals(btnVendas)) {
            sellsController.changePaneTo(0);
        } else if (botaoOrigem.equals(btnProdutos)) {
            sellsController.changePaneTo(1);
        } else if (botaoOrigem.equals(btnClientes)) {
            sellsController.changePaneTo(2);
        } else if (botaoOrigem.equals(btnResumo)) {
            sellsController.changePaneTo(3);
        }
    }
}
