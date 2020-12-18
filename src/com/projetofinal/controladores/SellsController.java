package com.projetofinal.controladores;


import java.io.IOException;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Compra;
import com.projetofinal.classes.Endereco;
import com.projetofinal.classes.Produto;
import com.projetofinal.controladores.colecionadores.ColecionadorDeClientes;
import com.projetofinal.controladores.colecionadores.ColecionadorDeProdutos;
import com.projetofinal.controladores.colecionadores.ColecionadorDeVendas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
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
    public TableColumn<Produto,Integer> tcProdutosCodigo;
    
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
    public TableColumn<Cliente,Long> tcClientesCpf;

    @FXML
    public TableColumn<Cliente,String> tcClientesEmail;

    @FXML
    public TableColumn<Cliente,String> tcClientesTelefone;

    @FXML
    public TableColumn<Cliente,Endereco> tcClientesEndereco;

    @FXML
    public TableColumn<Cliente,Double> tcClientesLimite;

    public ColecionadorDeClientes colecionadorDeClientes = ColecionadorDeClientes.getInstance();
    public ColecionadorDeVendas   colecionadorDeVendas   = ColecionadorDeVendas.getInstance();
    public ColecionadorDeProdutos colecionadorDeProdutos = ColecionadorDeProdutos.getInstance();

    public void changePaneTo(int option) {
        SingleSelectionModel<Tab> selection = tpCategorias.getSelectionModel();
        selection.select(option);

        setVendas();
        setProdutos();
        setClientes();
    }

    public void goHome(MouseEvent event) throws Exception {
        ImageView botaoOrigem = (ImageView)event.getSource();
        Scene currentScene = (Scene)botaoOrigem.getScene();

        Parent novoConteudo = FXMLLoader.load(getClass().getResource("../gui/Main.fxml"));

        currentScene.setRoot(novoConteudo);
    }

    public void addVenda(ActionEvent event) throws Exception {
        Stage vendaStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CadastrarVenda.fxml"));
        Parent root = loader.load();
        
        CadastrarVendaController cvc = loader.getController();

        Scene scene = new Scene(root);

        Button sourceBtn = (Button)event.getSource();
        Stage sourceStage = (Stage)sourceBtn.getScene().getWindow();

        vendaStage.setTitle("Cadastro de venda...");
        vendaStage.initOwner(sourceStage);
        vendaStage.initModality(Modality.APPLICATION_MODAL);
        vendaStage.setResizable(false);
        vendaStage.setScene(scene);
        vendaStage.showAndWait();

        Compra venda = cvc.getCompra();
        if (venda != null)
            colecionadorDeVendas.addVenda(venda);
        
        ObservableList<Compra> vendas = FXCollections.observableArrayList();
        for (Compra c: colecionadorDeVendas.getVendas())
            vendas.add(c);

        tvVendas.setItems(vendas);
    }

    public void remVenda(ActionEvent event) {

    }

    public void addProduto(ActionEvent event) throws IOException {
        Stage produtoStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CadastrarProduto.fxml"));
        Parent root = loader.load();
        
        CadastrarProdutoController cpc = loader.getController();

        Scene scene = new Scene(root);

        Button sourceBtn = (Button)event.getSource();
        Stage sourceStage = (Stage)sourceBtn.getScene().getWindow();

        produtoStage.setTitle("Cadastro de novo produto");
        produtoStage.initOwner(sourceStage);
        produtoStage.initModality(Modality.APPLICATION_MODAL);
        produtoStage.setResizable(false);
        produtoStage.setScene(scene);
        produtoStage.showAndWait();

        Produto produto = cpc.getProduto();
        if (produto != null)
            colecionadorDeProdutos.addProduto(produto);
        
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        for (Produto c: colecionadorDeProdutos.getProdutos())
            produtos.add(c);

        tvProdutos.setItems(produtos);
    }

    public void remProduto(ActionEvent event) {

    }

    public void addCliente(ActionEvent event) throws IOException {
        Stage produtoStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CadastrarCliente.fxml"));
        Parent root = loader.load();
        
        CadastrarClienteController ccc = loader.getController();

        Scene scene = new Scene(root);

        Button sourceBtn = (Button)event.getSource();
        Stage sourceStage = (Stage)sourceBtn.getScene().getWindow();

        produtoStage.setTitle("Cadastro de novo cliente");
        produtoStage.initOwner(sourceStage);
        produtoStage.initModality(Modality.APPLICATION_MODAL);
        produtoStage.setResizable(false);
        produtoStage.setScene(scene);
        produtoStage.showAndWait();

        Cliente cliente = ccc.getCliente();
        if (cliente != null)
            colecionadorDeClientes.addCliente(cliente);
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        for (Cliente c: colecionadorDeClientes.getClientes())
            clientes.add(c);

        tvClientes.setItems(clientes);

    }

    public void remCliente(ActionEvent event) {

    }

    public void setVendas() {
        ObservableList<Compra> compras = FXCollections.observableArrayList();
        for (Compra c: colecionadorDeVendas.getVendas())
            compras.add(c);

        tvVendas.setItems(compras);
        tcVendasCodigo.setCellValueFactory( new PropertyValueFactory<Compra, Integer>("codigo") );
        tcVendasCliente.setCellValueFactory( new PropertyValueFactory<Compra, String>("cliente") );
        tcVendasValor.setCellValueFactory( new PropertyValueFactory<Compra, Double>("total") );
        tcVendasData.setCellValueFactory( new PropertyValueFactory<Compra, String>("data") );
        tcVendasVencimento.setCellValueFactory( new PropertyValueFactory<Compra, String>("dataVencimento") );
        tcVendasPagamento.setCellValueFactory( new PropertyValueFactory<Compra, String>("pagamento") );
    }

    public void setProdutos() {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        for (Produto p: colecionadorDeProdutos.getProdutos())
            produtos.add(p);

        tvProdutos.setItems(produtos);
        tcProdutosCodigo.setCellValueFactory( new PropertyValueFactory<Produto, Integer>("codigo") );
        tcProdutosNome.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome") );
        tcProdutosPreco.setCellValueFactory( new PropertyValueFactory<Produto, Double>("preco") );
        tcProdutosCategoria.setCellValueFactory( new PropertyValueFactory<Produto, String>("categoria") );
        tcProdutosMarca.setCellValueFactory( new PropertyValueFactory<Produto, String>("marca") );
        tcProdutosTipo.setCellValueFactory( new PropertyValueFactory<Produto, String>("tipo") );
    }

    public void setClientes() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        for (Cliente c: colecionadorDeClientes.getClientes())
            clientes.add(c);

        tvClientes.setItems(clientes);
        tcClientesCodigo.setCellValueFactory( new PropertyValueFactory<Cliente, Integer>("codigo") );
        tcClientesNome.setCellValueFactory( new PropertyValueFactory<Cliente, String>("nomeCompleto") );
        tcClientesCpf.setCellValueFactory( new PropertyValueFactory<Cliente, Long>("cpf") );
        tcClientesEmail.setCellValueFactory( new PropertyValueFactory<Cliente, String>("email") );
        tcClientesTelefone.setCellValueFactory( new PropertyValueFactory<Cliente, String>("telefone") );
        tcClientesEndereco.setCellValueFactory( new PropertyValueFactory<Cliente, Endereco>("endereco") );
        tcClientesLimite.setCellValueFactory( new PropertyValueFactory<Cliente, Double>("limite") );
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
