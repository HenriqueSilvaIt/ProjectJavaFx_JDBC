package com.dev.projectjavafxjdbc;

import com.dev.projectjavafxjdbc.controllers.util.Alerts;
import com.dev.projectjavafxjdbc.controllers.util.Constraints;
import com.dev.projectjavafxjdbc.controllers.util.Utils;
import com.dev.projectjavafxjdbc.db.DbException;
import com.dev.projectjavafxjdbc.model.entities.Department;
import com.dev.projectjavafxjdbc.model.services.DepartmentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable { // tem que
    // colocar o método o  Initializable

    private Department entity;

    private DepartmentService service;

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

    public void setDepartmentService(DepartmentService service) {

        this.service = service;
    }

    // Botão cancela
    @FXML
    private Button btCancel;



    @FXML
    //Salva departamento no banco de dados
    public void onBtSaveAction(ActionEvent event) {

        if ( entity == null && service == null) { // caso o programado esqueceu de injetar
            throw new IllegalStateException("Entity or Service was null, please check");
        }
        try {
            // Instancia o departamento (entity) e salva no banco
            entity = getFormData();
            service.saveOrUpdate(entity);
            // Fechanado janela ao clicar no salvar
            Utils.currentStage(event).close();

        } catch (DbException e) {
            Alerts.showAlert("Error saving objetct", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    // Método apra pegar os dados  do objeto e salvar
    private Department getFormData() {
        Department obj = new Department();

        // pega o id do objeto
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        // Utils.tryParseToInt foi o método que criamo
        // para converter para inteiro, se for um novo id
        // ele considera que é nulo
        obj.setName(txtName.getText());

        return obj;
    }

    @FXML
    public void onBtCancelAction(ActionEvent event) {
        // Cancela o preenchimento do formulário

      // fecha a janela ao clicar no cancelar
       Utils.currentStage(event).close();

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
