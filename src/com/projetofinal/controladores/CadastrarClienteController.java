package com.projetofinal.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.projetofinal.classes.Cliente;
import com.projetofinal.classes.Endereco;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarClienteController {
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
    URL location;

    @FXML
    ResourceBundle resources;

    private Cliente cliente;

    @FXML
    public void initialize() {
        cliente = new Cliente();
    }

    public void salvarCliente(ActionEvent e) {
        // BEGIN: validation
        String cpfS;
        boolean store = true;
        
        Control[] mandatory = {
            tfNome, tfSobrenome, tfEmail, tfTelefone, tfCpf, dpNascimento
        };

        for (Control c: mandatory) {
            try {
                TextField tf = (TextField)c;
                if (tf.getText().equals("")) {
                    colorir(tf);
                    store = false;
                }
            } catch (ClassCastException exception) {
                DatePicker dp = (DatePicker)c;
                if (dp.getValue() == null) {
                    colorir(dp);
                    store = false;
                }
            }
            
        }
        
        cpfS = tfCpf.getText().replaceAll("[^0-9]", "");
        if (cpfS.length() == 11) {
            cliente.setCpf( cpfS );
        } else {
            colorir(tfCpf);
            store = false;
        }
        
        if (!store) {
            e.consume();
            return;
        }

        // END: validation

        cliente.setNome(tfNome.getText().toUpperCase());
        cliente.setSobrenome(tfSobrenome.getText().toUpperCase());
        cliente.setEmail(tfEmail.getText().toUpperCase());
        cliente.setTelefone(tfTelefone.getText().toUpperCase());
        cliente.setWhatsapp(tfWhatsApp.getText().toUpperCase());

        LocalDate ldNascimento = dpNascimento.getValue();
        Calendar c = Calendar.getInstance();
        c.set(ldNascimento.getYear(), ldNascimento.getMonthValue(), ldNascimento.getDayOfMonth());
        cliente.setDataNascimento(c);

        Endereco endereco = new Endereco(
            tfNumeroCasa.getText() != null ? tfNumeroCasa.getText().toUpperCase() : tfNumeroCasa.getText(),
            tfLogradouro.getText() != null ? tfLogradouro.getText().toUpperCase() : tfLogradouro.getText(),
            tfCep.getText() != null        ? tfCep.getText().toUpperCase()        : tfCep.getText(),
            tfBairro.getText() != null     ? tfBairro.getText().toUpperCase()     : tfBairro.getText(),
            tfMunicipio.getText() != null  ? tfMunicipio.getText().toUpperCase()  : tfMunicipio.getText(),
            tfEstado.getText() != null     ? tfEstado.getText().toUpperCase()     : tfEstado.getText()
        );

        cliente.setEndereco(endereco);
        fechar(e);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void cancelarCadastro(ActionEvent e) {
        cliente.decrementarContador();
        cliente = null;

        fechar(e);
    }

    private void fechar(ActionEvent e) {
        Button btn = (Button)e.getSource();
        Stage stg = (Stage)btn.getScene().getWindow();

        stg.close();
    }

    private void colorir(Control c) {
        if (!c.getStyle().endsWith("-fx-background-color:#ff2e2e;")) {
            c.setStyle(c.getStyle()+"-fx-background-color:#ff2e2e;");
        }
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
        tfCpf.setText(cliente.getCpf());
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
        

    }
}
