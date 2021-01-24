package com.projetofinal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Compra;
import com.projetofinal.classes.Endereco;
import com.projetofinal.classes.Produto;
import com.projetofinal.config.Config;
import com.projetofinal.controladores.colecionadores.ColecionadorDeClientes;
import com.projetofinal.controladores.colecionadores.ColecionadorDeProdutos;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    public TableColumn<Cliente,String> tcClientesCpf;

    @FXML
    public TableColumn<Cliente,String> tcClientesEmail;

    @FXML
    public TableColumn<Cliente,String> tcClientesTelefone;

    @FXML
    public TableColumn<Cliente,Endereco> tcClientesEndereco;

    @FXML
    public TableColumn<Cliente,Double> tcClientesLimite;

    @FXML
    public TextField tfPesquisaVendas;

    @FXML
    public TextField tfPesquisaProduto;

    @FXML
    public TextField tfPesquisaCliente;

    @FXML
    public ImageView ivColorMode;


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

    public Compra mostrarDialogoVenda(ActionEvent event, Compra compra, boolean ehInfo) throws IOException {
        Stage vendaStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CadastrarVenda.fxml"));
        Parent root = loader.load();
        
        CadastrarVendaController cvc = loader.getController();

        Scene scene = new Scene(root);
        if (Config.getDarkMode())
            scene.getStylesheets().add("/com/projetofinal/styles/main.css");

        Button sourceBtn = (Button)event.getSource();
        Stage sourceStage = (Stage)sourceBtn.getScene().getWindow();

        if (compra != null) {
            cvc.setVenda(compra);
        }

        if (ehInfo) {
            cvc.disableAll();
        }

        vendaStage.setOnCloseRequest( new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e) {
                e.consume();
            }
        } );

        vendaStage.setTitle("Cadastro de venda...");
        vendaStage.initOwner(sourceStage);
        vendaStage.initModality(Modality.APPLICATION_MODAL);
        vendaStage.setResizable(false);
        vendaStage.setScene(scene);
        vendaStage.showAndWait();

        return cvc.getCompra();
    }

    private Produto mostrarDialogoProduto(ActionEvent event, Produto produto, boolean ehInfo) throws IOException {
        Stage produtoStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CadastrarProduto.fxml"));
        Parent root = loader.load();
        
        CadastrarProdutoController cpc = loader.getController();

        Scene scene = new Scene(root);
        if (Config.getDarkMode())
            scene.getStylesheets().add("/com/projetofinal/styles/main.css");

        Button sourceBtn = (Button)event.getSource();
        Stage sourceStage = (Stage)sourceBtn.getScene().getWindow();

        if (produto != null)
            cpc.setProduto(produto);

        if (ehInfo)
            cpc.disableAll();

        produtoStage.setOnCloseRequest( new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e) {
                e.consume();
            }
        } );

        produtoStage.setTitle("Cadastro de venda...");
        produtoStage.initOwner(sourceStage);
        produtoStage.initModality(Modality.APPLICATION_MODAL);
        produtoStage.setResizable(false);
        produtoStage.setScene(scene);
        produtoStage.showAndWait();

        return cpc.getProduto();
    }

    private Cliente mostrarDialogoCliente(ActionEvent event, Cliente cliente, boolean ehInfo) throws IOException {
        String fileToImport = "../gui/CadastrarCliente.fxml";
        if (ehInfo) {
            fileToImport = "../gui/InfoCliente.fxml";
        }

        Stage vendaStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileToImport));
        Parent root = loader.load();
        
        CadastrarClienteController ccc = loader.getController();

        Scene scene = new Scene(root);
        if (Config.getDarkMode())
            scene.getStylesheets().add("/com/projetofinal/styles/main.css");

        Button sourceBtn = (Button)event.getSource();
        Stage sourceStage = (Stage)sourceBtn.getScene().getWindow();

        if (cliente != null)
            ccc.setCliente(cliente);

        if (ehInfo)
            ccc.disableAll();

        vendaStage.setOnCloseRequest( new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e) {
                e.consume();
            }
        } );

        vendaStage.setTitle("Informações de cliente");
        vendaStage.initOwner(sourceStage);
        vendaStage.initModality(Modality.APPLICATION_MODAL);
        vendaStage.setResizable(false);
        vendaStage.setScene(scene);
        vendaStage.showAndWait();

        return ccc.getCliente();
    }

    public void addVenda(ActionEvent event) throws Exception {
        Compra venda = mostrarDialogoVenda(event, null, false);
        if (venda != null)
            colecionadorDeVendas.addVenda(venda);
        
        ObservableList<Compra> vendas = FXCollections.observableArrayList();
        for (Compra c: colecionadorDeVendas.getVendas())
            vendas.add(c);

        tvVendas.setItems(vendas);
    }

    public void remVenda(ActionEvent event) {
        Compra v = tvVendas.getSelectionModel().getSelectedItem();

        colecionadorDeVendas.remova(v);
        tvVendas.getItems().remove(v);
    }

    public void addProduto(ActionEvent event) throws IOException {
        Produto produto = mostrarDialogoProduto(event, null, false);
        
        if (produto != null)
            colecionadorDeProdutos.addProduto(produto);
        
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        for (Produto c: colecionadorDeProdutos.getProdutos())
            produtos.add(c);

        tvProdutos.setItems(produtos);
    }

    public void remProduto(ActionEvent event) {
        Produto p = tvProdutos.getSelectionModel().getSelectedItem();

        colecionadorDeProdutos.remova(p);
        tvProdutos.getItems().remove(p);
    }

    public void addCliente(ActionEvent event) throws IOException {
        Cliente cliente = mostrarDialogoCliente(event, null, false);
        if (cliente != null)
            colecionadorDeClientes.addCliente(cliente);
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        for (Cliente c: colecionadorDeClientes.getClientes())
            clientes.add(c);

        tvClientes.setItems(clientes);

    }

    public void remCliente(ActionEvent event) {
        Cliente c = tvClientes.getSelectionModel().getSelectedItem();
        colecionadorDeClientes.remova(c);
        tvClientes.getItems().remove(c);
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
        tcClientesCpf.setCellValueFactory( new PropertyValueFactory<Cliente, String>("cpf") );
        tcClientesEmail.setCellValueFactory( new PropertyValueFactory<Cliente, String>("email") );
        tcClientesTelefone.setCellValueFactory( new PropertyValueFactory<Cliente, String>("telefone") );
        tcClientesEndereco.setCellValueFactory( new PropertyValueFactory<Cliente, Endereco>("endereco") );
        tcClientesLimite.setCellValueFactory( new PropertyValueFactory<Cliente, Double>("limite") );
    }

    public void pesquisarVendas(KeyEvent event) {
        String searchString = tfPesquisaVendas.getText();

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

        ArrayList<Compra> ovVendas = colecionadorDeVendas.getVendas();
        ObservableList<Compra> newVendas = FXCollections.observableArrayList();

        Compra venda;
        for (int i=0; i < ovVendas.size(); i++) {
            venda = ovVendas.get(i);
            if (venda.getCodigo() == searchStringNumber || 
                venda.getCliente().contains(searchString)  ||
                venda.getDataVencimento().contains(searchString)
                ) {
                newVendas.add(venda);
            }
        }

        tvVendas.getItems().setAll(newVendas);
    }

    public void pesquisarProdutos(KeyEvent event) {
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

        ArrayList<Produto> ovProdutos = colecionadorDeProdutos.getProdutos();
        ObservableList<Produto> newProdutos = FXCollections.observableArrayList();

        Produto produto;
        for (int i=0; i < ovProdutos.size(); i++) {
            produto = ovProdutos.get(i);
            if (produto.getCodigo() == searchStringNumber || 
                produto.getNome().contains(searchString)  ||
                produto.getCategoria().contains(searchString) ||
                produto.getMarca().contains(searchString)  ||
                produto.getTipo().contains(searchString)
            ) {
                    newProdutos.add(produto);
            }
        }

        tvProdutos.getItems().setAll(newProdutos);
    }

    public void pesquisarClientes(KeyEvent event) {
        String searchString = tfPesquisaCliente.getText();

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
        String searchStringCpf = searchString.replaceAll("[^0-9]", "");
;
        ArrayList<Cliente> ovClientes = colecionadorDeClientes.getClientes();
        ObservableList<Cliente> newClientes = FXCollections.observableArrayList();

        Cliente cliente;
        for (int i=0; i < ovClientes.size(); i++) {
            cliente = ovClientes.get(i);
            if (cliente.getCodigo() == searchStringNumber || 
                cliente.getNome().contains(searchString)  ||
                ( cliente.getCpf().contains(searchStringCpf) && searchStringCpf.length() > 0 ) ||
                cliente.getEmail().contains(searchString)  ||
                cliente.getTelefone().contains(searchString) ||
                cliente.getEndereco().contains(searchString)
                ) {
                    newClientes.add(cliente);
            }
        }

        tvClientes.getItems().setAll(newClientes);
    }

    public void infoVenda(ActionEvent e) throws IOException {
        Compra compra = tvVendas.getSelectionModel().getSelectedItem();
        if (compra == null) {
            e.consume();
            return;
        }

        mostrarDialogoVenda(e, compra, true);
    }

    public void editarProduto(ActionEvent e) throws IOException{
        Produto produto = tvProdutos.getSelectionModel().getSelectedItem();
        if (produto == null) {
            e.consume();
            return;
        }

        mostrarDialogoProduto(e, produto, false);

        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        for (Produto c: colecionadorDeProdutos.getProdutos())
            produtos.add(c);

        tvProdutos.getItems().setAll(produtos);
    }

    public void mostrarInfoProduto(ActionEvent e) throws Exception {
        Produto produto = tvProdutos.getSelectionModel().getSelectedItem();
        if (produto == null) {
            e.consume();
            return;
        }

        mostrarDialogoProduto(e, produto, true);
    }

    public void editarCliente(ActionEvent e) throws IOException {
        Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            e.consume();
            return;
        }

        mostrarDialogoCliente(e, cliente, false);

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        for (Cliente c: colecionadorDeClientes.getClientes())
            clientes.add(c);

        tvClientes.getItems().setAll(clientes);
        
    }

    public void mostrarInfoCliente(ActionEvent e) throws IOException {
        Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            e.consume();
            return;
        }
        
        Stage infoClienteStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/InfoCliente.fxml"));
        Parent root = loader.load();
        
        InfoClienteController icc = loader.getController();

        Scene scene = new Scene(root);
        if (Config.getDarkMode())
            scene.getStylesheets().add("/com/projetofinal/styles/main.css");

        Button sourceBtn = (Button)e.getSource();
        Stage sourceStage = (Stage)sourceBtn.getScene().getWindow();

        icc.setCliente(cliente);
        icc.disableAll();

        infoClienteStage.setOnCloseRequest( new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e) {
                e.consume();
            }
        } );

        infoClienteStage.setTitle("Informações de cliente");
        infoClienteStage.initOwner(sourceStage);
        infoClienteStage.initModality(Modality.APPLICATION_MODAL);
        infoClienteStage.setResizable(false);
        infoClienteStage.setScene(scene);
        infoClienteStage.showAndWait();
    }

    public void switchColorMode(ActionEvent e) {
        Button btn = (Button)e.getSource();
        if (Config.getDarkMode()) {
            btn.setText("Mudar para modo escuro");
            btn.getScene().getStylesheets().clear();
            Config.setDarkMode(false);
        } else {
            btn.setText("Mudar para modo claro");
            btn.getScene().getStylesheets().add("/com/projetofinal/styles/main.css");
            Config.setDarkMode(true);
        }
    }
}

