package com.projetofinal.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Compra;
import com.projetofinal.classes.ItemCompra;
import com.projetofinal.config.Config;
import com.projetofinal.controladores.colecionadores.ColecionadorDeClientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastrarVendaController {
    @FXML
    public Label lblTotal;

    @FXML
    public TextField tfClientes;

    @FXML
    public TableView<ItemCompra> tvItensDeVenda;

    @FXML
    public TableColumn<ItemCompra, Integer> tcCodigoItem;

    @FXML
    public TableColumn<ItemCompra, String> tcNome;

    @FXML
    public TableColumn<ItemCompra, Integer> tcQuantidade;

    @FXML
    public TableColumn<ItemCompra, Double> tcPrecoUnitario;

    @FXML
    public TableColumn<ItemCompra, Double> tcSubTotal;

    @FXML
    public Label lblCliente;

    @FXML
    public TextField tfDataAtual;
    
    @FXML
    public TextField tfDataVencimento;

    @FXML
    public URL location;

    @FXML
    public ResourceBundle resources;

    @FXML
    public Button btnLimparCliente;

    @FXML
    public Button btnAdicionarProduto;

    @FXML
    public Button btnPesquisarCliente;

    ColecionadorDeClientes cc = ColecionadorDeClientes.getInstance();

    private Compra venda;
    private Calendar dataCorrenta;

    @FXML
    public void initialize() {
        venda = new Compra();

        dataCorrenta = Calendar.getInstance();
        String dataString = dataCorrenta.get(Calendar.DAY_OF_MONTH) + "/" +
                            dataCorrenta.get(Calendar.MONTH) + "/" +
                            dataCorrenta.get(Calendar.YEAR);
        tfDataAtual.setText(dataString);

        tvItensDeVenda.setItems(venda.getItens());
        tcCodigoItem.setCellValueFactory(new PropertyValueFactory<ItemCompra, Integer>("codigo"));
        tcNome.setCellValueFactory(new PropertyValueFactory<ItemCompra, String>("produto"));
        tcQuantidade.setCellValueFactory(new PropertyValueFactory<ItemCompra, Integer>("quantidade"));
        tcPrecoUnitario.setCellValueFactory(new PropertyValueFactory<ItemCompra, Double>("preco"));
        tcSubTotal.setCellValueFactory(new PropertyValueFactory<ItemCompra, Double>("subtotal"));
    }

    public void addProdutoParaVenda(ActionEvent e) throws Exception {
        Stage st = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AddProdutoFromVenda.fxml"));
        Parent root = loader.load();
        AddProdutoFromVendaController apfvc = loader.getController();

        Scene sc = new Scene(root);
        if (Config.getDarkMode())
            sc.getStylesheets().add("/com/projetofinal/styles/main.css");

        st.setScene(sc);
        st.setResizable(false);
        st.initModality(Modality.WINDOW_MODAL);
        st.setTitle("Adicionar item na venda...");
        st.showAndWait();

        ItemCompra ic = apfvc.getItemCompra();
        venda.addItem(ic);
        tvItensDeVenda.setItems(venda.getItens()); //getItems().setAll(venda.getItens());

        lblTotal.setText("R$ "+Double.toString(venda.getTotal()));
    }

    public void limparCliente(ActionEvent event) {
        lblCliente.setText("<nenhum>");
        venda.setCliente(null);
    }

    public void pesquisarCliente(ActionEvent event) throws IOException {
        String searchString = tfClientes.getText();

        ObservableList<Cliente> itens = FXCollections.observableArrayList();
        
        for (Cliente p: cc.getClientes()) {
            String nomeCompleto = p.getNome() + " " + p.getSobrenome();
            String cpf = searchString.replace("[\\.\\-]", "");
            if (
                nomeCompleto.contains(searchString) ||
                p.getCpf().contains(cpf)
            )
                itens.add(p);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ResultadosPesquisaCliente.fxml"));
        Parent root = loader.load();

        ResultadosPesquisaClienteController rpcl = loader.getController();
        rpcl.setItems(itens);

        Scene sc = new Scene(root);
        if (Config.getDarkMode())
            sc.getStylesheets().add("/com/projetofinal/styles/main.css");

        Stage st = new Stage();
        st.setTitle("Escolher professor");
        st.setScene(sc);
        st.showAndWait();

        Cliente cliente = rpcl.getSelecionado();
        if (cliente != null) {
            venda.setCliente(cliente);
            lblCliente.setText(cliente.getNome());
        }
    }

    public Compra getCompra() {
        return venda;
    }

    public void salvarVenda(ActionEvent e) {
        if (tvItensDeVenda.getItems().size() > 0 && tfDataVencimento.getText().length() > 0){
            venda.setData(tfDataAtual.getText());
            venda.setDataVencimento(tfDataVencimento.getText());

            Button btn = (Button)e.getSource();
            Stage scn  = (Stage)btn.getScene().getWindow();
            scn.close();
        }

        if (tfDataVencimento.getText().equals("")) {
            tfDataVencimento.setStyle(tfDataVencimento.getStyle()+"-fx-background-color:#ee2222");
            tfDataAtual.requestFocus();
        }
    }

    public void cancelarVenda(ActionEvent e) {
        venda = null;

        Button btn = (Button)e.getSource();
        Stage scn  = (Stage)btn.getScene().getWindow();
        scn.close();
    }

    public void disableAll() {
        tfDataAtual.setDisable(true);
        tfDataVencimento.setDisable(true);
        tfClientes.setDisable(true);
        btnLimparCliente.setDisable(true);
        btnAdicionarProduto.setDisable(true);
        btnPesquisarCliente.setDisable(true);
    }
    
    public void setVenda(Compra c) {
        venda = c;

        lblTotal.setText("R$ " + venda.getTotal());
        lblCliente.setText(venda.getCliente());
        tfDataAtual.setText(venda.getData());
        tfDataVencimento.setText(venda.getDataVencimento());

        ObservableList<ItemCompra> ics = FXCollections.observableArrayList();
        for (ItemCompra ic: venda.getItens()) {
            ics.add(ic);
        }

        tvItensDeVenda.setItems(ics);
    }
}
