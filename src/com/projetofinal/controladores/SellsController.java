package com.projetofinal.controladores;


import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Compra;
import com.projetofinal.classes.Produto;
import com.projetofinal.controladores.colecionadores.ColecionadorDeClientes;
import com.projetofinal.controladores.colecionadores.ColecionadorDeProdutos;
import com.projetofinal.controladores.colecionadores.ColecionadorDeVendas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SellsController {
    @FXML
    public TabPane tpCategorias;

    @FXML
    public ImageView imgBtnHome;

    @FXML
    public TextField tfVendas;

    @FXML
    public TextField tfProdutos;

    @FXML
    public TextField tfClientes;

    // Tabela e coluna de vendas
    @FXML
    public TableView<Compra> tvVendas;
    
    @FXML
    public TableColumn<Compra,Integer> tcVendasCodigo;
    
    @FXML
    public TableColumn<Compra,String> tcVendasCliente;
    
    @FXML
    public TableColumn<Compra,Double> tcVendasValor;
    
    @FXML
    public TableColumn<Compra,String> tcVendasData;
    
    @FXML
    public TableColumn<Compra,String> tcVendasVencimento;
    
    @FXML
    public TableColumn<Compra,String> tcVendasPagamento;

    // Tabela e coluna de Produtos
    @FXML
    public TableView<Produto> tvProdutos;
    
    @FXML
    public TableColumn<Produto,Integer> tvProdutosCodigo;
    
    @FXML
    public TableColumn<Produto,String> tcProdutosNome;
    
    @FXML
    public TableColumn<Produto,Double> tcProdutosPreco;
    
    @FXML
    public TableColumn<Produto,String> tcProdutosCategoria;
    
    @FXML
    public TableColumn<Produto,String> tcProdutosMarca;
    
    @FXML
    public TableColumn<Produto,String> tcProdutosTipo;
    
    // Colunas tabela de clientes
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
    public TableColumn<Cliente,String> tcClientesLimite;

    public ColecionadorDeClientes colecionadorDeClientes = new ColecionadorDeClientes();
    public ColecionadorDeVendas   colecionadorDeVendas   = new ColecionadorDeVendas();
    public ColecionadorDeProdutos colecionadorDeProdutos = new ColecionadorDeProdutos();

    public void changePaneTo(int option) {
        SingleSelectionModel<Tab> selection = tpCategorias.getSelectionModel();
        selection.select(option);

        setVendas();
        setProdutos();
        setClientes();
    }

    public void goHome(MouseEvent event) throws Exception {
        double width, height;

        ImageView botaoOrigem = (ImageView)event.getSource();
        Stage stage = (Stage)botaoOrigem.getScene().getWindow();
        width = stage.getWidth();
        height = stage.getHeight();

        Parent novoConteudo = FXMLLoader.load(getClass().getResource("../gui/Main.fxml"));
        Scene scene = new Scene(novoConteudo, height, width);
        stage.setScene(scene);
    }

    public void addVenda(ActionEvent event) {

    }

    public void remVenda(ActionEvent event) {

    }

    public void addProduto(ActionEvent event) {

    }

    public void remProduto(ActionEvent event) {

    }

    public void addCliente(ActionEvent event) {

    }

    public void remCliente(ActionEvent event) {

    }

    public void setVendas() {
        tvVendas.setItems(colecionadorDeVendas.getVendas());
        tcVendasCodigo.setCellValueFactory( new PropertyValueFactory<Compra, Integer>("codigo") );
        tcVendasCliente.setCellValueFactory( new PropertyValueFactory<Compra, String>("cliente") );
        tcVendasValor.setCellValueFactory( new PropertyValueFactory<Compra, Double>("total") );
        tcVendasData.setCellValueFactory( new PropertyValueFactory<Compra, String>("data") );
        tcVendasVencimento.setCellValueFactory( new PropertyValueFactory<Compra, String>("dataVencimento") );
        tcVendasPagamento.setCellValueFactory( new PropertyValueFactory<Compra, String>("pagamento") );
    }

    public void setProdutos() {
        tvProdutos.setItems(colecionadorDeProdutos.getProdutos());
        tvProdutosCodigo.setCellValueFactory( new PropertyValueFactory<Produto, Integer>("codigo") );
        tcProdutosNome.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome") );
        tcProdutosPreco.setCellValueFactory( new PropertyValueFactory<Produto, Double>("preco") );
        tcProdutosCategoria.setCellValueFactory( new PropertyValueFactory<Produto, String>("categoria") );
        tcProdutosMarca.setCellValueFactory( new PropertyValueFactory<Produto, String>("marca") );
        tcProdutosTipo.setCellValueFactory( new PropertyValueFactory<Produto, String>("tipo") );
    }

    public void setClientes() {
        tvClientes.setItems(colecionadorDeClientes.getClientes());
        tcClientesCodigo.setCellValueFactory( new PropertyValueFactory<Cliente, Integer>("codigo") );
        tcClientesNome.setCellValueFactory( new PropertyValueFactory<Cliente, String>("nome") );
        tcClientesCpf.setCellValueFactory( new PropertyValueFactory<Cliente, String>("total") );
        tcClientesEmail.setCellValueFactory( new PropertyValueFactory<Cliente, String>("data") );
        tcClientesTelefone.setCellValueFactory( new PropertyValueFactory<Cliente, String>("data") );
        tcClientesEndereco.setCellValueFactory( new PropertyValueFactory<Cliente, String>("dataVencimento") );
        tcClientesLimite.setCellValueFactory( new PropertyValueFactory<Cliente, String>("pagamento") );
    }

    public void pesquisarVendas(ActionEvent e) {
        // TODO: obterTexto, pesquisarItens, atualizarTabela
    }

    public void pesquisarProdutos(ActionEvent e) {
        // TODO: obterTexto, pesquisarItens, atualizarTabela
    }

    public void pesquisarClientes(ActionEvent e) {
        // TODO: obterTexto, pesquisarItens, atualizarTabela
    }
}
