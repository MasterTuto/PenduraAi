package com.projetofinal.controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.projetofinal.classes.ItemCompra;
import com.projetofinal.classes.Produto;
import com.projetofinal.controladores.colecionadores.ColecionadorDeProdutos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddProdutoFromVendaController {
    @FXML
    public TextField tfPesquisaProduto;

    @FXML
    public TextField tfQuantidade;

    @FXML
    public TableView<Produto> tvProdutosParaSelecionar;

    @FXML
    public TableColumn<Produto, Integer> tcCodigo;

    @FXML
    public TableColumn<Produto, String> tcNome;
    
    @FXML
    public TableColumn<Produto, String> tcMarca;

    @FXML
    public TableColumn<Produto, String> tcCategoria;

    @FXML
    public TableColumn<Produto, String> tcTipo;

    @FXML
    public TableColumn<Produto, Double> tcPreco;

    @FXML
    public URL location;
    
    @FXML
    private ResourceBundle resources;

    private ColecionadorDeProdutos colecionadorDeProdutos = ColecionadorDeProdutos.getInstance();

    private ItemCompra esseItemCompra;

    @FXML
    public void initialize() {
        pesquisarItem(null);
        tcCodigo.setCellValueFactory( new PropertyValueFactory<Produto, Integer>("codigo") );
        tcNome.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome") );
        tcPreco.setCellValueFactory( new PropertyValueFactory<Produto, Double>("preco") );
        tcCategoria.setCellValueFactory( new PropertyValueFactory<Produto, String>("categoria") );
        tcMarca.setCellValueFactory( new PropertyValueFactory<Produto, String>("marca") );
        tcTipo.setCellValueFactory( new PropertyValueFactory<Produto, String>("tipo") );
    }

    public void pesquisarItem(KeyEvent event) {
        String searchString = tfPesquisaProduto.getText();
        if (event != null)
            if (event.getText().length() == 1)
                searchString += event.getText();
        
        searchString = searchString.toUpperCase();
        int searchStringNumber;
        try {
            searchStringNumber = Integer.parseInt(searchString);
        } catch (Exception e) {
            searchStringNumber = -1;
        }

        colecionadorDeProdutos = ColecionadorDeProdutos.getInstance();

        ArrayList<Produto> ovProdutos = colecionadorDeProdutos.getProdutos();
        ObservableList<Produto> newProdutos = FXCollections.observableArrayList();

        Produto produto;
        for (int i=0; i < ovProdutos.size(); i++) {
            produto = ovProdutos.get(i);
            if (produto.getCodigo() == searchStringNumber || 
                produto.getNome().contains(searchString)  ||
                produto.getCategoria().contains(searchString) ||
                produto.getTipo().contains(searchString)
                ) {
                newProdutos.add(produto);
            }
        }

        tvProdutosParaSelecionar.getItems().setAll(newProdutos);
    }

    public void adicionarItem(ActionEvent e) {
        Produto produto = tvProdutosParaSelecionar.getSelectionModel().getSelectedItem();
        if(produto == null) {
            e.consume();
            return;
        }

        double preco = produto.getPreco();
        int quantidade = Integer.parseInt(tfQuantidade.getText());

        esseItemCompra = new ItemCompra(produto, preco, quantidade);

        Button btn = (Button)e.getSource();
        Stage st = (Stage)btn.getScene().getWindow();
        
        st.close();
    }

    public ItemCompra getItemCompra() {
        return esseItemCompra;
    }
}
