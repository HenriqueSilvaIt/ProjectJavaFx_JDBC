package com.dev.projectjavafxjdbc;

import com.dev.projectjavafxjdbc.controllers.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable { // tem que
    // colocar o método o  Initializable

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;


    // Mensagem de erro caso tenha algo errado no preenchimento do nome
    @FXML
    private Label labalErrorName;

    // Botão Salva
    @FXML
    private Button btSave;

    // Botão cancela
    @FXML
    private Button btCancel;

    @FXML
    public void onBtSaveAction() {
        System.out.println("OnBtSaveAction");
    }

    @FXML
    public void onBtCancelAction() {
        System.out.println("OnBtCancelAction");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        // Limitando o atributo id para aceitar somente números inteiros
        Constraints.setTextFieldInteger(txtId);
        // Limitando o atributo name para aceitar somente 30 caracteres
        Constraints.setTextFieldMaxLength(txtName, 30);
    }
}
