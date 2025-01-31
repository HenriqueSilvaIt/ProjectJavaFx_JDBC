package com.dev.projectjavafxjdbc;

import com.dev.projectjavafxjdbc.controllers.util.Constraints;
import com.dev.projectjavafxjdbc.model.entities.Department;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable { // tem que
    // colocar o método o  Initializable

    private Department entity;

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

    // Criando instancia da classe Department
    public void setDepartment(Department entity) {
        this.entity = entity;
    }

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

    //Método para atualizar ou salvar informações do formulário
    // Recebemos as informações da classe entity
    public void updateFormData() {
        if ( entity == null) { // Isso é só para garantir que o programador
            // n esqueça de injetar o objeto entity
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId())); // para pegar o id
        // usamos o String.valueOf, pois a caixa de texto (Text Field)
        // trabalha com String e para pegar o id precisamos converter para
        // inteiro, por isso usamos o String.valueOf
        txtName.setText(entity.getName());
    }
}
