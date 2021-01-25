package com.projetofinal.controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Compra;
import com.projetofinal.classes.Endereco;
import com.projetofinal.config.Config;
import com.projetofinal.controladores.colecionadores.ColecionadorDeVendas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class InfoClienteController {
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfSobrenome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfWhatsApp;

    @FXML
    private TextField tfLogradouro;

    @FXML
    private TextField tfNumeroCasa;

    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfMunicipio;

    @FXML
    private TextField tfEstado;

    @FXML
    private TextField tfCep;

    @FXML
    private DatePicker dpNascimento;

    @FXML
    private Label lblDevendo;

    @FXML
    private Label lblPago;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<Compra> tvCompras;

    @FXML
    private TableColumn<Compra, Integer> tcCodigo;

    @FXML
    private TableColumn<Compra, Double> tcTotal;

    @FXML
    private TableColumn<Compra, String> tcData;

    @FXML
    private TableColumn<Compra, String> tcPago;

    @FXML
    URL location;

    @FXML
    ResourceBundle resources;

    private Cliente cliente;
    private ColecionadorDeVendas cDeVendas = ColecionadorDeVendas.getInstance();

    @FXML
    public void initialize() {
        tcCodigo.setCellValueFactory( new PropertyValueFactory<Compra, Integer>("codigo") );
        tcTotal.setCellValueFactory( new PropertyValueFactory<Compra, Double>("total") );
        tcData.setCellValueFactory( new PropertyValueFactory<Compra, String>("data") );
        tcPago.setCellValueFactory( new PropertyValueFactory<Compra, String>("pagamento") );
    }

    public void fechar(ActionEvent e) {
        Button btn = (Button)e.getSource();
        Stage stg = (Stage)btn.getScene().getWindow();

        stg.close();
    }

    public void disableAll() {
        tfNome.setDisable(true);
        tfCpf.setDisable(true);
        tfSobrenome.setDisable(true);
        tfEmail.setDisable(true);
        tfTelefone.setDisable(true);
        tfWhatsApp.setDisable(true);
        tfLogradouro.setDisable(true);
        tfNumeroCasa.setDisable(true);
        tfBairro.setDisable(true);
        tfMunicipio.setDisable(true);
        tfEstado.setDisable(true);
        tfCep.setDisable(true);
        dpNascimento.setDisable(true);
    }

    public void setCliente(Cliente c) {
        cliente = c;
        Endereco endereco = cliente.getEnderecoObj();
        Calendar dataNascimento = cliente.getDataNascimentoObj();
        int ano = dataNascimento.get(Calendar.YEAR);
        int mes = dataNascimento.get(Calendar.MONTH);
        int dia = dataNascimento.get(Calendar.DAY_OF_MONTH);

        
        tfNome.setText(cliente.getNome());
        tfSobrenome.setText(cliente.getSobrenome());
        tfCpf.setText( cliente.getCpf() );
        tfEmail.setText(cliente.getEmail());
        tfTelefone.setText(cliente.getTelefone());
        tfWhatsApp.setText(cliente.getWhatsapp());
        tfLogradouro.setText(endereco.getRua());
        tfNumeroCasa.setText(endereco.getNumeroCasa());
        tfBairro.setText(endereco.getBairro());
        tfMunicipio.setText(endereco.getMunicipio());
        tfEstado.setText(endereco.getEstado());
        tfCep.setText(endereco.getCep());
        dpNascimento.setValue( LocalDate.of(ano, mes, dia) );

        configurarCompras();
    }

    public void pagarCompra(ActionEvent e) throws IOException {
        // Abrir diálogo
        Compra compra = tvCompras.getSelectionModel().getSelectedItem();

        if (compra == null) {
            e.consume();
            return;
        }

        Button btn = (Button)e.getSource();
        Stage  sourceStage = (Stage)btn.getScene().getWindow();

        if (!compra.getPagamento().equals("<sem pagamento>") ) {
            e.consume();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CadastrarPagamento.fxml"));
        Parent root = loader.load();

        CadastrarPagamentoController cpc = loader.getController();
        cpc.setCompra(compra);

        Scene scene = new Scene(root);
        if (Config.getDarkMode())
            scene.getStylesheets().add("/com/projetofinal/styles/main.css");

        Stage pagamentoStage = new Stage();

        pagamentoStage.setOnCloseRequest( new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e) {
                e.consume();
            }
        } );

        pagamentoStage.setTitle("Informações de cliente");
        pagamentoStage.initOwner(sourceStage);
        pagamentoStage.initModality(Modality.APPLICATION_MODAL);
        pagamentoStage.setResizable(false);
        pagamentoStage.setScene(scene);
        pagamentoStage.showAndWait();

        configurarCompras();
    }

    public void configurarCompras() {
        ObservableList<Compra> compras = FXCollections.observableArrayList();
        double total = 0.0, totalPago = 0.0, totalEmAberto = 0.0;

        for (Compra compra: cDeVendas.getVendas()) {
            if (compra.getClienteObj().getCodigo() == cliente.getCodigo() ) {
                if (compra.getPagamento().equals("<sem pagamento>")) {
                    totalEmAberto += compra.getTotal();
                } else {
                    totalPago += compra.getTotal();
                }
                total += compra.getTotal();

                compras.add(compra);
            }
        }

        tvCompras.getItems().setAll(compras);
        lblDevendo.setText("R$ " + totalEmAberto);
        lblTotal.setText("R$ " + total);
        lblPago.setText("R$ " + totalPago);
    }

}
